package com.minerarcana.runecarved.item;

import javax.annotation.Nullable;

import com.minerarcana.runecarved.Runecarved;
import com.minerarcana.runecarved.client.ModelRunicArmor;
import com.teamacronymcoders.base.items.ItemArmorBase;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemRunicArmor extends ItemArmorBase {

    private static int rV = ArmorMaterial.IRON.getDamageReductionAmount(EntityEquipmentSlot.CHEST);
    public static final ArmorMaterial RUNIC = EnumHelper.addArmorMaterial("runic", Runecarved.MODID, -1,
            new int[] { rV, rV, rV, rV }, 0, SoundEvents.BLOCK_ANVIL_PLACE, ArmorMaterial.DIAMOND.getToughness());

    @SideOnly(Side.CLIENT)
    private ModelRunicArmor modelRunicArmor;

    public ItemRunicArmor(EntityEquipmentSlot equipmentSlotIn, String name) {
        super(RUNIC, equipmentSlotIn, name);
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
