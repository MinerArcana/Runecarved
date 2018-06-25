package com.minerarcana.runecarved.container;

import com.minerarcana.runecarved.tileentity.TileEntityCarvingTable;
import com.teamacronymcoders.base.containers.ContainerBase;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerCarvingTable extends ContainerBase {
    TileEntityCarvingTable tileCarvingTable;

    public ContainerCarvingTable(EntityPlayer player, World world, TileEntityCarvingTable tile) {
        this.addSlotToContainer(new SlotItemHandler(
                tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null), 0, 82, 35));
        this.addSlotToContainer(new SlotItemHandler(
                tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null), 1, 145, 35));
        this.createPlayerSlots(player.inventory);
        this.tileCarvingTable = tile;
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        // TODO Auto-generated method stub
        return true;
    }

}
