package com.minerarcana.runecarved.tileentity;

import java.util.ArrayList;

import com.google.common.collect.Lists;
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
import net.minecraftforge.items.ItemStackHandler;

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

        protected ArrayList<Spell> spells = Lists.newArrayList();

        public ItemHandlerRunic(int i) {
            super(i);
        }

        protected void onContentsChanged(int slot) {
            ItemStack stack = this.getStackInSlot(slot);
            if (stack.getItem() instanceof ItemRunestone) {
                String runeName = stack.getUnlocalizedName().split("\\.")[1];
                // TODO Removal
                spells.add(RunecarvedAPI.getInstance().getSpellRegistry()
                        .getSpell(new ResourceLocation(Runecarved.MODID, runeName)));
            }
        }

        public ArrayList<Spell> getContainedSpells() {
            return spells;
        }
    }
}
