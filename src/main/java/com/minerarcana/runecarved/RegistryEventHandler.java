package com.minerarcana.runecarved;

import com.minerarcana.runecarved.api.spell.Spell;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.RegistryBuilder;


@EventBusSubscriber(modid = Runecarved.MODID)
public class RegistryEventHandler {
    public static void buildRegistries(RegistryEvent.NewRegistry newRegistryEvent) {
        new RegistryBuilder<Spell>()
                .setName(new ResourceLocation(Runecarved.MODID, "spells"))
                .setType(Spell.class)
                .create();
    }
}
