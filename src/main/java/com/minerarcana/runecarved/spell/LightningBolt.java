package com.minerarcana.runecarved.spell;

import com.minerarcana.runecarved.api.caster.ICaster;
import com.minerarcana.runecarved.api.spell.Spell;
import net.minecraft.util.ResourceLocation;

import static com.minerarcana.runecarved.Runecarved.MODID;

public class LightningBolt extends Spell {
    public LightningBolt() {
        super(new ResourceLocation(MODID, "lightning_bolt"));
    }

    @Override
    public void cast(ICaster caster) {

    }
}
