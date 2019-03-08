package com.minerarcana.runecarved.tileentity;

import com.minerarcana.runecarved.api.RunecarvedAPI;
import com.minerarcana.runecarved.api.spell.Spell;
import com.teamacronymcoders.base.tileentities.TileEntityBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

public class TileEntityRunestone extends TileEntityBase {

    public Spell spell;

    @Override
    protected void readFromDisk(NBTTagCompound data) {
        spell = RunecarvedAPI.getInstance().getSpellRegistry().getSpell(new ResourceLocation(data.getString("spell")));
    }

    @Override
    protected NBTTagCompound writeToDisk(NBTTagCompound data) {
        if (spell != null)
            data.setString("spell", spell.getRegistryName().toString());
        return data;
    }

    @Override
    public boolean hasFastRenderer() {
        return true;
    }
}
