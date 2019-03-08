package com.minerarcana.runecarved.tileentity;

import com.minerarcana.runecarved.container.ContainerSimpleEnchanter;
import com.minerarcana.runecarved.gui.GuiSimpleEnchanter;
import com.teamacronymcoders.base.guisystem.IHasGui;
import com.teamacronymcoders.base.tileentities.TileEntityInventoryBase;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntitySimpleEnchanter extends TileEntityInventoryBase implements IHasGui {

    public TileEntitySimpleEnchanter() {
        super(1);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public Gui getGui(EntityPlayer entityPlayer, World world, BlockPos blockPos) {
        return new GuiSimpleEnchanter(entityPlayer.inventory, world, this);
    }

    @Override
    public Container getContainer(EntityPlayer entityPlayer, World world, BlockPos blockPos) {
        return new ContainerSimpleEnchanter(entityPlayer.inventory, world, this);
    }
}
