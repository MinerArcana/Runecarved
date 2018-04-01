package com.minerarcana.runecarved.item;

import com.minerarcana.runecarved.api.spell.Spell;
import com.teamacronymcoders.base.items.ItemBase;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public class ItemRuneStone extends ItemBase {
    private Spell spell;

    public ItemRuneStone(Spell spell) {
        super("runestone." + spell.getRegistryName().toString().replace(":", "."));
        this.spell = spell;
    }

    @Override
    public List<ResourceLocation> getResourceLocations(List<ResourceLocation> resourceLocations) {
        resourceLocations.add(new ResourceLocation(spell.getRegistryName().getResourceDomain(),
                "runes/" + spell.getRegistryName().getResourcePath()));
        return resourceLocations;
    }
}
