package com.minerarcana.runecarved.container;

import com.minerarcana.runecarved.tileentity.TileEntityRuneworkTable;
import com.teamacronymcoders.base.containers.ContainerBase;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.world.World;
import net.minecraftforge.items.*;

public class ContainerRuneworkTable extends ContainerBase {
    TileEntityRuneworkTable tile;

    public ContainerRuneworkTable(EntityPlayer player, World world, TileEntityRuneworkTable tile) {
        this.addSlotToContainer(new SlotItemHandler(
                tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null), 0, 25, 33));
        for (int i = 0; i < 4; i++) {
            this.addSlotToContainer(
                    new SlotItemHandler(tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null), i,
                            134 + (i * 10), 33 + (i * 10)));
        }
        this.createPlayerSlots(player.inventory);
        this.tile = tile;
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public void onCraftMatrixChanged(IInventory inventoryIn) {
        super.onCraftMatrixChanged(inventoryIn);
        ItemStackHandler handler = (ItemStackHandler) tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,
                null);
        for (int i = 0; i < 4; i++) {

        }
    }

}
