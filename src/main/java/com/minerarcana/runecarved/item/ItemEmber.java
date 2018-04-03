package com.minerarcana.runecarved.item;

import java.util.List;

import javax.annotation.Nullable;

import com.teamacronymcoders.base.items.ItemBase;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemEmber extends ItemBase {
    public ItemEmber() {
        super("ember");
    }

    @Override
    public boolean hasCustomEntity(ItemStack stack) {
        return true;
    }

    @Override
    @Nullable
    // Feeding the item entity back to itself means the vanilla handling destroys it
    public Entity createEntity(World world, Entity location, ItemStack itemstack) {
        return location;
    }

    @Override
    public int getItemBurnTime(ItemStack itemStack) {
        return 200;
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        entityIn.attackEntityFrom(DamageSource.ON_FIRE, 10f);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFormatting.RED.toString() + I18n.translateToLocal("item.runecarved.ember.desc"));
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.EPIC;
    }

}
