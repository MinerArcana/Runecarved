package com.minerarcana.runecarved.item.tool;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.teamacronymcoders.base.items.ItemBase;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMagicTool extends ItemBase {

    public int ticksExisted = 0;
    public static final int expiryTicks = 2400;

    public ItemMagicTool() {
        super("magic_tool");
        this.setMaxStackSize(1);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        MagicToolHelper.convertToTool(stack, "sword", player);
        return false;
    }

    @Override
    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, @Nonnull EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);
        if (Items.IRON_HOE.onItemRightClick(worldIn, player, hand) == new ActionResult<>(EnumActionResult.SUCCESS,
                stack)) {
            MagicToolHelper.convertToTool(stack, "hoe", player);
            return new ActionResult<>(EnumActionResult.SUCCESS, stack);
        }
        return new ActionResult<>(EnumActionResult.PASS, stack);
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
}
