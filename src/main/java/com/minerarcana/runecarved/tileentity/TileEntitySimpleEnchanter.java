package com.minerarcana.runecarved.tileentity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.minerarcana.runecarved.container.ContainerSimpleEnchanter;
import com.minerarcana.runecarved.gui.GuiSimpleEnchanter;
import com.teamacronymcoders.base.guisystem.IHasGui;
import com.teamacronymcoders.base.tileentities.TileEntityBase;

import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntitySimpleEnchanter extends TileEntityBase implements IHasGui {
    private ItemStackHandler inventory = new ItemStackHandler();

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Nullable
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return (T) inventory;
        }
        return super.getCapability(capability, facing);
    }

    // @Override
    // public void readFromNBT(NBTTagCompound data) {
    // inventory.deserializeNBT(data.getCompoundTag("Items"));
    // }
    //
    // @Override
    // @Nonnull
    // public NBTTagCompound writeToNBT(NBTTagCompound data) {
    // data.setTag("Items", inventory.serializeNBT());
    // return data;
    // }

    @Override
    protected void readFromDisk(NBTTagCompound data) {

    }

    @Override
    protected NBTTagCompound writeToDisk(NBTTagCompound data) {
        return data;
    }

    @Override
    public Gui getGui(EntityPlayer entityPlayer, World world, BlockPos blockPos) {
        return new GuiSimpleEnchanter(entityPlayer.inventory, world, this);
    }

    @Override
    public Container getContainer(EntityPlayer entityPlayer, World world, BlockPos blockPos) {
        return new ContainerSimpleEnchanter(entityPlayer.inventory, world, this);
    }
}
