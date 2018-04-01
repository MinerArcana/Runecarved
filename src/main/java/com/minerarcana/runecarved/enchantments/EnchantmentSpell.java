package com.minerarcana.runecarved.enchantments;

import com.minerarcana.runecarved.Runecarved;
import com.minerarcana.runecarved.api.spell.Spell;

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
    private Spell spell;

    public EnchantmentSpell(Spell spell) {
        super(Enchantment.Rarity.RARE, SPELL, new EntityEquipmentSlot[] { EntityEquipmentSlot.MAINHAND });
        this.spell = spell;
    }

    @Override
    public String getTranslatedName(int level) {
        return this.spell.getRegistryName().getResourcePath(); // TODO
    }

    public Spell getEnchantmentSpell() {
        return spell;
    }

}
