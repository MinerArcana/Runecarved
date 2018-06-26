package com.minerarcana.runecarved.tileentity;

import com.minerarcana.runecarved.Runecarved;
import com.minerarcana.runecarved.RunecarvedContent;
import com.minerarcana.runecarved.container.ContainerMeldingAltar;
import com.minerarcana.runecarved.gui.GuiMeldingAltar;
import com.teamacronymcoders.base.guisystem.IHasGui;
import com.teamacronymcoders.base.tileentities.TileEntityInventoryBase;

import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityMeldingAltar extends TileEntityInventoryBase implements IHasGui {

	private int searchRadius = 3;
	private BlockPos indexPos;

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
}
