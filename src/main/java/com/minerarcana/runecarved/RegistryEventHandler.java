package com.minerarcana.runecarved;

import com.minerarcana.runecarved.api.RunecarvedAPI;
import com.minerarcana.runecarved.api.spell.SpellRegistryEvent;
import com.minerarcana.runecarved.enchantments.EnchantmentSpell;
import com.minerarcana.runecarved.item.ItemRunestone;
import com.minerarcana.runecarved.potion.PotionSeeInvisible;
import com.minerarcana.runecarved.spell.*;
import com.teamacronymcoders.base.registrysystem.ItemRegistry;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = Runecarved.MODID)
public class RegistryEventHandler {

    @SubscribeEvent
    public static void createSpellEnchantments(RegistryEvent.Register<Enchantment> event) {
        RunecarvedAPI.getInstance().getSpellRegistry().getSpells().values()
                .forEach(spell -> event.getRegistry().register(new EnchantmentSpell(spell)
                        .setRegistryName(spell.getRegistryName()).setName(spell.getRegistryName().getResourcePath())));
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void createSpellItems(RegistryEvent.Register<Item> itemRegisterEvent) {
        ItemRegistry itemRegistry = Runecarved.instance.getRegistry(ItemRegistry.class, "ITEM");

        RunecarvedAPI.getInstance().getSpellRegistry().getSpells().values().stream().map(ItemRunestone::new)
                .forEach(itemRegistry::register);
    }

    @SubscribeEvent
    public static void createSpellPotions(RegistryEvent.Register<Potion> potionRegistryEvent) {
        potionRegistryEvent.getRegistry().register(PotionSeeInvisible.SEE_INVISIBLE);
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

    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        event.getRegistry().register(
                new RecipesScrollToStone().setRegistryName(new ResourceLocation(Runecarved.MODID, "scroll_to_stone")));
    }
}
