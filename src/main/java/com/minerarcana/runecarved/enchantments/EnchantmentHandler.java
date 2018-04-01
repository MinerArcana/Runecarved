package com.minerarcana.runecarved.enchantments;

import com.minerarcana.runecarved.Runecarved;
import com.minerarcana.runecarved.api.RunecarvedAPI;
import com.minerarcana.runecarved.api.spell.Spell;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.init.Items;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class EnchantmentHandler {
    public static final EnumEnchantmentType SPELL = EnumHelper.addEnchantmentType("spells",
            item -> item == Items.PAPER);

    @SubscribeEvent
    public static void registerEnchantments(Register<Enchantment> event) {
        for (Spell spell : RunecarvedAPI.getInstance().getSpellRegistry().getSpells().values()) {

            Runecarved.instance.getLogger().devInfo(spell.getRegistryName().getResourcePath());
        }
    }

}
