package com.minerarcana.runecarved.spell;

import static com.minerarcana.runecarved.Runecarved.MODID;

import com.minerarcana.runecarved.api.caster.ICaster;
import com.minerarcana.runecarved.api.spell.Spell;

import net.minecraft.util.ResourceLocation;

public class ManifestTool extends Spell {
    public ManifestTool() {
        super(new ResourceLocation(MODID, "manifest_tool"));
    }

    @Override
    public void cast(ICaster caster) {

    }
}
