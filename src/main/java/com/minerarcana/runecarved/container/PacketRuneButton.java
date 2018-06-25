package com.minerarcana.runecarved.container;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class PacketRuneButton implements IMessage {

    public String name;
    public BlockPos pos;

    public PacketRuneButton() {

    }

    public PacketRuneButton(String name, BlockPos pos) {
        this.name = name;
        this.pos = pos;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        name = ByteBufUtils.readUTF8String(buf);
        pos = BlockPos.fromLong(buf.readLong());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, name);
        buf.writeLong(pos.toLong());
    }

}
