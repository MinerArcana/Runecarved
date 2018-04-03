package com.minerarcana.runecarved.spell;

import static com.minerarcana.runecarved.Runecarved.MODID;

import com.minerarcana.runecarved.api.caster.CasterEntityPlayer;
import com.minerarcana.runecarved.api.caster.ICaster;
import com.minerarcana.runecarved.api.spell.Spell;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class Blink extends Spell {
    public Blink() {
        super(new ResourceLocation(MODID, "blink"));
    }

    @Override
    public void cast(ICaster caster) {
        if (caster instanceof CasterEntityPlayer) {
            CasterEntityPlayer playerCaster = (CasterEntityPlayer) caster;
            EntityPlayer playerEntity = playerCaster.getPlayer();
            BlockPos last = null;
            BlockPos current;
            for (int i = 0; i < 8; i++) {
                current = new BlockPos(caster.getCastDirection().x + i, caster.getCastDirection().y + i,
                        caster.getCastDirection().z + i);
                if (!playerEntity.getEntityWorld().isAirBlock(current)) {
                    break;
                }
                last = current;
            }
            if (last != null) {
                playerEntity.attemptTeleport(last.getX(), last.getY(), last.getZ());
            }
        }
    }
}
