package com.minerarcana.runecarved.spell;

import com.minerarcana.runecarved.api.caster.ICaster;
import com.minerarcana.runecarved.api.spell.Spell;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

import static com.minerarcana.runecarved.Runecarved.MODID;

public class Blink extends Spell {
    public Blink() {
        super(new ResourceLocation(MODID, "blink"));
    }

    @Override
    public void cast(ICaster caster) {

    }
}
