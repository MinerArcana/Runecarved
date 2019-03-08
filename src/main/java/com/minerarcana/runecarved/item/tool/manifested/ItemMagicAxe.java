package com.minerarcana.runecarved.item.tool.manifested;

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
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
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

public class ItemMagicAxe extends ItemAxe implements IHasModel, IModAware, IManifestedTool {

    public static final int expiryTicks = 2400;
    public int ticksExisted = 0;
    boolean creativeTabSet = false;
    String name;
    private IBaseMod mod;

    public ItemMagicAxe(String name) {
        super(Runecarved.MAGIC_TOOL, Runecarved.MAGIC_TOOL.getAttackDamage(), -3.0F);
        this.setTranslationKey(name);
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
