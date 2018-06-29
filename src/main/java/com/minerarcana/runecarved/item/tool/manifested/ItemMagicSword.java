package com.minerarcana.runecarved.item.tool.manifested;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.minerarcana.runecarved.Runecarved;
import com.minerarcana.runecarved.item.tool.MagicToolHelper;
import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.IModAware;
import com.teamacronymcoders.base.client.models.IHasModel;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMagicSword extends ItemSword implements IHasModel, IModAware, IManifestedTool {

    public int ticksExisted = 0;
    public static final int expiryTicks = 2400;
    boolean creativeTabSet = false;
    private IBaseMod mod;
    String name;

    public ItemMagicSword(String name) {
        super(Runecarved.MAGIC_TOOL);
        this.setUnlocalizedName(name);
        this.name = name;
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
