package com.minerarcana.runecarved.spell;

import com.minerarcana.runecarved.api.caster.CasterEntityPlayer;
import com.minerarcana.runecarved.api.caster.ICaster;
import com.minerarcana.runecarved.api.spell.ExtendedSpell;
import com.minerarcana.runecarved.entity.EntityFlame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;

import static com.minerarcana.runecarved.Runecarved.MODID;

public class Fireball extends ExtendedSpell {

    public Fireball() {
        super(new ResourceLocation(MODID, "fire"));
    }

    @Override
    public void cast(ICaster caster) {
        // NO-OP
    }

    @Override
    public int getCastDuration() {
        return 1600;
    }

    @Override
    public void duringCasting(ICaster caster) {
        if (caster instanceof CasterEntityPlayer) {
            CasterEntityPlayer playerCaster = (CasterEntityPlayer) caster;
            EntityPlayer playerEntity = playerCaster.getPlayer();
            Vec3d v = playerEntity.getLookVec();
            int split = 8;
            float scatter = .05f;
            float range = 1f;
            for (int i = 0; i < split; i++) {
                Vec3d vecDir = v.add(playerEntity.getRNG().nextGaussian() * scatter,
                        playerEntity.getRNG().nextGaussian() * scatter, playerEntity.getRNG().nextGaussian() * scatter);
                EntityFlame projectile = new EntityFlame(playerEntity.getEntityWorld(), playerEntity);
                projectile.motionX = vecDir.x * range;
                projectile.motionY = vecDir.y * range;
                projectile.motionZ = vecDir.z * range;
                if (!playerEntity.getEntityWorld().isRemote) {
                    projectile.ignoreEntity = playerEntity;
                    projectile.setFire(10);
                    playerEntity.getEntityWorld().spawnEntity(projectile);
                }
            }
        }
    }
}
