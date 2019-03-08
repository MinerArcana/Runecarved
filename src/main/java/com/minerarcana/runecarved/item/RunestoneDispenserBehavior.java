package com.minerarcana.runecarved.item;

import com.minerarcana.runecarved.api.caster.CasterTileEntity;
import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.item.ItemStack;

public class RunestoneDispenserBehavior implements IBehaviorDispenseItem {

    @Override
    public ItemStack dispense(IBlockSource source, ItemStack stack) {
        ItemRunestone stone = (ItemRunestone) stack.getItem();
        stone.getSpell().cast(new CasterTileEntity(source.getBlockTileEntity(),
                source.getBlockState().getValue(BlockDispenser.FACING)));
        stack.shrink(1);
        return stack;
    }

}
