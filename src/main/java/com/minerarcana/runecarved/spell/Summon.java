package com.minerarcana.runecarved.spell;

import static com.minerarcana.runecarved.Runecarved.MODID;

import java.lang.reflect.InvocationTargetException;

import com.minerarcana.runecarved.Runecarved;
import com.minerarcana.runecarved.api.caster.ICaster;
import com.minerarcana.runecarved.api.spell.EntityInteractionSpell;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class Summon extends EntityInteractionSpell {
    public Summon() {
        super(new ResourceLocation(MODID, "summon"));
    }

    @Override
    public void cast(ICaster caster, ItemStack stack) {
        if (caster.getWorld().isRemote)
            return;
        if (stack.hasTagCompound() && stack.getTagCompound().hasKey("class")) {
            Runecarved.instance.getLogger().devInfo("Classname: " + stack.getTagCompound().getString("class"));
            EntityLivingBase base = null;
            try {
                base = (EntityLivingBase) Class.forName(stack.getTagCompound().getString("class"))
                        .getDeclaredConstructor(World.class).newInstance(caster.getWorld());
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IllegalArgumentException
                    | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (base != null) {
                base.readFromNBT(stack.getTagCompound().getCompoundTag("data"));
                base.setPositionAndUpdate(caster.getCastPosition().x, caster.getCastPosition().y,
                        caster.getCastPosition().z);
                caster.getWorld().spawnEntity(base);
                stack.getTagCompound().removeTag("class");
                stack.getTagCompound().removeTag("data");
            }
        }
    }

    @Override
    public void castOnEntity(ICaster caster, ItemStack stack, EntityLivingBase entity) {
        if (caster.getWorld().isRemote)
            return;
        if (!stack.getTagCompound().hasKey("class")) {
            stack.getTagCompound().setString("class", entity.getClass().getCanonicalName());
            stack.getTagCompound().setTag("data", entity.writeToNBT(new NBTTagCompound()));
            entity.setDead();
        }
    }
}
