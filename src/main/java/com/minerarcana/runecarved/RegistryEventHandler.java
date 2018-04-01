package com.minerarcana.runecarved;

import com.minerarcana.runecarved.api.spell.Spell;
import com.minerarcana.runecarved.enchantments.EnchantmentSpell;
import com.minerarcana.runecarved.spell.Fireball;
import com.minerarcana.runecarved.spell.IceBurst;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;

@EventBusSubscriber(modid = Runecarved.MODID)
public class RegistryEventHandler {
    @SubscribeEvent
    public static void buildRegistries(RegistryEvent.NewRegistry newRegistryEvent) {
        new RegistryBuilder<Spell>().setName(new ResourceLocation(Runecarved.MODID, "spells")).setType(Spell.class)
                .create();
    }

    @SubscribeEvent
    public static void registerSpells(RegistryEvent.Register<Spell> registerSpellsEvent) {
        IForgeRegistry<Spell> registry = registerSpellsEvent.getRegistry();
        registry.register(new IceBurst());
        registry.register(new Fireball());
        registry.forEach(spell -> GameRegistry.findRegistry(Enchantment.class).register(new EnchantmentSpell()
                .setRegistryName(spell.getRegistryName()).setName(spell.getRegistryName().getResourcePath())));
    }
}
