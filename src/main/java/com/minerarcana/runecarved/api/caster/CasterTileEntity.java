package com.minerarcana.runecarved.api.caster;

import com.minerarcana.runecarved.Runecarved;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class CasterTileEntity implements ICaster {

    TileEntity tile;
    EnumFacing facing;

    public CasterTileEntity(TileEntity tile) {
        this.tile = tile;
    }

    public CasterTileEntity(TileEntity tile, EnumFacing facing) {
        this(tile);
        this.facing = facing;
    }

    @Override
    public Vec3d getCastDirection() {
        if (facing != null) {
            Runecarved.instance.getLogger().devInfo(facing.getDirectionVec().toString());
            return new Vec3d(facing.getDirectionVec());
        }
        return new Vec3d(0, 1.0F, 0); // TODO
    }

    @Override
    public Vec3d getCastPosition() {
        BlockPos pos = tile.getPos();
        // Half block offset added to prevent projectiles spawning inside dispenser TODO
        // Directional offset
        Vec3d position = new Vec3d(pos.getX() + 0.5F, pos.getY() + 0.5, pos.getZ() + 0.5);
        // if (facing != null) {
        // position.add(new Vec3d(facing.getDirectionVec()));
        // }
        return position;
    }

    @Override
    public World getWorld() {
        return tile.getWorld();
    }

}
