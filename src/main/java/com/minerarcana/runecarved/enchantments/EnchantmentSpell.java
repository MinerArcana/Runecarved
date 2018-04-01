package com.minerarcana.runecarved.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EntityEquipmentSlot;

public class EnchantmentSpell extends Enchantment {

    public EnchantmentSpell() {
        super(Enchantment.Rarity.RARE, EnchantmentHandler.SPELL,
                new EntityEquipmentSlot[] { EntityEquipmentSlot.MAINHAND });
    }

}
