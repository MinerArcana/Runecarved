package com.minerarcana.runecarved.item.tool;

import com.minerarcana.runecarved.RunecarvedContent;
import com.teamacronymcoders.base.items.ItemBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;

public class ItemRunepick extends ItemBase {

    public ItemRunepick() {
        super("runepick");
        this.setMaxStackSize(1);
    }

    @Override
    public float getDestroySpeed(ItemStack stack, IBlockState state) {
        return state.getBlock() == RunecarvedContent.runestoneBlock ? 5.0F : 0F;
    }

    @Override
    public boolean canHarvestBlock(IBlockState state) {
        return state.getBlock() == RunecarvedContent.runestoneBlock;
    }

}
