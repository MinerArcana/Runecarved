package com.minerarcana.runecarved;

import com.minerarcana.runecarved.enchantments.EnchantmentSpell;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class RecipesScrollToStone extends net.minecraftforge.registries.IForgeRegistryEntry.Impl<IRecipe>
        implements IRecipe {

    @Override
    public boolean matches(InventoryCrafting inv, World worldIn) {
        boolean hasScroll = false;
        boolean hasPlate = false;
        for (int i = 0; i < inv.getSizeInventory(); i++) {
            ItemStack itemstack = inv.getStackInSlot(i);
            if (!itemstack.isEmpty()) {
                Item item = itemstack.getItem();

                if (item == RunecarvedContent.scroll && itemstack.isItemEnchanted()) {
                    hasScroll = true;
                    continue;
                } else if (item == Item.getItemFromBlock(Blocks.STONE_PRESSURE_PLATE)) {
                    hasPlate = true;
                    continue;
                }
            }
        }
        return hasScroll && hasPlate;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        for (int i = 0; i < inv.getSizeInventory(); i++) {
            ItemStack itemstack = inv.getStackInSlot(i);
            if (!itemstack.isEmpty()) {
                Item item = itemstack.getItem();

                if (item == RunecarvedContent.scroll) {
                    for (Enchantment enchantment : EnchantmentHelper.getEnchantments(itemstack).keySet()) {
                        if (enchantment instanceof EnchantmentSpell) {
                            EnchantmentSpell spellEnchantment = (EnchantmentSpell) enchantment;
                            return new ItemStack(Item.getByNameOrId("runecarved:runestone."
                                    + spellEnchantment.getEnchantmentSpell().getRegistryName().getResourcePath()));
                        }
                    }

                }
            }
        }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canFit(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getRecipeOutput() {
        // TODO Auto-generated method stub
        return ItemStack.EMPTY;
    }

}
