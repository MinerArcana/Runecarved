package com.minerarcana.runecarved.api.entity;

import com.minerarcana.runecarved.api.spell.ProjectileSpell;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class EntityProjectileSpell extends EntityThrowable {
    private ProjectileSpell projectileSpell;

    public EntityProjectileSpell(World world) {
        super(world);
    }

    public EntityProjectileSpell(World world, ProjectileSpell spell) {
        super(world);
        this.projectileSpell = spell;
    }

    @Override
    protected void onImpact(@Nonnull RayTraceResult result) {
        switch (result.typeOfHit) {
            case BLOCK:
                projectileSpell.onImpact(this, result.getBlockPos(), result.sideHit);
                break;
            case ENTITY:
                if (result.entityHit != null) {
                    projectileSpell.onImpact(this, result.entityHit);
                }
                break;
        }
    }
}
