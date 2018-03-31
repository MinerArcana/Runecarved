package com.minerarcana.runecarved.spell;

import com.minerarcana.runecarved.api.entity.EntityProjectileSpell;
import com.minerarcana.runecarved.api.spell.ProjectileSpell;
import com.minerarcana.runecarved.entity.EntityIceBurst;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class IceBurst extends ProjectileSpell {
    @Override
    public EntityProjectileSpell getEntityProjectileSpell(World world) {
        return new EntityIceBurst(world);
    }

    @Override
    public void onImpact(EntityProjectileSpell entitySpell, @Nonnull Entity entity) {
        entity.attackEntityFrom(DamageSource.causeThrownDamage(entitySpell, entitySpell.getThrower()),
                //TODO Spell Resistances
                entity instanceof EntityBlaze ? 6f : 3f);
    }

    @Override
    public void onImpact(EntityProjectileSpell entitySpell, BlockPos pos, EnumFacing impactedSize) {

    }
}
