package com.minerarcana.runecarved.tileentity;

import java.util.ArrayList;

import com.google.common.collect.Lists;
import com.minerarcana.runecarved.api.RunecarvedAPI;
import com.minerarcana.runecarved.api.spell.Spell;
import com.minerarcana.runecarved.container.ContainerRuneworkTable;
import com.minerarcana.runecarved.gui.GuiRuneworkTable;
import com.teamacronymcoders.base.guisystem.IHasGui;
import com.teamacronymcoders.base.tileentities.TileEntityInventoryBase;

import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.nbt.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileEntityRuneworkTable extends TileEntityInventoryBase implements IHasGui {

    public TileEntityRuneworkTable() {
        super(5);
    }

    ArrayList<Spell> knownRunes = Lists.newArrayList();
    Spell[] selectedRunes;

    @Override
    protected void readFromDisk(NBTTagCompound data) {
        NBTTagList spells = data.getTagList("spells", data.getInteger("type"));
        spells.forEach(tag -> knownRunes.add(RunecarvedAPI.getInstance().getSpellRegistry()
                .getSpell(new ResourceLocation(((NBTTagString) tag).getString()))));
    }

    @Override
    protected NBTTagCompound writeToDisk(NBTTagCompound data) {
        NBTTagList spells = new NBTTagList();
        knownRunes.forEach(spell -> spells.appendTag(new NBTTagString(spell.getRegistryName().toString())));
        data.setTag("spells", spells);
        data.setInteger("type", spells.getTagType());
        return data;
    }

    @Override
    public Gui getGui(EntityPlayer entityPlayer, World world, BlockPos blockPos) {
        // TODO Auto-generated method stub
        return new GuiRuneworkTable(new ContainerRuneworkTable(entityPlayer, world, this));
    }

    @Override
    public Container getContainer(EntityPlayer entityPlayer, World world, BlockPos blockPos) {
        // TODO Auto-generated method stub
        return new ContainerRuneworkTable(entityPlayer, world, this);
    }
}
