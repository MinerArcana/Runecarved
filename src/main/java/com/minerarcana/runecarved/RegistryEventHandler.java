package com.minerarcana.runecarved;

import com.minerarcana.runecarved.api.RunecarvedAPI;
import com.minerarcana.runecarved.api.entity.EntityProjectileSpell;
import com.minerarcana.runecarved.api.spell.SpellRegistryEvent;
import com.minerarcana.runecarved.enchantments.EnchantmentSpell;
import com.minerarcana.runecarved.entity.EntityBoundZombie;
import com.minerarcana.runecarved.entity.EntityFlame;
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
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

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

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityEntry> event) {
        int networkID = 0;
        EntityRegistry.registerModEntity(new ResourceLocation(Runecarved.MODID, "projectile_spell"),
                EntityProjectileSpell.class, "Generic Spell", networkID++, Runecarved.MODID, 64, 1, true);
        EntityRegistry.registerModEntity(new ResourceLocation(Runecarved.MODID, "flame"), EntityFlame.class, "Flame",
                networkID++, Runecarved.MODID, 64, 1, true);
        EntityRegistry.registerModEntity(new ResourceLocation(Runecarved.MODID, "bound_zombie"),
                EntityBoundZombie.class, "bound_zombie", networkID++, Runecarved.MODID, 64, 8, false, 0xFFFFFF,
                0xAAAAAA);
        // EntityEntry genericSpell =
        // EntityEntryBuilder.create().entity(EntityProjectileSpell.class)
        // .id(new ResourceLocation(Runecarved.MODID, "projectile_spell"),
        // networkID++).name("projectile_spell")
        // .tracker(64, 10, true).build();
        // EntityEntry bound_zombie =
        // EntityEntryBuilder.create().entity(EntityBoundZombie.class)
        // .id(new ResourceLocation(Runecarved.MODID, "bound_zombie"),
        // networkID++).name("bound_zombie")
        // .egg(0xFFFFFF, 0xAAAAAA).tracker(64, 8, false).build();
        // EntityEntry flame = EntityEntryBuilder.create().entity(EntityFlame.class)
        // .id(new ResourceLocation(Runecarved.MODID, "flame"),
        // networkID++).name("flame").tracker(64, 30, true)
        // .build();
        // event.getRegistry().registerAll(bound_zombie, genericSpell, flame);
    }
}
