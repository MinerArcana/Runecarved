package com.minerarcana.runecarved.api.runestack;

import java.util.Optional;

import com.minerarcana.runecarved.api.RunecarvedAPI;
import com.minerarcana.runecarved.api.spell.ISpellItem;
import com.minerarcana.runecarved.api.spell.Spell;
import com.minerarcana.runecarved.item.ItemRunestone;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

public class RuneStack {
	public static final RuneStack EMPTY = new RuneStack((Spell)null, 0);
    private int size;
    private final Spell spell;
    private boolean isEmpty;

    public RuneStack(Spell spell, int size) {
        this.spell = spell;
        this.size = size >= 0 ? size : 0;
        this.updateEmptyState();
    }

    public int getSize() {
        return size;
    }

    public Spell getSpell() {
        return spell;
    }

    public void decreaseSize(int amount) {
        this.size -= amount;
        this.updateEmptyState();
    }

    public void increaseSize(int amount) {
        this.size += amount;
    }

    public ItemStack combineStack(ItemStack itemStack) {
        return Optional.of(convertToRuneStack(itemStack))
                .map(this::combineStack)
                .map(RuneStack::pullItemStack)
                .orElse(itemStack);
    }

    public RuneStack combineStack(RuneStack runeStack) {
        if (runeStack.getSpell() == this.getSpell()) {
            int amountAccepted = Math.max(runeStack.getSize(), Integer.MAX_VALUE - this.getSize());
            runeStack.decreaseSize(amountAccepted);
            this.increaseSize(amountAccepted);
        }
        return runeStack;
    }

    public ItemStack pullItemStack() {
        return pullItemStack(64);
    }

    public ItemStack pullItemStack(int requestedSize) {
        int amountPulled = Math.min(requestedSize, this.getSize());
        this.decreaseSize(amountPulled);
        return ItemRunestone.getRunestoneItemStackForSpell(this.getSpell(), amountPulled);
    }

    public static RuneStack convertToRuneStack(ItemStack itemStack) {
        if (!itemStack.isEmpty()) {
            if (itemStack.getItem() instanceof ISpellItem) {
                return new RuneStack(((ISpellItem) itemStack.getItem()).getSpell(), itemStack.getCount());
            }
        }
        return EMPTY;
    }

	public void writeToNBT(NBTTagCompound tag) {
		if(!this.isEmpty()) {
			tag.setString("Spell", this.spell.getRegistryName().toString());
			tag.setInteger("Count", this.size);
		}
		else {
			tag.setString("Spell", "EMPTY");
		}
	}

	public static RuneStack readFromNBT(NBTTagCompound nbt) {
		//TODO
		if(nbt.getString("Spell") == "EMPTY") {
			return EMPTY;
		}
		return new RuneStack(RunecarvedAPI.getInstance().getSpellRegistry().getSpell(new ResourceLocation(nbt.getString("Spell"))), nbt.getInteger("Count"));
	}
	
	private void updateEmptyState()
	{
		this.isEmpty = this.isEmpty();
	}
	
	public boolean isEmpty() {
		if(this == EMPTY) {
			return true;
		}
		else if(this.getSize() <= 0) {
			return true;
		}
		else if(this.getSpell() == null) {
			return true;
		}
		return false;
    }
}
