package com.minerarcana.runecarved.spell;

import com.minerarcana.runecarved.api.caster.ICaster;
import com.minerarcana.runecarved.api.spell.Spell;
import net.minecraft.util.ResourceLocation;

import static com.minerarcana.runecarved.Runecarved.MODID;

public class Sight extends Spell {
    public Sight() {
        super(new ResourceLocation(MODID, "sight"));
    }

    @Override
    public void cast(ICaster caster) {

    }
}
