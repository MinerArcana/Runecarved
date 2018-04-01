package com.minerarcana.runecarved.api.spell;

import com.minerarcana.runecarved.api.caster.ICaster;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public abstract class Spell {
    private ResourceLocation name;

    public Spell(@Nonnull ResourceLocation name) {
        this.name = name;
    }
    public abstract void cast(ICaster caster);

    @Nonnull
    public ResourceLocation getRegistryName() {
        return name;
    }
}
