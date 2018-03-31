package com.minerarcana.runecarved.entity;

import com.minerarcana.runecarved.api.Spells;
import com.minerarcana.runecarved.api.entity.EntityProjectileSpell;
import net.minecraft.world.World;

public class EntityIceBurst extends EntityProjectileSpell {
    public EntityIceBurst(World world) {
        super(world, Spells.ICE_BURST);
    }
}
