package com.minerarcana.runecarved.api.spell;

import com.minerarcana.runecarved.api.caster.ICaster;
import com.minerarcana.runecarved.api.entity.EntityProjectileSpell;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class ProjectileSpell extends Spell {
    public void cast(ICaster caster) {
        EntityProjectileSpell entityProjectileSpell = getEntityProjectileSpell(caster.getWorld());
    }

    public abstract EntityProjectileSpell getEntityProjectileSpell(World world);

    public abstract void onImpact(EntityProjectileSpell entitySpell, Entity entity);

    public abstract void onImpact(EntityProjectileSpell entitySpell, BlockPos pos, EnumFacing impactedSize);
}
