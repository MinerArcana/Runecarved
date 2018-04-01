package com.minerarcana.runecarved.api.caster;

import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public interface ICaster {
    Vec3d getCastDirection();

    Vec3d getCastPosition();

    World getWorld();
}
