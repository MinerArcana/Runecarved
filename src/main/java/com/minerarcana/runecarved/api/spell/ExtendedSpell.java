package com.minerarcana.runecarved.api.spell;

import com.minerarcana.runecarved.api.caster.ICaster;
import net.minecraft.util.ResourceLocation;

public abstract class ExtendedSpell extends Spell {

    public ExtendedSpell(ResourceLocation name) {
        super(name);
    }

    @Override
    public abstract void cast(ICaster caster);

    public abstract void duringCasting(ICaster caster);

    public abstract int getCastDuration();

}
