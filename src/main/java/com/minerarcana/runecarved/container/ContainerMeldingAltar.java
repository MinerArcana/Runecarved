package com.minerarcana.runecarved.container;

import com.minerarcana.runecarved.tileentity.*;
import com.minerarcana.runecarved.tileentity.TileEntityMeldingAltar.ItemStackHandlerExposeInternal;
import com.teamacronymcoders.base.containers.ContainerBase;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerMeldingAltar extends ContainerBase {

	TileEntityMeldingAltar tile;

	public ContainerMeldingAltar(EntityPlayer player, World world, TileEntityMeldingAltar tile) {
		for (int y = 0; y < 3; ++y) {
			for (int x = 0; x < 3; ++x) {
				this.addSlotToContainer(
						new SlotItemHandler(tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null),
								x + y * 3, 61 + x * 18, 15 + y * 18));
			}
		}

		this.addSlotToContainer(new SlotItemHandler(
				tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null), 9, 145, 35));
		this.createPlayerSlots(player.inventory);
		this.tile = tile;
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void detectAndSendChanges() {
		ItemStackHandlerExposeInternal handler = (ItemStackHandlerExposeInternal) this.tile
				.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		for (RecipeMeldingAltar recipe : RecipeMeldingAltar.getRecipeList()) {
			if (recipe.checkItemMatch(handler.getStacks())) {
				if (recipe.checkSpellMatch(((TileEntityRuneIndex) tile.getWorld().getTileEntity(tile.getIndexPos())))) {
					if (handler.getStackInSlot(9).isEmpty()) {
						handler.insertItem(9, recipe.getOutput(), false);
					}
				}
			}
		}
		super.detectAndSendChanges();
	}

}
