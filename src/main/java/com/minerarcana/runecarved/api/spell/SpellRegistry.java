package com.minerarcana.runecarved.api.spell;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.util.ResourceLocation;

import java.util.Map;

public class SpellRegistry {
    private Map<ResourceLocation, Spell> spells;

    public SpellRegistry() {
        spells = Maps.newHashMap();
    }

    public void register(Spell spell) {
        spells.put(spell.getRegistryName(), spell);
    }

    public Map<ResourceLocation, Spell> getSpells() {
        return ImmutableMap.copyOf(spells);
    }

    public Spell getSpell(ResourceLocation resourceLocation) {
        return spells.get(resourceLocation);
    }
}
