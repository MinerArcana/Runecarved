package com.minerarcana.runecarved.tileentity;

import java.util.HashMap;

import javax.annotation.Nonnull;

import com.google.common.collect.Maps;
import com.minerarcana.runecarved.Runecarved;
import com.minerarcana.runecarved.api.RunecarvedAPI;
import com.minerarcana.runecarved.api.capability.IRuneIndex;
import com.minerarcana.runecarved.api.capability.RuneIndexImpl;
import com.minerarcana.runecarved.api.runestack.RuneStack;
import com.minerarcana.runecarved.api.spell.Spell;
import com.minerarcana.runecarved.item.ItemRunestone;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;

public class ItemHandlerRunic extends ItemStackHandler {

	IRuneIndex index;
    // Store spells and what slots they are in TODO Fallback to index instead
	@Deprecated
    protected HashMap<Spell, Integer> spells = Maps.newHashMap();

    public ItemHandlerRunic(int size) {
        super(size);
        this.index = new RuneIndexImpl(9);
    }
    
    public IRuneIndex getIndex() {
    	return index;
    }

    // TODO This could be where we ensure it is always synced to client?
    // Build list on load instead of saving/loading to NBT
    @Override
    protected void onLoad() {
    	setSize(9);//TODO
    	for (int slotIndex = 0; slotIndex < 9; slotIndex++) {
    		//Build inventory from index storage
    		RuneStack rStack = this.index.getStackInSlot(slotIndex);
    		this.setStackInSlot(slotIndex, ItemRunestone.getRunestoneItemStackForSpell(rStack.getSpell(), rStack.getSize()));
    		//Build lookup map
            ItemStack stack = this.getStackInSlot(slotIndex);
            if (stack.getItem() instanceof ItemRunestone) {
                String runeName = stack.getTranslationKey().split("\\.")[2];
                spells.put(RunecarvedAPI.getInstance().getSpellRegistry()
                        .getSpell(new ResourceLocation(Runecarved.MODID, runeName)), slotIndex);
            }
        }
    }

    @Override
    protected void onContentsChanged(int slotIndex) {
        ItemStack stack = this.getStackInSlot(slotIndex);
        if (stack.getItem() instanceof ItemRunestone) {
            String runeName = stack.getTranslationKey().split("\\.")[2];
            spells.put(RunecarvedAPI.getInstance().getSpellRegistry()
                    .getSpell(new ResourceLocation(Runecarved.MODID, runeName)), slotIndex);
        }
    }

    // Necessary because onContentsChanged is normally called after the stack is
    // already removed
    @Override
    @Nonnull
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        if (amount == 0)
            return ItemStack.EMPTY;

        validateSlotIndex(slot);

        ItemStack existing = this.stacks.get(slot);

        if (existing.isEmpty())
            return ItemStack.EMPTY;

        int toExtract = Math.min(amount, existing.getMaxStackSize());

        if (existing.getCount() <= toExtract) {
            if (!simulate) {
                String runeName = existing.getTranslationKey().split("\\.")[2];
                this.spells.remove(RunecarvedAPI.getInstance().getSpellRegistry()
                        .getSpell(new ResourceLocation(Runecarved.MODID, runeName)));
                this.stacks.set(slot, ItemStack.EMPTY);
                onContentsChanged(slot);
            }
            return existing;
        } else {
            if (!simulate) {
                this.stacks.set(slot,
                        ItemHandlerHelper.copyStackWithSize(existing, existing.getCount() - toExtract));
                String runeName = existing.getTranslationKey().split("\\.")[2];
                this.spells.remove(RunecarvedAPI.getInstance().getSpellRegistry()
                        .getSpell(new ResourceLocation(Runecarved.MODID, runeName)));
                onContentsChanged(slot);
            }

            return ItemHandlerHelper.copyStackWithSize(existing, toExtract);
        }
    }

    public HashMap<Spell, Integer> getContainedSpells() {
        return spells;
    }
    
    @Override
    public NBTTagCompound serializeNBT()
    {
    	NBTTagCompound tag = super.serializeNBT();
    	tag.setTag("index", index.serializeNBT());
    	return tag;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt)
    {
    	index.deserializeNBT(nbt.getCompoundTag("index"));
    	super.deserializeNBT(nbt);
    }
}