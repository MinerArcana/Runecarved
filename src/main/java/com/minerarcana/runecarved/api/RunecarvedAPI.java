package com.minerarcana.runecarved.api;

import com.minerarcana.runecarved.api.capability.IRuneIndex;
import com.minerarcana.runecarved.api.spell.SpellRegistry;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class RunecarvedAPI {
    private static RunecarvedAPI instance;

    private final SpellRegistry spellRegistry;
    
    @CapabilityInject(IRuneIndex.class)
	public static Capability<IRuneIndex> RUNE_INDEX;

    public RunecarvedAPI() {
        spellRegistry = new SpellRegistry();
    }

    public static RunecarvedAPI getInstance() {
        if (instance == null) {
            instance = new RunecarvedAPI();
        }
        return instance;
    }

    public SpellRegistry getSpellRegistry() {
        return spellRegistry;
    }
}
