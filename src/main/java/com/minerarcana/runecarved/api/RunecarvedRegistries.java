package com.minerarcana.runecarved.api;

import com.minerarcana.runecarved.api.spell.Spell;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

public class RunecarvedRegistries {
    public static final IForgeRegistry<Spell> SPELLS = GameRegistry.findRegistry(Spell.class);
}
