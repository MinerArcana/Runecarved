package com.minerarcana.runecarved.tileentity;

import com.google.common.base.Predicate;
import com.minerarcana.runecarved.api.spell.Spell;

//We could implement Predicate on Spell directly, but I am using a wrapper class for consistency
public class IngredientSpell implements Predicate<Spell> {

	private Spell spell;

	public IngredientSpell(Spell spell) {
		this.spell = spell;
	}

	@Override
	public boolean apply(Spell input) {
		return spell.equals(input);
	}

	public Spell getSpell() {
		return spell;
	}

}
