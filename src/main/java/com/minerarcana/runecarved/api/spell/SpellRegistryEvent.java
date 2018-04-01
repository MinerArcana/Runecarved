package com.minerarcana.runecarved.api.spell;


import net.minecraftforge.fml.common.eventhandler.Event;

public class SpellRegistryEvent extends Event {
    private SpellRegistry spellRegistry;

    public SpellRegistryEvent(SpellRegistry spellRegistry) {
        this.spellRegistry = spellRegistry;
    }

    public void registerSpell(Spell spell) {
        this.getSpellRegistry().register(spell);
    }

    public SpellRegistry getSpellRegistry() {
        return spellRegistry;
    }
}
