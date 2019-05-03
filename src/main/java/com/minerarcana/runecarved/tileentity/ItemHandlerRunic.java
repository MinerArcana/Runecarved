package com.minerarcana.runecarved.tileentity;

import java.util.HashMap;

import com.google.common.collect.Maps;
import com.minerarcana.runecarved.api.capability.IRuneIndex;
import com.minerarcana.runecarved.api.capability.RuneIndexImpl;
import com.minerarcana.runecarved.api.runestack.RuneStack;
import com.minerarcana.runecarved.api.spell.Spell;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.items.ItemStackHandler;

//Wrapper for IRuneIndex to expose inventory to automation
public class ItemHandlerRunic extends ItemStackHandler {

	IRuneIndex index;
	@Deprecated
    protected HashMap<Spell, Integer> spells = Maps.newHashMap();

    public ItemHandlerRunic(int size) {
        super(size);
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
    	NBTTagCompound tag = super.serializeNBT();
    	tag.setTag("index", this.index.serializeNBT());
    	return tag;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt)
    {
    	index.deserializeNBT(nbt.getCompoundTag("index"));
    	super.deserializeNBT(nbt);
    }

	@Override
	public void onContentsChanged(int slot) {
		if(this.getStackInSlot(slot).isEmpty()) {
			this.index.setStackInSlot(slot, RuneStack.EMPTY);
		}
		else {
			this.index.setStackInSlot(slot, RuneStack.convertToRuneStack(this.getStackInSlot(slot)));
		}
	}
}