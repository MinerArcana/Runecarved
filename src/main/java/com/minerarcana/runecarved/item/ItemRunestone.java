package com.minerarcana.runecarved.item;

import java.util.List;

import javax.annotation.Nullable;

import com.minerarcana.runecarved.RunecarvedContent;
import com.minerarcana.runecarved.api.spell.Spell;
import com.minerarcana.runecarved.block.BlockRunestone;
import com.minerarcana.runecarved.tileentity.TileEntityRunestone;
import com.teamacronymcoders.base.items.itemblocks.ItemBlockModel;

import net.minecraft.block.BlockDispenser;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemRunestone extends ItemBlockModel<BlockRunestone> {

	private Spell spell;

	public ItemRunestone(Spell spell) {
		super((BlockRunestone) RunecarvedContent.runestoneBlock);
		this.setUnlocalizedName("runestone." + spell.getRegistryName().getResourcePath());
		this.spell = spell;
		BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(this, new RunestoneDispenserBehavior());
	}

	@SuppressWarnings("deprecation")
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		String rawTooltip = I18n.translateToLocal("spell." + this.spell.getRegistryName().getResourcePath());
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

	// Override default behaviour of ItemBlock which is fall back to the block
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
			items.add(new ItemStack(this));
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		return I18n.translateToLocal("spell." + this.spell.getRegistryName().getResourcePath()).split("/")[0] + " "
				+ I18n.translateToLocal("item.runecarved.runestone.name");
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return this.getUnlocalizedName();
	}

	@Override
	public String getUnlocalizedName() {
		return "item.runestone." + spell.getRegistryName().getResourcePath();
	}

	@Override
	public List<ResourceLocation> getResourceLocations(List<ResourceLocation> resourceLocations) {
		resourceLocations.add(new ResourceLocation(spell.getRegistryName().getResourceDomain(),
				"runes/" + spell.getRegistryName().getResourcePath()));
		return resourceLocations;
	}

	@Override
	public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side,
			float hitX, float hitY, float hitZ, IBlockState newState) {
		super.placeBlockAt(stack, player, world, pos, side, hitX, hitY, hitZ, newState);
		((TileEntityRunestone) world.getTileEntity(pos)).spell = spell;
		if (!player.capabilities.isCreativeMode) {
			stack.shrink(1);
		}
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.RARE;
	}

	@Override
	public Item getItem() {
		return this;
	}

	public Spell getSpell() {
		return spell;
	}
}
