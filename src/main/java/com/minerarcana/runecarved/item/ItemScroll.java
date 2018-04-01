package com.minerarcana.runecarved.item;

import com.minerarcana.runecarved.enchantments.EnchantmentSpell;
import com.teamacronymcoders.base.items.ItemBase;

import net.minecraft.item.ItemStack;

public class ItemScroll extends ItemBase {

    public ItemScroll() {
        super("scroll");
        this.setMaxStackSize(1);
        this.setMaxDamage(8);
    }

    @Override
    public int getItemEnchantability() {
        return ToolMaterial.GOLD.getEnchantability();
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, net.minecraft.enchantment.Enchantment enchantment) {
        return enchantment.type.equals(EnchantmentSpell.SPELL);
    }
}
