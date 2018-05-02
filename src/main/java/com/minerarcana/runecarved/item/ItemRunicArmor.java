package com.minerarcana.runecarved.item;

import java.util.List;

import javax.annotation.Nullable;

import com.minerarcana.runecarved.Runecarved;
import com.minerarcana.runecarved.client.ModelRunicArmor;
import com.teamacronymcoders.base.items.ItemArmorBase;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemRunicArmor extends ItemArmorBase {

    public static final ArmorMaterial RUNIC = EnumHelper.addArmorMaterial("runic", Runecarved.MODID, -1,
            new int[] { 2, 6, 5, 2 }, 0, SoundEvents.BLOCK_ANVIL_PLACE, ArmorMaterial.DIAMOND.getToughness());

    @SideOnly(Side.CLIENT)
    private ModelRunicArmor modelRunicArmor;

    public static final int expiryTicks = 3600;

    public ItemRunicArmor(EntityEquipmentSlot equipmentSlotIn, String name) {
        super(RUNIC, equipmentSlotIn, name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if (stack.hasTagCompound()) {
            tooltip.add("Seconds remaining: " + (expiryTicks - stack.getTagCompound().getInteger("existed")) / 20);
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if (!stack.hasTagCompound()) {
            NBTTagCompound tag = new NBTTagCompound();
            tag.setInteger("existed", 0);
            stack.setTagCompound(tag);
        } else {
            int ticksExisted = stack.getTagCompound().getInteger("existed");
            if (ticksExisted == expiryTicks) {
                stack.shrink(1);
            } else {
                NBTTagCompound tag = stack.getTagCompound();
                tag.setInteger("existed", tag.getInteger("existed") + 1);
                stack.setTagCompound(tag);
            }
        }
    }

    @Override
    @Nullable
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase living, ItemStack stack, EntityEquipmentSlot slot,
            ModelBiped defaultModel) {
        if (modelRunicArmor == null) {
            modelRunicArmor = new ModelRunicArmor(slot);
        }
        modelRunicArmor.setModelAttributes(defaultModel);
        return modelRunicArmor;
    }

    @Nullable
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return Runecarved.MODID + ":textures/runic_armor.png";
    }

}
