package com.minerarcana.runecarved.spell;

import com.minerarcana.runecarved.api.caster.CasterEntityPlayer;
import com.minerarcana.runecarved.api.caster.ICaster;
import com.minerarcana.runecarved.api.spell.Spell;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;

import static com.minerarcana.runecarved.Runecarved.MODID;

public class Blink extends Spell {
    public Blink() {
        super(new ResourceLocation(MODID, "blink"));
    }

    @Override
    public void cast(ICaster caster) {
        if (caster instanceof CasterEntityPlayer) {
            CasterEntityPlayer playerCaster = (CasterEntityPlayer) caster;
            EntityPlayer playerEntity = playerCaster.getPlayer();
            for (int dist = 8; dist > 0; dist--) {
                Vec3d teleportPosition = caster.getCastPosition().add(playerCaster.getCastDirection().scale(dist));
                if (playerEntity.attemptTeleport(teleportPosition.x, teleportPosition.y, teleportPosition.z)) {
                    break;
                }
            }
        }
    }
}
