package com.minerarcana.runecarved.api.caster;

import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public interface ICaster {
    Vec2f getCastDirection();

    Vec3d getCastPosition();

    World getWorld();
}
