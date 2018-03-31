package com.minerarcana.runecarved.api.spell;

import com.minerarcana.runecarved.api.caster.ICaster;
import net.minecraftforge.registries.IForgeRegistryEntry;

public abstract class Spell extends IForgeRegistryEntry.Impl<Spell> {
    public abstract void cast(ICaster caster);
}
