package com.minerarcana.runecarved.gui;

import com.minerarcana.runecarved.tileentity.TileEntityCarvingTable;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.*;

public class HandlerRuneButton implements IMessageHandler<PacketRuneButton, IMessage> {
    public HandlerRuneButton() {

    }

    @Override
    public IMessage onMessage(PacketRuneButton message, MessageContext ctx) {
        EntityPlayerMP serverPlayer = ctx.getServerHandler().player;
        WorldServer worldServer = serverPlayer.getServerWorld();
        worldServer.addScheduledTask(() -> {
            BlockPos pos = message.pos;
            if (worldServer.isBlockLoaded(pos, false)) {
                TileEntity te = worldServer.getTileEntity(message.pos);
                ((TileEntityCarvingTable) te).handleButtonClick(message.name);
            }
        });
        return null;
    }

}
