package com.minerarcana.runecarved.entity;

import com.minerarcana.runecarved.RunecarvedContent;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityFlame extends EntityThrowable {

    public EntityFlame(World worldIn) {
        super(worldIn);
    }

    public EntityFlame(World worldIn, EntityLivingBase throwerIn) {
        super(worldIn, throwerIn);
    }

    @Override
    public void onUpdate() {
        if (this.ticksExisted >= 80) {
            this.setDead();
        }
        super.onUpdate();
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (result.typeOfHit == RayTraceResult.Type.ENTITY) {
            if (!world.isRemote && result.entityHit != this.ignoreEntity) {
                result.entityHit.setFire(3);
                this.setDead();
            }
        } else if (result.typeOfHit == RayTraceResult.Type.BLOCK)
            if (!world.isRemote) {
                if (world.getTileEntity(result.getBlockPos()) instanceof TileEntityFurnace) {
                    TileEntityFurnace furnace = (TileEntityFurnace) world.getTileEntity(result.getBlockPos());
                    if (furnace.getStackInSlot(1).isEmpty()) {
                        furnace.setInventorySlotContents(1, new ItemStack(RunecarvedContent.ember));
                        this.setDead();
                    }
                    // } else {
                    // if (world.isAirBlock(result.getBlockPos().up())) {
                    // world.setBlockState(result.getBlockPos().up(),
                    // Blocks.FIRE.getDefaultState());
                    // this.setDead();
                    // }
                }
            }
    }

    @Override
    protected float getGravityVelocity() {
        return 0F;
    }

}
