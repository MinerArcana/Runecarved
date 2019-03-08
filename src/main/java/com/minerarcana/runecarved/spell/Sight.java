package com.minerarcana.runecarved.spell;

import com.minerarcana.runecarved.api.caster.CasterEntityPlayer;
import com.minerarcana.runecarved.api.caster.ICaster;
import com.minerarcana.runecarved.api.spell.Spell;
import com.minerarcana.runecarved.potion.PotionSeeInvisible;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

import static com.minerarcana.runecarved.Runecarved.MODID;

public class Sight extends Spell {
    public Sight() {
        super(new ResourceLocation(MODID, "sight"));
    }

    @Override
    public void cast(ICaster caster) {
        // TODO Make rune work from world
        if (caster instanceof CasterEntityPlayer) {
            CasterEntityPlayer playerCaster = (CasterEntityPlayer) caster;
            EntityPlayer playerEntity = playerCaster.getPlayer();
            playerEntity.addPotionEffect(new PotionEffect(PotionSeeInvisible.SEE_INVISIBLE, 600));
        }
    }
}
