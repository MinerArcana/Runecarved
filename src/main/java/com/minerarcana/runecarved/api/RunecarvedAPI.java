package com.minerarcana.runecarved.api;

import com.minerarcana.runecarved.api.spell.SpellRegistry;

public class RunecarvedAPI {
    private static RunecarvedAPI instance;

    private final SpellRegistry spellRegistry;

    public RunecarvedAPI() {
        spellRegistry = new SpellRegistry();
    }

    public SpellRegistry getSpellRegistry() {
        return spellRegistry;
    }

    public static RunecarvedAPI getInstance() {
        if (instance == null) {
            instance = new RunecarvedAPI();
        }
        return instance;
    }
}
