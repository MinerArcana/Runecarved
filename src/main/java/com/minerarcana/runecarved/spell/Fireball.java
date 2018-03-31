package com.minerarcana.runecarved.spell;

import com.minerarcana.runecarved.api.caster.ICaster;
import com.minerarcana.runecarved.api.spell.Spell;
import net.minecraft.util.ResourceLocation;

import static com.minerarcana.runecarved.Runecarved.MODID;

public class Fireball extends Spell {
    public Fireball() {
        setRegistryName(new ResourceLocation(MODID, "fireball"));
    }

    @Override
    public void cast(ICaster caster) {

    }
}
