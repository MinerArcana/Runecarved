package com.minerarcana.runecarved.spell;

import com.minerarcana.runecarved.api.entity.EntityProjectileSpell;
import com.minerarcana.runecarved.api.spell.ProjectileSpell;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static com.minerarcana.runecarved.Runecarved.MODID;

public class LightningBolt extends ProjectileSpell {
    public LightningBolt() {
        super(new ResourceLocation(MODID, "lightning_bolt"));
    }

    @Override
    public EntityProjectileSpell getEntityProjectileSpell(World world) {
        return new EntityProjectileSpell(world, this);
    }

    @Override
    public void onImpact(EntityProjectileSpell entitySpell, Entity entity) {
        if (entity instanceof EntityLivingBase) {
            EntityLivingBase living = ((EntityLivingBase) entity);
            living.addPotionEffect(
                    new PotionEffect(Potion.getPotionFromResourceLocation("slowness"), 20, 3, true, false));
            living.addPotionEffect(
                    new PotionEffect(Potion.getPotionFromResourceLocation("blindness"), 20, 1, true, false));
        }
    }

    @Override
    public void onImpact(EntityProjectileSpell entitySpell, BlockPos pos, EnumFacing impactedSize) {
        // TODO Auto-generated method stub

    }

}
