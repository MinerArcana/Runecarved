package com.minerarcana.runecarved.api.spell;

import javax.annotation.Nonnull;

import com.minerarcana.runecarved.api.caster.ICaster;
import com.minerarcana.runecarved.api.entity.EntityProjectileSpell;

import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public abstract class ProjectileSpell extends Spell {
    public ProjectileSpell(@Nonnull ResourceLocation name) {
        super(name);
    }

    public void cast(ICaster caster) {
        EntityProjectileSpell entityProjectileSpell = getEntityProjectileSpell(caster.getWorld());
        Vec3d castPosition = caster.getCastPosition();
        entityProjectileSpell.setPosition(castPosition.x, castPosition.y, castPosition.z);
        Vec3d castDirection = caster.getCastDirection();
        entityProjectileSpell.setVelocity(castDirection.x, castDirection.y, castDirection.z);
        caster.getWorld().spawnEntity(entityProjectileSpell);
        // entityProjectileSpell.shoot();
    }

    public abstract EntityProjectileSpell getEntityProjectileSpell(World world);

    public abstract void onImpact(EntityProjectileSpell entitySpell, Entity entity);

    public abstract void onImpact(EntityProjectileSpell entitySpell, BlockPos pos, EnumFacing impactedSize);
}
