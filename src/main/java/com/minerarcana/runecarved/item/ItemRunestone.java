package com.minerarcana.runecarved.item;

import com.google.common.collect.Lists;
import com.minerarcana.runecarved.RunecarvedContent;
import com.minerarcana.runecarved.api.spell.Spell;
import com.minerarcana.runecarved.block.BlockRunestone;
import com.minerarcana.runecarved.tileentity.TileEntityRunestone;
import com.teamacronymcoders.base.items.itemblocks.ItemBlockModel;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.stream.Collectors;

public class ItemRunestone extends ItemBlockModel<BlockRunestone> {
    private final Spell spell;

    public ItemRunestone(Spell spell) {
        super((BlockRunestone) RunecarvedContent.runestoneBlock);
        this.setTranslationKey("runestone." + spell.getRegistryName().getPath());
        this.spell = spell;
        BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(this, new RunestoneDispenserBehavior());
    }

    @SuppressWarnings("deprecation")
    @Override
    @SideOnly(Side.CLIENT)
    @ParametersAreNonnullByDefault
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        String rawTooltip = I18n.translateToLocal("spell." + this.spell.getRegistryName().getPath());
        String[] splitTooltip = rawTooltip.split("/");
        for (int i = 1; i < splitTooltip.length; i++) {
            String format = "";
            if (i == 1) {
                format = TextFormatting.BLUE.toString();
            }

            tooltip.add(format + splitTooltip[i]);
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    // Override default behaviour of ItemBlock which is fall back to the block
    @Override
    @ParametersAreNonnullByDefault
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab)) {
            items.add(new ItemStack(this));
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    @Nonnull
    @ParametersAreNonnullByDefault
    public String getItemStackDisplayName(ItemStack stack) {
        return I18n.translateToLocal("spell." + this.spell.getRegistryName().getPath()).split("/")[0] + " "
                + I18n.translateToLocal("item.runecarved.runestone.name");
    }

    @Override
    @Nonnull
    public String getTranslationKey(ItemStack stack) {
        return this.getTranslationKey();
    }

    @Override
    @Nonnull
    public String getTranslationKey() {
        return "item.runestone." + spell.getRegistryName().getPath();
    }

    @Override
    public List<ResourceLocation> getResourceLocations(List<ResourceLocation> resourceLocations) {
        resourceLocations.add(new ResourceLocation(spell.getRegistryName().getNamespace(),
                "runes/" + spell.getRegistryName().getPath()));
        return resourceLocations;
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side,
                                float hitX, float hitY, float hitZ, IBlockState newState) {
        super.placeBlockAt(stack, player, world, pos, side, hitX, hitY, hitZ, newState);

        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity instanceof TileEntityRunestone) {
            ((TileEntityRunestone) tileEntity).spell = spell;
        }

        if (!player.capabilities.isCreativeMode) {
            stack.shrink(1);
        }

        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack) {
        return true;
    }

    @Override
    @Nonnull
    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.RARE;
    }

    @Override
    @Nonnull
    public Item getItem() {
        return this;
    }

    public Spell getSpell() {
        return spell;
    }

    @Nonnull
    @Override
    @SideOnly(Side.CLIENT)
    @ParametersAreNonnullByDefault
    public List<ModelResourceLocation> getModelResourceLocations(List<ModelResourceLocation> models) {
        return this.getResourceLocations(Lists.newArrayList())
                .stream()
                .map(location -> new ModelResourceLocation(location, "inventory"))
                .collect(Collectors.toList());
    }
}
