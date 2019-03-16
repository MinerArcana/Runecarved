package com.minerarcana.runecarved.item;

import com.minerarcana.runecarved.api.caster.CasterTileEntity;
import com.minerarcana.runecarved.api.spell.ISpellItem;
import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class RunestoneDispenserBehavior implements IBehaviorDispenseItem {

    @Override
    @Nonnull
    public ItemStack dispense(@Nonnull IBlockSource source, @Nonnull ItemStack stack) {
        ISpellItem stone = (ISpellItem) stack.getItem();
        stone.getSpell().cast(new CasterTileEntity(source.getBlockTileEntity(),
                source.getBlockState().getValue(BlockDispenser.FACING)));
        stack.shrink(1);
        return stack;
    }

}
