package com.minerarcana.runecarved.spell;

import com.minerarcana.runecarved.RunecarvedContent;
import com.minerarcana.runecarved.api.caster.ICaster;
import com.minerarcana.runecarved.api.spell.Spell;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

import static com.minerarcana.runecarved.Runecarved.MODID;

@ObjectHolder(MODID)
public class ManifestArmor extends Spell {


    public ManifestArmor() {
        super(new ResourceLocation(MODID, "manifest_armor"));
    }

    @Override
    public void cast(ICaster caster) {
        if (caster.getArmor() != null) {
            NonNullList<ItemStack> armor = caster.getArmor();
            for (EntityEquipmentSlot entityEquipmentSlot : EntityEquipmentSlot.values()) {
                if (entityEquipmentSlot.getSlotType() == EntityEquipmentSlot.Type.ARMOR) {
                    ItemStack stackInSlot = armor.get(entityEquipmentSlot.getIndex());
                    if (stackInSlot.isEmpty()) {
                        armor.set(entityEquipmentSlot.getIndex(), getArmorStack(entityEquipmentSlot));
                    }
                }
            }
        }
    }

    private ItemStack getArmorStack(EntityEquipmentSlot entityEquipmentSlot) {
        switch (entityEquipmentSlot) {
            case HEAD:
                return new ItemStack(RunecarvedContent.runicHelmet);
            case CHEST:
                return new ItemStack(RunecarvedContent.runicChestplate);
            case LEGS:
                return new ItemStack(RunecarvedContent.runicLeggings);
            case FEET:
                return new ItemStack(RunecarvedContent.runicBoots);
            default:
                return ItemStack.EMPTY;
        }
    }
}
