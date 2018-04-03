package com.minerarcana.runecarved.spell;

import static com.minerarcana.runecarved.Runecarved.MODID;

import com.minerarcana.runecarved.api.caster.ICaster;
import com.minerarcana.runecarved.api.spell.Spell;
import com.minerarcana.runecarved.entity.EntityBoundZombie;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class RaiseDead extends Spell {
    public RaiseDead() {
        super(new ResourceLocation(MODID, "raise_dead"));
    }

    @Override
    public void cast(ICaster caster) {
        World world = caster.getWorld();
        if (!world.isRemote) {
            for (int i = 0; i < 5; i++) {
                if (world.rand.nextBoolean()) {
                    EntityBoundZombie corpse = new EntityBoundZombie(world);
                    // if (caster instanceof CasterEntityPlayer) {
                    // corpse.setController(((CasterEntityPlayer) caster).getPlayer());
                    // }
                    corpse.setPositionAndUpdate(caster.getCastPosition().x + world.rand.nextDouble(),
                            caster.getCastPosition().y + world.rand.nextDouble(),
                            caster.getCastPosition().z + world.rand.nextDouble());
                    world.spawnEntity(corpse);
                }
            }
        }
    }
}
