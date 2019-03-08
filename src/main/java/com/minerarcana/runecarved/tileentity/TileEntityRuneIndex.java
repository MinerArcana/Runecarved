package com.minerarcana.runecarved.tileentity;

import com.google.common.collect.Maps;
import com.minerarcana.runecarved.Runecarved;
import com.minerarcana.runecarved.api.RunecarvedAPI;
import com.minerarcana.runecarved.api.spell.Spell;
import com.minerarcana.runecarved.container.ContainerRuneIndex;
import com.minerarcana.runecarved.gui.GuiRuneIndex;
import com.minerarcana.runecarved.item.ItemRunestone;
import com.teamacronymcoders.base.guisystem.IHasGui;
import com.teamacronymcoders.base.tileentities.TileEntityInventoryBase;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import java.util.HashMap;

public class TileEntityRuneIndex extends TileEntityInventoryBase implements IHasGui {

    public TileEntityRuneIndex() {
        super(new ItemHandlerRunic(9));
    }

    @Override
    public Gui getGui(EntityPlayer entityPlayer, World world, BlockPos blockPos) {
        // TODO Auto-generated method stub
        return new GuiRuneIndex(new ContainerRuneIndex(entityPlayer, world, this));
    }

    @Override
    public Container getContainer(EntityPlayer entityPlayer, World world, BlockPos blockPos) {
        // TODO Auto-generated method stub
        return new ContainerRuneIndex(entityPlayer, world, this);
    }

    public static class ItemHandlerRunic extends ItemStackHandler {

        // Store spells and what slots they are in
        protected HashMap<Spell, Integer> spells = Maps.newHashMap();

        public ItemHandlerRunic(int i) {
            super(i);
        }

        // TODO This could be where we ensure it is always synced to client?
        // Build list on load instead of saving/loading to NBT
        @Override
        protected void onLoad() {
            for (int slotIndex = 0; slotIndex < this.getSlots(); slotIndex++) {
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
    }
}
