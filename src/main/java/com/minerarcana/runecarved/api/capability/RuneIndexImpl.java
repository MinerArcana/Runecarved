package com.minerarcana.runecarved.api.capability;

import com.minerarcana.runecarved.api.runestack.RuneStack;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.util.Constants;

public class RuneIndexImpl implements IRuneIndex {

    protected NonNullList<RuneStack> stacks;
    
    public RuneIndexImpl(int size) {
    	this.stacks = NonNullList.withSize(size, RuneStack.EMPTY);
    }
	
	@Override
	public int getSlots() {
		return stacks.size();
	}

	@Override
	public NBTTagCompound serializeNBT() {
        NBTTagList nbtTagList = new NBTTagList();
        for (int i = 0; i < stacks.size(); i++)
        {
            if (stacks.get(i) != null)
            {
                NBTTagCompound itemTag = new NBTTagCompound();
                itemTag.setInteger("Slot", i);
                stacks.get(i).writeToNBT(itemTag);
                nbtTagList.appendTag(itemTag);
            }
        }
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setTag("Stacks", nbtTagList);
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt)
    {
        NBTTagList tagList = nbt.getTagList("Stacks", Constants.NBT.TAG_COMPOUND);
        for (int i = 0; i < tagList.tagCount(); i++)
        {
            NBTTagCompound itemTags = tagList.getCompoundTagAt(i);
            int slot = itemTags.getInteger("Slot");

            if (slot >= 0 && slot < stacks.size())
            {
                stacks.set(slot, RuneStack.readFromNBT(itemTags));
            }
        }
    }   
    
	@Override
	public RuneStack getStackInSlot(int slot) {
		if(stacks.size() < slot) {
			return null;
		}
		return stacks.get(slot);
	}

	@Override
	public void setStackInSlot(int slot, RuneStack stack) {
		stacks.set(slot, stack);
	}
}
