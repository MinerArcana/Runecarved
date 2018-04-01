package com.minerarcana.runecarved;

import com.minerarcana.runecarved.api.RunecarvedAPI;
import com.minerarcana.runecarved.api.spell.SpellRegistryEvent;
import com.minerarcana.runecarved.item.ItemRuneStone;
import com.minerarcana.runecarved.spell.*;
import com.teamacronymcoders.base.registrysystem.ItemRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = Runecarved.MODID)
public class RegistryEventHandler {
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void createSpellItems(RegistryEvent.Register<Item> itemRegisterEvent) {
        ItemRegistry itemRegistry = Runecarved.instance.getRegistry(ItemRegistry.class, "ITEM");

        RunecarvedAPI.getInstance().getSpellRegistry().getSpells().values().stream()
                .map(ItemRuneStone::new)
                .forEach(itemRegistry::register);
    }

    @SubscribeEvent
    public static void createSpells(SpellRegistryEvent event) {
        event.registerSpell(new Blink());
        event.registerSpell(new Fireball());
        event.registerSpell(new IceBurst());
        event.registerSpell(new LightningBolt());
        event.registerSpell(new LightningBolt());
        event.registerSpell(new ManifestArmor());
        event.registerSpell(new ManifestTool());
        event.registerSpell(new RaiseDead());
        event.registerSpell(new Sight());
    }
}
