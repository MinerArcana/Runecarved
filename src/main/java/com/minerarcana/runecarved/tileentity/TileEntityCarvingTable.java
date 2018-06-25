package com.minerarcana.runecarved.tileentity;

import com.minerarcana.runecarved.Runecarved;
import com.minerarcana.runecarved.RunecarvedContent;
import com.minerarcana.runecarved.container.ContainerCarvingTable;
import com.minerarcana.runecarved.gui.GuiCarvingTable;
import com.teamacronymcoders.base.guisystem.IHasGui;
import com.teamacronymcoders.base.tileentities.TileEntityInventoryBase;

import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class TileEntityCarvingTable extends TileEntityInventoryBase implements IHasGui {

    private int searchRadius = 3;
    private BlockPos indexPos;

    public TileEntityCarvingTable() {
        super(2);
    }

    @Override
    public Gui getGui(EntityPlayer entityPlayer, World world, BlockPos blockPos) {
        // TODO Auto-generated method stub
        return new GuiCarvingTable(new ContainerCarvingTable(entityPlayer, world, this), this);
    }

    @Override
    public Container getContainer(EntityPlayer entityPlayer, World world, BlockPos blockPos) {
        // TODO Auto-generated method stub
        return new ContainerCarvingTable(entityPlayer, world, this);
    }

    @Override
    protected void readFromDisk(NBTTagCompound data) {
    }

    @Override
    protected NBTTagCompound writeToDisk(NBTTagCompound data) {
        return data;
    }

    public void searchForIndex() {
        for (int x = 0; x < searchRadius; x++) {
            for (int z = 0; z < searchRadius; z++) {
                BlockPos pos = this.getPos().add(x, 0, z);
                if (this.getWorld().getBlockState(pos).getBlock() == RunecarvedContent.runeIndex) {
                    Runecarved.instance.getLogger().devInfo("Found index at " + pos.toString());
                    this.indexPos = pos;
                }
            }
        }
        for (int x = 0; x < searchRadius; x++) {
            for (int z = 0; z < searchRadius; z++) {
                BlockPos pos = this.getPos().add(-x, 0, -z);
                if (this.getWorld().getBlockState(pos).getBlock() == RunecarvedContent.runeIndex) {
                    Runecarved.instance.getLogger().devInfo("Found index at " + pos.toString());
                    this.indexPos = pos;
                }
            }
        }
    }

    public BlockPos getIndexPos() {
        return indexPos;
    }

    public void handleButtonClick(String spellName) {
        IItemHandler handler = this.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
        if (handler.getStackInSlot(1).isEmpty()
                && handler.getStackInSlot(0).getItem() == Item.getItemFromBlock(Blocks.STONE_PRESSURE_PLATE)) {
            handler.insertItem(1, new ItemStack(Item.getByNameOrId("runecarved:runestone." + spellName)), false);
        }
    }
}
