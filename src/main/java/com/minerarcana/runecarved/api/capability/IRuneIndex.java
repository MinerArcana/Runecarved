package com.minerarcana.runecarved.api.capability;

import javax.annotation.Nullable;

import com.minerarcana.runecarved.api.runestack.RuneStack;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public interface IRuneIndex extends INBTSerializable<NBTTagCompound> {
	int getSlots();
	
	@Nullable
	RuneStack getStackInSlot(int slot);
	
	void setStackInSlot(int slot, RuneStack stack);
}
