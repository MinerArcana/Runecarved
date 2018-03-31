package com.minerarcana.runecarved;

import com.minerarcana.runecarved.container.ContainerSimpleEnchanter;
import com.minerarcana.runecarved.gui.GuiSimpleEnchanter;
import com.minerarcana.runecarved.tileentity.TileEntitySimpleEnchanter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return new ContainerSimpleEnchanter(player.inventory, world, new BlockPos(x, y, z),
				(TileEntitySimpleEnchanter) world.getTileEntity(new BlockPos(x, y, z)));
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		// TODO Auto-generated method stub
		return new GuiSimpleEnchanter(player.inventory, world,
				(TileEntitySimpleEnchanter) world.getTileEntity(new BlockPos(x, y, z)));
	}

}
