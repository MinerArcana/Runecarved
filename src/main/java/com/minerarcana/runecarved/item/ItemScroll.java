package com.minerarcana.runecarved.item;

import com.minerarcana.runecarved.api.caster.CasterEntityPlayer;
import com.minerarcana.runecarved.enchantments.EnchantmentSpell;
import com.teamacronymcoders.base.items.ItemBase;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public class ItemScroll extends ItemBase {

    public ItemScroll() {
        super("scroll");
        this.setMaxStackSize(1);
        this.setMaxDamage(8);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        if (stack.isItemEnchanted()) {
            for (Enchantment enchantment : EnchantmentHelper.getEnchantments(stack).keySet()) {
                if (enchantment instanceof EnchantmentSpell) {
                    EnchantmentSpell spellEnchantment = (EnchantmentSpell) enchantment;
                    spellEnchantment.getEnchantmentSpell().cast(new CasterEntityPlayer(playerIn));
                    // TODO
                    if (stack.getItemDamage() == 1) {
                        worldIn.spawnParticle(EnumParticleTypes.FLAME, playerIn.getPosition().getX() + 0.5F,
                                playerIn.getPosition().getY(), playerIn.getPosition().getZ() + 0.5F, 0, 0, 0);
                        for (int i = 0; i < 8; i++) {
                            if (worldIn.rand.nextBoolean())
                                worldIn.spawnParticle(EnumParticleTypes.FLAME, playerIn.getPosition().getX() + 0.5F,
                                        playerIn.getPosition().getY(), playerIn.getPosition().getZ() + 0.5F, 0, 0, 0);
                        }
                    }
                    stack.damageItem(1, playerIn);
                    return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
                }
            }
        }
        return new ActionResult<ItemStack>(EnumActionResult.PASS, stack);
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        if (stack.isItemEnchanted()) {
            for (Enchantment enchantment : EnchantmentHelper.getEnchantments(stack).keySet()) {
                if (enchantment instanceof EnchantmentSpell) {
                    EnchantmentSpell spellEnchantment = (EnchantmentSpell) enchantment;
                    return I18n
                            .translateToLocal("spell."
                                    + spellEnchantment.getEnchantmentSpell().getRegistryName().getResourcePath())
                            .split("/")[0] + " " + I18n.translateToLocal("item.runecarved.scroll.name");
                }
            }
        }
        return super.getItemStackDisplayName(stack);
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
