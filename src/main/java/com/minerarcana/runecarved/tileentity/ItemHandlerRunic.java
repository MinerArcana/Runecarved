package com.minerarcana.runecarved.tileentity;

import java.util.HashMap;
import java.util.Optional;

import com.google.common.collect.Maps;
import com.minerarcana.runecarved.api.capability.IRuneIndex;
import com.minerarcana.runecarved.api.capability.RuneIndexImpl;
import com.minerarcana.runecarved.api.runestack.RuneStack;
import com.minerarcana.runecarved.api.spell.Spell;
import com.minerarcana.runecarved.item.ItemRunestone;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.items.IItemHandlerModifiable;

//Wrapper for IRuneIndex to expose inventory to automation
public class ItemHandlerRunic implements IItemHandlerModifiable, INBTSerializable<NBTTagCompound> {

	IRuneIndex index;
	@Deprecated
    protected HashMap<Spell, Integer> spells = Maps.newHashMap();

    public ItemHandlerRunic(int size) {
        super();
        this.index = new RuneIndexImpl(size);
    }
    
    public IRuneIndex getIndex() {
    	return index;
    }

    @Deprecated
    public HashMap<Spell, Integer> getContainedSpells() {
        return spells;
    }
    
    @Override
    public NBTTagCompound serializeNBT()
    {
    	return index.serializeNBT();
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt)
    {
    	index.deserializeNBT(nbt.getCompoundTag("index"));
    }

	@Override
	public int getSlots() {
		return index.getSlots();
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		RuneStack rStack = index.getStackInSlot(slot);
		if(rStack.isEmpty()) {
			return ItemStack.EMPTY;
		}
		return ItemRunestone.getRunestoneItemStackForSpell(rStack.getSpell(), rStack.getSize());
	}

	@Override
	public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
			RuneStack rStack = RuneStack.convertToRuneStack(stack);
			//TODO allow combining
			if(!rStack.isEmpty() && this.index.getStackInSlot(slot) == null) {
				this.index.setStackInSlot(slot, rStack);
				return ItemStack.EMPTY;
			}
		return stack;
	}

	@Override
	public ItemStack extractItem(int slot, int amount, boolean simulate) {
		if(this.index.getStackInSlot(slot) != null) {
			RuneStack inSlot = index.getStackInSlot(slot);
			if(inSlot.getSize() >= amount) {
				inSlot.decreaseSize(amount);
				return ItemRunestone.getRunestoneItemStackForSpell(inSlot.getSpell(), amount);
			}
		}
		return ItemStack.EMPTY;
	}

	@Override
	public int getSlotLimit(int slot) {
		//TODO
		return 64;
	}

	@Override
	public void setStackInSlot(int slot, ItemStack stack) {
		RuneStack rStack = RuneStack.convertToRuneStack(stack);
		if(!rStack.isEmpty()) {
			this.index.setStackInSlot(slot, rStack);
		}
	}
}