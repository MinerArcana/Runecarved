package com.minerarcana.runecarved.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityBoundZombie extends EntityZombie {
    EntityPlayer controller;

    public EntityBoundZombie(World worldIn) {
        super(worldIn);
    }

    public void setController(EntityPlayer player) {
        controller = player;
    }

    @Override
    protected ResourceLocation getLootTable() {
        return null;
    }

    @Override
    protected void applyEntityAI() {
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<EntityLivingBase>(this, EntityLivingBase.class,
                10, true, true, entity -> !(entity instanceof EntityBoundZombie)));
    }

    @Override
    protected boolean canDropLoot() {
        return false;
    }
}
