package com.minerarcana.runecarved.api.spell;

import com.minerarcana.runecarved.api.caster.ICaster;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public abstract class Spell implements Comparable<Spell> {
    private ResourceLocation name;

    public Spell(@Nonnull ResourceLocation name) {
        this.name = name;
    }

    public abstract void cast(ICaster caster);

    @Nonnull
    public ResourceLocation getRegistryName() {
        return name;
    }

    @Override
    public int compareTo(@Nonnull Spell compare) {
        return getRegistryName().compareTo(compare.getRegistryName());
    }

    @Override
    public boolean equals(Object compare) {
        return this.getClass() == compare.getClass() &&
                getRegistryName().equals(((Spell) compare).getRegistryName());
    }
}
