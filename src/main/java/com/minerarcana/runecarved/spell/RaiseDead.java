package com.minerarcana.runecarved.spell;

import com.minerarcana.runecarved.api.caster.ICaster;
import com.minerarcana.runecarved.api.spell.Spell;
import net.minecraft.util.ResourceLocation;

import static com.minerarcana.runecarved.Runecarved.MODID;

public class RaiseDead extends Spell {
    public RaiseDead() {
        super(new ResourceLocation(MODID, "raise_dead"));
    }

    @Override
    public void cast(ICaster caster) {

    }
}
