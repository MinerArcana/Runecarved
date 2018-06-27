package com.minerarcana.runecarved.api.spell;

import javax.annotation.Nonnull;

import com.minerarcana.runecarved.api.caster.ICaster;

import net.minecraft.util.ResourceLocation;

public abstract class Spell implements Comparable<Spell> {
	private ResourceLocation name;

	public Spell(@Nonnull ResourceLocation name) {
		this.name = name;
	}

	public abstract void cast(ICaster caster);

	@Nonnull
	public ResourceLocation getRegistryName() {
		return name;
	}

	@Override
	public int compareTo(Spell compare) {
		return getRegistryName().compareTo(compare.getRegistryName());
	}

	@Override
	public boolean equals(Object compare) {
		return getRegistryName().equals(((Spell) compare).getRegistryName());
	}
}
