package com.minerarcana.runecarved.tileentity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.minerarcana.runecarved.api.RunecarvedAPI;
import com.minerarcana.runecarved.container.ContainerRuneIndex;
import com.minerarcana.runecarved.gui.GuiRuneIndex;
import com.teamacronymcoders.base.guisystem.IHasGui;
import com.teamacronymcoders.base.tileentities.TileEntityInventoryBase;

import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;

public class TileEntityRuneIndex extends TileEntityInventoryBase implements IHasGui {

    public TileEntityRuneIndex() {
        super(new ItemHandlerRunic(9));
    }

    @Override
    public Gui getGui(EntityPlayer entityPlayer, World world, BlockPos blockPos) {
        return new GuiRuneIndex(new ContainerRuneIndex(entityPlayer, world, this));
    }

    @Override
    public Container getContainer(EntityPlayer entityPlayer, World world, BlockPos blockPos) {
        return new ContainerRuneIndex(entityPlayer, world, this);
    }
    
	@Override
	public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
		return capability == RunecarvedAPI.RUNE_INDEX || super.hasCapability(capability, facing);
	}
	
	@Override
	@Nonnull
	public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
		if(capability == RunecarvedAPI.RUNE_INDEX) {
			return RunecarvedAPI.RUNE_INDEX.cast(((ItemHandlerRunic)this.getInventory()).getIndex());
		}
		return super.getCapability(capability, facing);
	}

}
