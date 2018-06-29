package com.minerarcana.runecarved.tileentity;

import java.util.HashMap;

import com.google.common.collect.Maps;
import com.minerarcana.runecarved.util.IngredientSpell;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

public class RecipeMeldingAltar {
	// For network purposes TODO use string, possibly autogen
	final int id;
	final Ingredient[] inputs;
	final ItemStack output;
	final IngredientSpell[] requiredRunes;

	public RecipeMeldingAltar(int id, Ingredient[] inputs, ItemStack output, IngredientSpell... requiredRunes) {
		this.id = id;
		this.inputs = inputs;
		this.output = output;
		this.requiredRunes = requiredRunes;
	}

	public ItemStack getOutput() {
		return output;
	}

	public static HashMap<Integer, RecipeMeldingAltar> getRecipeList() {
		return recipeList;
	}

	public static void addRecipe(RecipeMeldingAltar r) {
		recipeList.put(r.id, r);
	}

	private static HashMap<Integer, RecipeMeldingAltar> recipeList = Maps.newHashMap();

	public IngredientSpell[] getRequiredSpells() {
		return requiredRunes;
	}

}
