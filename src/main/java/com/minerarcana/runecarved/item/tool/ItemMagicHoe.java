package com.minerarcana.runecarved.item.tool;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.minerarcana.runecarved.Runecarved;
import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.IModAware;
import com.teamacronymcoders.base.client.models.IHasModel;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMagicHoe extends ItemHoe implements IHasModel, IModAware {

    public int ticksExisted = 0;
    public static final int expiryTicks = 2400;
    boolean creativeTabSet = false;
    private IBaseMod mod;
    String name;

    public ItemMagicHoe(String name) {
        super(Runecarved.MAGIC_TOOL);
        this.setUnlocalizedName(name);
        this.name = name;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add("Seconds remaining: " + (expiryTicks - ticksExisted) / 20);
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        this.ticksExisted++;
        if (ticksExisted == expiryTicks) {
            stack.shrink(1);
        }
    }

    @Override
    public List<String> getModelNames(List<String> modelNames) {
        modelNames.add(name);
        return modelNames;
    }

    @Override
    @Nonnull
    public Item setCreativeTab(@Nonnull CreativeTabs tab) {
        if (!creativeTabSet) {
            super.setCreativeTab(tab);
            this.creativeTabSet = true;
        }
        return this;
    }

    @Override
    public IBaseMod getMod() {
        return mod;
    }

    @Override
    public void setMod(IBaseMod mod) {
        this.mod = mod;
    }

    @Override
    public Item getItem() {
        return this;
    }

}
