package com.minerarcana.runecarved.item;

import java.util.List;

import javax.annotation.*;

import com.minerarcana.runecarved.RunecarvedContent;
import com.minerarcana.runecarved.api.spell.Spell;
import com.minerarcana.runecarved.tileentity.TileEntityRunestone;
import com.teamacronymcoders.base.items.ItemBase;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemRunestone extends ItemBase {
	private final Spell spell;

	public ItemRunestone(Spell spell) {
		super("runestone");
		this.setTranslationKey("runestone." + spell.getRegistryName().getPath());
		this.spell = spell;
		BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(this, new RunestoneDispenserBehavior());
	}

	@Override
	public List<ResourceLocation> getResourceLocations(List<ResourceLocation> resourceLocations) {
		resourceLocations.add(new ResourceLocation(spell.getRegistryName().getNamespace(),
				"runes/" + spell.getRegistryName().getPath()));
		return resourceLocations;
	}

	@SuppressWarnings("deprecation")
	@Override
	@SideOnly(Side.CLIENT)
	@ParametersAreNonnullByDefault
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		String rawTooltip = I18n.translateToLocal("spell." + this.spell.getRegistryName().getPath());
		String[] splitTooltip = rawTooltip.split("/");
		for (int i = 1; i < splitTooltip.length; i++) {
			String format = "";
			if (i == 1) {
				format = TextFormatting.BLUE.toString();
			}

			tooltip.add(format + splitTooltip[i]);
		}
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	@Override
	@ParametersAreNonnullByDefault
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
			items.add(new ItemStack(this));
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	@Nonnull
	@ParametersAreNonnullByDefault
	public String getItemStackDisplayName(ItemStack stack) {
		return I18n.translateToLocal("spell." + this.spell.getRegistryName().getPath()).split("/")[0] + " "
				+ I18n.translateToLocal("item.runecarved.runestone.name");
	}

	@Override
	@Nonnull
	public String getTranslationKey(ItemStack stack) {
		return this.getTranslationKey();
	}

	@Override
	@Nonnull
	public String getTranslationKey() {
		return "item.runestone." + spell.getRegistryName().getPath();
	}

	/**
	 * Called when a Block is right-clicked with this Item
	 */
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		IBlockState iblockstate = worldIn.getBlockState(pos);
		Block block = iblockstate.getBlock();

		if (!block.isReplaceable(worldIn, pos)) {
			pos = pos.offset(facing);
		}

		ItemStack itemstack = player.getHeldItem(hand);

		if (!itemstack.isEmpty() && player.canPlayerEdit(pos, facing, itemstack)
				&& worldIn.mayPlace(RunecarvedContent.runestoneBlock, pos, false, facing, (Entity) null)) {
			int i = this.getMetadata(itemstack.getMetadata());
			IBlockState iblockstate1 = RunecarvedContent.runestoneBlock.getStateForPlacement(worldIn, pos, facing, hitX,
					hitY, hitZ, i, player, hand);

			if (placeBlockAt(itemstack, player, worldIn, pos, facing, hitX, hitY, hitZ, iblockstate1)) {
				iblockstate1 = worldIn.getBlockState(pos);
				SoundType soundtype = iblockstate1.getBlock().getSoundType(iblockstate1, worldIn, pos, player);
				worldIn.playSound(player, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS,
						(soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
				itemstack.shrink(1);
			}

			return EnumActionResult.SUCCESS;
		} else {
			return EnumActionResult.FAIL;
		}
	}

	public static boolean setTileEntityNBT(World worldIn, @Nullable EntityPlayer player, BlockPos pos,
			ItemStack stackIn) {
		MinecraftServer minecraftserver = worldIn.getMinecraftServer();

		if (minecraftserver == null) {
			return false;
		} else {
			NBTTagCompound nbttagcompound = stackIn.getSubCompound("BlockEntityTag");

			if (nbttagcompound != null) {
				TileEntity tileentity = worldIn.getTileEntity(pos);

				if (tileentity != null) {
					if (!worldIn.isRemote && tileentity.onlyOpsCanSetNbt()
							&& (player == null || !player.canUseCommandBlock())) {
						return false;
					}

					NBTTagCompound nbttagcompound1 = tileentity.writeToNBT(new NBTTagCompound());
					NBTTagCompound nbttagcompound2 = nbttagcompound1.copy();
					nbttagcompound1.merge(nbttagcompound);
					nbttagcompound1.setInteger("x", pos.getX());
					nbttagcompound1.setInteger("y", pos.getY());
					nbttagcompound1.setInteger("z", pos.getZ());

					if (!nbttagcompound1.equals(nbttagcompound2)) {
						tileentity.readFromNBT(nbttagcompound1);
						tileentity.markDirty();
						return true;
					}
				}
			}

			return false;
		}
	}

	@SideOnly(Side.CLIENT)
	public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side, EntityPlayer player,
			ItemStack stack) {
		Block block = worldIn.getBlockState(pos).getBlock();

		if (block == Blocks.SNOW_LAYER && block.isReplaceable(worldIn, pos)) {
			side = EnumFacing.UP;
		} else if (!block.isReplaceable(worldIn, pos)) {
			pos = pos.offset(side);
		}

		return worldIn.mayPlace(RunecarvedContent.runestoneBlock, pos, false, side, (Entity) null);
	}

	public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side,
			float hitX, float hitY, float hitZ, IBlockState newState) {
		if (!world.setBlockState(pos, newState, 11))
			return false;

		IBlockState state = world.getBlockState(pos);
		if (state.getBlock() == RunecarvedContent.runestoneBlock) {
			setTileEntityNBT(world, player, pos, stack);
			RunecarvedContent.runestoneBlock.onBlockPlacedBy(world, pos, state, player, stack);

			if (player instanceof EntityPlayerMP)
				CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP) player, pos, stack);
		}

		TileEntity tileEntity = world.getTileEntity(pos);
		if (tileEntity instanceof TileEntityRunestone) {
			((TileEntityRunestone) tileEntity).spell = spell;
		}

		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

	@Override
	@Nonnull
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.RARE;
	}

	@Override
	@Nonnull
	public Item getItem() {
		return this;
	}

	public Spell getSpell() {
		return spell;
	}

}
