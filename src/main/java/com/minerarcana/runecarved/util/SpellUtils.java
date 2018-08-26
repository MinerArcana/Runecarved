package com.minerarcana.runecarved.util;

import com.minerarcana.runecarved.api.spell.Spell;
import com.minerarcana.runecarved.enchantments.EnchantmentSpell;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public class SpellUtils {

	@SuppressWarnings("deprecation")
	public static String getDisplayNameFromEnchantedStack(ItemStack stack) {
		if (stack.isItemEnchanted()) {
			for (Enchantment enchantment : EnchantmentHelper.getEnchantments(stack).keySet()) {
				if (enchantment instanceof EnchantmentSpell) {
					EnchantmentSpell spellEnchantment = (EnchantmentSpell) enchantment;
					return I18n
							.translateToLocal("spell."
									+ spellEnchantment.getEnchantmentSpell().getRegistryName().getPath())
							.split("/")[0] + " " + I18n.translateToLocal("item.runecarved.scroll.name");
				}
			}
		}
		return null;
	}

	public static Spell getSpellFromStack(ItemStack stack) {
		if (stack.isItemEnchanted()) {
			for (Enchantment enchantment : EnchantmentHelper.getEnchantments(stack).keySet()) {
				if (enchantment instanceof EnchantmentSpell) {
					EnchantmentSpell spellEnchantment = (EnchantmentSpell) enchantment;
					return spellEnchantment.getEnchantmentSpell();
				}
			}
		}
		return null;
	}

	public static void onSpellCast(World worldIn, EntityPlayer playerIn, ItemStack stack) {
		if (stack.getItemDamage() == 1) {
			worldIn.spawnParticle(EnumParticleTypes.FLAME, playerIn.getPosition().getX() + 0.5F,
					playerIn.getPosition().getY(), playerIn.getPosition().getZ() + 0.5F, 0, 0, 0);
			for (int i = 0; i < 8; i++) {
				if (worldIn.rand.nextBoolean())
					worldIn.spawnParticle(EnumParticleTypes.FLAME, playerIn.getPosition().getX() + 0.5F,
							playerIn.getPosition().getY(), playerIn.getPosition().getZ() + 0.5F, 0, 0, 0);
			}
		}
		if (!playerIn.capabilities.isCreativeMode) {
			stack.damageItem(1, playerIn);
		}
	}

}
