package com.minerarcana.runecarved.item.tool.manifested;

import com.minerarcana.runecarved.item.tool.MagicToolHelper;
import com.teamacronymcoders.base.items.ItemBase;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class ItemMagicTool extends ItemBase implements IManifestedTool {

    public static final int expiryTicks = 2400;
    public int ticksExisted = 0;

    public ItemMagicTool() {
        super("magic_tool");
        this.setMaxStackSize(1);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        MagicToolHelper.hitEntity(stack, target, attacker);
        return super.hitEntity(stack, target, attacker);
    }

    @Override
    @Nonnull
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
                                      EnumFacing facing, float hitX, float hitY, float hitZ) {
        MagicToolHelper.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
        return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
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
        if (ticksExisted >= expiryTicks) {
            stack.shrink(1);
        }
    }
}
