package com.minerarcana.runecarved.spell;

import com.minerarcana.runecarved.RunecarvedContent;
import com.minerarcana.runecarved.api.caster.CasterEntityPlayer;
import com.minerarcana.runecarved.api.caster.ICaster;
import com.minerarcana.runecarved.api.spell.Spell;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.minerarcana.runecarved.Runecarved.MODID;

public class ManifestArmor extends Spell {

    public ManifestArmor() {
        super(new ResourceLocation(MODID, "manifest_armor"));
    }

    @Override
    public void cast(ICaster caster) {
        if (caster.getArmor() != null) {
            NonNullList<ItemStack> armor = caster.getArmor();
            if (armor.stream().allMatch(stack -> !stack.isEmpty()) && caster instanceof CasterEntityPlayer) {
                ((CasterEntityPlayer) caster).getPlayer()
                        .addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("absorption"), 600));
            } else {
                List<EntityEquipmentSlot> slots = Arrays.asList(EntityEquipmentSlot.values());
                Collections.shuffle(slots);
                for (EntityEquipmentSlot entityEquipmentSlot : slots) {
                    if (entityEquipmentSlot.getSlotType() == EntityEquipmentSlot.Type.ARMOR) {
                        ItemStack stackInSlot = armor.get(entityEquipmentSlot.getIndex());
                        if (stackInSlot.isEmpty()) {
                            armor.set(entityEquipmentSlot.getIndex(), getArmorStack(entityEquipmentSlot));
                            break;
                        }
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
