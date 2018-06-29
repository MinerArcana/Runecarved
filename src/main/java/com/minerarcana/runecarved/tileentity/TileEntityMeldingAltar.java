package com.minerarcana.runecarved.tileentity;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Nonnull;

import com.google.common.collect.Lists;
import com.minerarcana.runecarved.Runecarved;
import com.minerarcana.runecarved.RunecarvedContent;
import com.minerarcana.runecarved.container.ContainerMeldingAltar;
import com.minerarcana.runecarved.gui.GuiMeldingAltar;
import com.minerarcana.runecarved.tileentity.TileEntityRuneIndex.ItemHandlerRunic;
import com.teamacronymcoders.base.guisystem.IHasGui;
import com.teamacronymcoders.base.tileentities.IOnSlotChanged;
import com.teamacronymcoders.base.tileentities.TileEntityInventoryBase;

import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.RecipeMatcher;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityMeldingAltar extends TileEntityInventoryBase implements IHasGui, IOnSlotChanged {

	private int searchRadius = 3;
	private BlockPos indexPos;
	public RecipeMeldingAltar currentRecipe;

	public TileEntityMeldingAltar() {
		super(new ItemStackHandlerExposeInternal(10));
	}

	public void searchForIndex() {
		for (int x = 0; x < searchRadius; x++) {
			for (int z = 0; z < searchRadius; z++) {
				BlockPos pos = getPos().add(x, 0, z);
				if (getWorld().getBlockState(pos).getBlock() == RunecarvedContent.runeIndex) {
					Runecarved.instance.getLogger().devInfo("Found index at " + pos.toString());
					setIndexPos(pos);
				}
			}
		}
		for (int x = 0; x < searchRadius; x++) {
			for (int z = 0; z < searchRadius; z++) {
				BlockPos pos = getPos().add(-x, 0, -z);
				if (getWorld().getBlockState(pos).getBlock() == RunecarvedContent.runeIndex) {
					Runecarved.instance.getLogger().devInfo("Found index at " + pos.toString());
					setIndexPos(pos);
				}
			}
		}
	}

	public BlockPos getIndexPos() {
		return indexPos;
	}

	private void setIndexPos(BlockPos indexPos) {
		this.indexPos = indexPos;
	}

	@Override
	public Gui getGui(EntityPlayer entityPlayer, World world, BlockPos blockPos) {
		return new GuiMeldingAltar(new ContainerMeldingAltar(entityPlayer, world, this), this);
	}

	@Override
	public Container getContainer(EntityPlayer entityPlayer, World world, BlockPos blockPos) {
		return new ContainerMeldingAltar(entityPlayer, world, this);
	}

	public static class ItemStackHandlerExposeInternal extends ItemStackHandler {
		public ItemStackHandlerExposeInternal(int i) {
			super(i);
		}

		public NonNullList<ItemStack> getStacks() {
			return this.stacks;
		}
	}

	// TODO Slot changed isn't the best place to run this logic
	@Override
	public void onSlotChanged(Slot slot) {
		if (world.isRemote) {
			return;
		}
		ItemStackHandlerExposeInternal handler = (ItemStackHandlerExposeInternal) this
				.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		TileEntityRuneIndex index = ((TileEntityRuneIndex) this.getWorld().getTileEntity(this.getIndexPos()));
		ItemHandlerRunic indexHandler = (ItemHandlerRunic) index
				.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		// OUTPUT
		if (slot.getSlotIndex() == 9) {
			// Remove recipe ingredients when output is 'claimed'
			handler.getStacks().clear(); // TODO This is lazy :D
			for (IngredientSpell spell : currentRecipe.requiredRunes) {
				Runecarved.instance.getLogger().devInfo(spell.getSpell().getRegistryName().toString());
				indexHandler.extractItem(indexHandler.getContainedSpells().get(spell), 1, false);
				this.sendBlockUpdate();
				this.markDirty();
			}
		}
		// CRAFTING GRID
		else {
			// Search for matching recipe each time craftin grid is changed
			// TODO: This doesn't re-search each time runic index changes (may be more
			// trouble than it is worth)
			List<ItemStack> nonEmpty = Lists.newArrayList();
			for (ItemStack stack : handler.getStacks()) {
				if (!stack.isEmpty()) {
					nonEmpty.add(stack);
				}
			}
			for (RecipeMeldingAltar recipe : RecipeMeldingAltar.getRecipeList()) {
				if (RecipeMatcher.findMatches(nonEmpty, Arrays.asList(recipe.inputs)) != null) {
					FMLLog.warning(indexHandler.getContainedSpells().toString());
					FMLLog.warning(indexHandler.getContainedSpells().keySet().toString());
					if (Arrays.asList(recipe.requiredRunes).stream().map(ingredient -> ingredient.getSpell())
							.peek(spell -> FMLLog.warning(spell.getRegistryName().toString()))

							.allMatch(spell -> indexHandler.getContainedSpells().containsKey(spell))) {
						if (handler.getStackInSlot(9).isEmpty()) {
							handler.insertItem(9, recipe.getOutput(), false);
							currentRecipe = recipe;
							this.sendBlockUpdate();
							this.markDirty();
						}
					}
				}
			}
		}
	}

	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToDisk(nbt);
		return new SPacketUpdateTileEntity(pos, 3, nbt);
	}

	@Nonnull
	@Override
	public NBTTagCompound getUpdateTag() {
		NBTTagCompound nbt = super.writeToNBT(new NBTTagCompound());
		this.writeToDisk(nbt);
		return nbt;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		this.readFromDisk(pkt.getNbtCompound());
	}
}
