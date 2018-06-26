package com.minerarcana.runecarved.tileentity;

import java.util.*;
import java.util.stream.IntStream;

import com.google.common.collect.Lists;
import com.minerarcana.runecarved.Runecarved;
import com.minerarcana.runecarved.api.RunecarvedAPI;
import com.minerarcana.runecarved.api.spell.Spell;
import com.minerarcana.runecarved.item.ItemRunestone;
import com.teamacronymcoders.base.util.ItemStackUtils;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class RecipeMeldingAltar {
	final ItemStack[] inputs;
	final ItemStack output;
	final Spell[] requiredRunes;

	public RecipeMeldingAltar(ItemStack[] inputs, ItemStack output, Spell... requiredRunes) {
		this.inputs = inputs;
		this.output = output;
		this.requiredRunes = requiredRunes;
	}

	public ItemStack getOutput() {
		return output;
	}

	public boolean checkItemMatch(List<ItemStack> inventory) {
		for (ItemStack stack : Arrays.asList(inputs)) {
			if (inventory.stream()
					.noneMatch(inventoryStack -> ItemStackUtils.containsItemStack(inventoryStack, stack))) {
				return false;
			}
		}
		return true;
	}

	public boolean checkSpellMatch(TileEntityRuneIndex tile) {
		IItemHandler handler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		ArrayList<Spell> knownSpells = Lists.newArrayList();
		// TODO handler should rather just know what spells each time its changed
		IntStream.range(0, handler.getSlots()).mapToObj(slotIndex -> handler.getStackInSlot(slotIndex))
				.filter(stack -> stack.getItem() instanceof ItemRunestone)
				.forEach(stack -> knownSpells.add(RunecarvedAPI.getInstance().getSpellRegistry()
						.getSpell(new ResourceLocation(Runecarved.MODID, stack.getUnlocalizedName().split("\\.")[2]))));
		for (Spell requiredSpell : requiredRunes) {
			if (knownSpells.stream().noneMatch(known -> known.compareTo(requiredSpell) == 0)) {
				return false;
			}
		}
		return true;
	}

	public static ArrayList<RecipeMeldingAltar> getRecipeList() {
		return recipeList;
	}

	public static void addRecipe(RecipeMeldingAltar r) {
		recipeList.add(r);
	}

	private static ArrayList<RecipeMeldingAltar> recipeList = Lists.newArrayList();

}
