package com.minerarcana.runecarved.api.caster;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class CasterTileEntity implements ICaster {

    TileEntity tile;

    public CasterTileEntity(TileEntity tile) {
        this.tile = tile;
    }

    @Override
    public Vec3d getCastDirection() {
        return new Vec3d(0, 1.0F, 0); // TODO
    }

    @Override
    public Vec3d getCastPosition() {
        BlockPos pos = tile.getPos();
        return new Vec3d(pos.getX(), pos.getY(), pos.getZ());
    }

    @Override
    public World getWorld() {
        return tile.getWorld();
    }

}
