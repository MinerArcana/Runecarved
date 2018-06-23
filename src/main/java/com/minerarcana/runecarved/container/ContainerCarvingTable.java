package com.minerarcana.runecarved.container;

import com.minerarcana.runecarved.tileentity.TileEntityCarvingTable;
import com.teamacronymcoders.base.containers.ContainerBase;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class ContainerCarvingTable extends ContainerBase {
    TileEntityCarvingTable tile;

    public ContainerCarvingTable(EntityPlayer player, World world, TileEntityCarvingTable tile) {
        this.createPlayerSlots(player.inventory);
        this.tile = tile;
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        // TODO Auto-generated method stub
        return true;
    }

}
