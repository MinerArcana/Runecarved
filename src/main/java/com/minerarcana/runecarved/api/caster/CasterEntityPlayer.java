package com.minerarcana.runecarved.api.caster;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class CasterEntityPlayer implements ICaster {

    EntityPlayer player;

    public CasterEntityPlayer(EntityPlayer player) {
        this.player = player;
    }

    @Override
    public Vec3d getCastDirection() {
        // TODO Auto-generated method stub
        return player.getLookVec();
    }

    @Override
    public Vec3d getCastPosition() {
        return player.getPositionVector();
    }

    @Override
    public World getWorld() {
        // TODO Auto-generated method stub
        return player.getEntityWorld();
    }
}
