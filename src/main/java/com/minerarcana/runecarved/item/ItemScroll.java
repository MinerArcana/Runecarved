package com.minerarcana.runecarved.item;

import java.util.Map;

import com.google.common.collect.Maps;
import com.minerarcana.runecarved.api.caster.CasterEntityPlayer;
import com.minerarcana.runecarved.api.spell.EntityInteractionSpell;
import com.minerarcana.runecarved.api.spell.ExtendedSpell;
import com.minerarcana.runecarved.enchantments.EnchantmentSpell;
import com.minerarcana.runecarved.util.SpellUtils;
import com.teamacronymcoders.base.items.ItemBase;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
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
		if (SpellUtils.getSpellFromStack(stack) != null) {
			if (SpellUtils.getSpellFromStack(stack) instanceof ExtendedSpell) {
				playerIn.setActiveHand(handIn);
			} else if (SpellUtils.getSpellFromStack(stack) instanceof EntityInteractionSpell) {
				((EntityInteractionSpell) SpellUtils.getSpellFromStack(stack)).cast(new CasterEntityPlayer(playerIn),
						stack);
			} else {
				SpellUtils.getSpellFromStack(stack).cast(new CasterEntityPlayer(playerIn));
				// TODO
				SpellUtils.onSpellCast(worldIn, playerIn, stack);
			}
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase entity,
			EnumHand hand) {
		if (SpellUtils.getSpellFromStack(stack) instanceof EntityInteractionSpell) {
			((EntityInteractionSpell) SpellUtils.getSpellFromStack(stack)).castOnEntity(new CasterEntityPlayer(player),
					stack, entity);
			return true;
		}
		return false;
	}

	// FIXME Possibly unsafe casts
	@Override
	public void onUsingTick(ItemStack stack, EntityLivingBase player, int count) {
		((ExtendedSpell) SpellUtils.getSpellFromStack(stack))
				.duringCasting(new CasterEntityPlayer((EntityPlayer) player));
		super.onUsingTick(stack, player, count);
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase player, int timeLeft) {
		SpellUtils.onSpellCast(world, (EntityPlayer) player, stack);
		super.onPlayerStoppedUsing(stack, world, player, timeLeft);
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
		SpellUtils.getSpellFromStack(stack).cast(new CasterEntityPlayer((EntityPlayer) entityLiving));
		SpellUtils.onSpellCast(worldIn, ((EntityPlayer) entityLiving), stack);
		return super.onItemUseFinish(stack, worldIn, entityLiving);
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		if (SpellUtils.getSpellFromStack(stack) != null
				&& SpellUtils.getSpellFromStack(stack) instanceof ExtendedSpell) {
			return ((ExtendedSpell) SpellUtils.getSpellFromStack(stack)).getCastDuration();
		}
		return super.getMaxItemUseDuration(stack);
	}

	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		String displayName = SpellUtils.getDisplayNameFromEnchantedStack(stack);
		return displayName != null ? displayName : super.getItemStackDisplayName(stack);
	}

	@Override
	public int getItemEnchantability() {
		return ToolMaterial.GOLD.getEnchantability();
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, net.minecraft.enchantment.Enchantment enchantment) {
		return enchantment.type.equals(EnchantmentSpell.SPELL_TYPE);
	}
}
