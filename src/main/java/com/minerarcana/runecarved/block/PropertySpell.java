package com.minerarcana.runecarved.block;

import com.minerarcana.runecarved.api.RunecarvedAPI;
import com.minerarcana.runecarved.api.spell.Spell;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.property.IUnlistedProperty;

public class PropertySpell implements IUnlistedProperty<Spell> {

    public Spell parseValue(String value) {
        return RunecarvedAPI.getInstance().getSpellRegistry().getSpell(new ResourceLocation(value));
    }

    @Override
    public String getName() {
        return "spell";
    }

    @Override
    public boolean isValid(Spell value) {
        return RunecarvedAPI.getInstance().getSpellRegistry().getSpells().values().contains(value);
    }

    @Override
    public Class getType() {
        // TODO Auto-generated method stub
        return Spell.class;
    }

    @Override
    public String valueToString(Spell value) {
        return value.getRegistryName().toString();
    }
}
