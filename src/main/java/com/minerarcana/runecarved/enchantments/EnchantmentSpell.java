package com.minerarcana.runecarved.enchantments;

import com.minerarcana.runecarved.Runecarved;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

public class EnchantmentSpell extends Enchantment {

    @ObjectHolder(value = Runecarved.MODID + ":scroll")
    public static final Item scroll = null;

    public static final EnumEnchantmentType SPELL = EnumHelper.addEnchantmentType("spells", item -> item == scroll);

    public EnchantmentSpell() {
        super(Enchantment.Rarity.RARE, SPELL, new EntityEquipmentSlot[] { EntityEquipmentSlot.MAINHAND });
    }

}
