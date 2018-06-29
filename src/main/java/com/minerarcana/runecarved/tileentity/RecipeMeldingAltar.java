package com.minerarcana.runecarved.tileentity;

import java.util.ArrayList;

import com.google.common.collect.Lists;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

public class RecipeMeldingAltar {
	final Ingredient[] inputs;
	final ItemStack output;
	final IngredientSpell[] requiredRunes;

	public RecipeMeldingAltar(Ingredient[] inputs, ItemStack output, IngredientSpell... requiredRunes) {
		this.inputs = inputs;
		this.output = output;
		this.requiredRunes = requiredRunes;
	}

	public ItemStack getOutput() {
		return output;
	}

	public static ArrayList<RecipeMeldingAltar> getRecipeList() {
		return recipeList;
	}

	public static void addRecipe(RecipeMeldingAltar r) {
		recipeList.add(r);
	}

	private static ArrayList<RecipeMeldingAltar> recipeList = Lists.newArrayList();

	public IngredientSpell[] getRequiredSpells() {
		return requiredRunes;
	}

}
