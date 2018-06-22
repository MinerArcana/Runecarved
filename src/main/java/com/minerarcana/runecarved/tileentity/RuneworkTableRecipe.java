package com.minerarcana.runecarved.tileentity;

import java.util.ArrayList;

import org.apache.commons.lang3.ArrayUtils;

import com.google.common.collect.Lists;
import com.minerarcana.runecarved.api.spell.Spell;

import net.minecraft.item.ItemStack;

public class RuneworkTableRecipe {

    ItemStack[] inputs;
    Spell[] spells;
    ItemStack output;

    public RuneworkTableRecipe(ItemStack[] inputs, ItemStack output, Spell... requiredSpells) {
        this.inputs = inputs;
        this.spells = requiredSpells;
        this.output = output;
    }

    public ItemStack[] getInputs() {
        return inputs;
    }

    public Spell[] getSpells() {
        return spells;
    }

    public ItemStack getOutput() {
        return output;
    }

    private static ArrayList<RuneworkTableRecipe> recipeList = Lists.newArrayList();

    public static ArrayList<RuneworkTableRecipe> getRecipeList() {
        return recipeList;
    }

    public static void addRecipe(ItemStack[] inputs, ItemStack output, Spell... requiredSpells) {
        if (ArrayUtils.isNotEmpty(inputs) && output != null && !output.isEmpty()) {
            recipeList.add(new RuneworkTableRecipe(inputs, output, requiredSpells));
        }
    }

}
