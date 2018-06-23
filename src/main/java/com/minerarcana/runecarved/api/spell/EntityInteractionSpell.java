package com.minerarcana.runecarved.api.spell;

import com.minerarcana.runecarved.api.caster.ICaster;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public abstract class EntityInteractionSpell extends Spell {

    public EntityInteractionSpell(ResourceLocation name) {
        super(name);
    }

    @Override
    public void cast(ICaster caster) {
        // NO-OP
    }

    public abstract void cast(ICaster caster, ItemStack stack);

    public abstract void castOnEntity(ICaster caster, ItemStack stack, EntityLivingBase entity);

}
