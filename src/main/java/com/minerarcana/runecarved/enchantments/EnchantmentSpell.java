package com.minerarcana.runecarved.enchantments;

import com.minerarcana.runecarved.RunecarvedContent;
import com.minerarcana.runecarved.api.spell.Spell;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.common.util.EnumHelper;

public class EnchantmentSpell extends Enchantment {

    public static final EnumEnchantmentType SPELL_TYPE = EnumHelper.addEnchantmentType("spells",
            item -> item == RunecarvedContent.scroll);
    private Spell spell;

    public EnchantmentSpell(Spell spell) {
        super(Enchantment.Rarity.RARE, SPELL_TYPE, new EntityEquipmentSlot[] { EntityEquipmentSlot.MAINHAND });
        this.spell = spell;
    }

    @Override
    protected boolean canApplyTogether(Enchantment ench) {
        return false;
    }

    @Override
    public boolean isAllowedOnBooks() {
        return false;
    }

    @Override
    public String getTranslatedName(int level) {
        String rawTooltip = I18n.translateToLocal("spell." + this.spell.getRegistryName().getPath());
        String[] splitTooltip = rawTooltip.split("/");
        return splitTooltip[1];
    }

    public Spell getEnchantmentSpell() {
        return spell;
    }

}
