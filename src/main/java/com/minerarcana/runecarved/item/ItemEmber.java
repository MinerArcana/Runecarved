package com.minerarcana.runecarved.item;

import com.teamacronymcoders.base.items.ItemBase;
import net.minecraft.item.ItemStack;

public class ItemEmber extends ItemBase {
    public ItemEmber() {
        super("ember");
    }

    @Override
    public int getItemBurnTime(ItemStack itemStack) {
        return 200;
    }
}
