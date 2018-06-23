package com.minerarcana.runecarved.item;

import java.util.Map;

import com.google.common.collect.Maps;
import com.minerarcana.runecarved.Runecarved;
import com.minerarcana.runecarved.api.caster.CasterEntityPlayer;
import com.minerarcana.runecarved.api.spell.*;
import com.minerarcana.runecarved.enchantments.EnchantmentSpell;
import com.teamacronymcoders.base.items.ItemBase;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
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
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (tab == CreativeTabs.SEARCH || tab == this.getCreativeTab()) {
            items.add(new ItemStack(this));
            for (Enchantment enchantment : Enchantment.REGISTRY) {
                if (enchantment.type.equals(EnchantmentSpell.SPELL_TYPE)) {
                    ItemStack stack = new ItemStack(this);
                    Map<Enchantment, Integer> map = Maps.<Enchantment, Integer>newLinkedHashMap();
                    map.put(enchantment, Enchantment.getEnchantmentID(enchantment));
                    EnchantmentHelper.setEnchantments(map, stack);
                    items.add(stack);
                }
            }
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        if (getSpellFromStack(stack) != null) {
            if (getSpellFromStack(stack) instanceof ExtendedSpell) {
                playerIn.setActiveHand(handIn);
            } else if (getSpellFromStack(stack) instanceof EntityInteractionSpell) {
                ((EntityInteractionSpell) getSpellFromStack(stack)).cast(new CasterEntityPlayer(playerIn), stack);
            } else {
                getSpellFromStack(stack).cast(new CasterEntityPlayer(playerIn));
                // TODO
                onSpellCast(worldIn, playerIn, stack);
            }
            return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase entity,
            EnumHand hand) {
        if (getSpellFromStack(stack) instanceof EntityInteractionSpell) {
            ((EntityInteractionSpell) getSpellFromStack(stack)).castOnEntity(new CasterEntityPlayer(player), stack,
                    entity);
            return true;
        }
        return false;
    }

    private void onSpellCast(World worldIn, EntityPlayer playerIn, ItemStack stack) {
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

    // FIXME Possibly unsafe casts
    @Override
    public void onUsingTick(ItemStack stack, EntityLivingBase player, int count) {
        ((ExtendedSpell) getSpellFromStack(stack)).duringCasting(new CasterEntityPlayer((EntityPlayer) player));
        super.onUsingTick(stack, player, count);
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase player, int timeLeft) {
        onSpellCast(world, (EntityPlayer) player, stack);
        super.onPlayerStoppedUsing(stack, world, player, timeLeft);
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        getSpellFromStack(stack).cast(new CasterEntityPlayer((EntityPlayer) entityLiving));
        onSpellCast(worldIn, ((EntityPlayer) entityLiving), stack);
        return super.onItemUseFinish(stack, worldIn, entityLiving);
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        if (getSpellFromStack(stack) != null && getSpellFromStack(stack) instanceof ExtendedSpell) {
            Runecarved.instance.getLogger().devInfo("" + ((ExtendedSpell) getSpellFromStack(stack)).getCastDuration());
            return ((ExtendedSpell) getSpellFromStack(stack)).getCastDuration();
        }
        return super.getMaxItemUseDuration(stack);
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
        return enchantment.type.equals(EnchantmentSpell.SPELL_TYPE);
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
}
