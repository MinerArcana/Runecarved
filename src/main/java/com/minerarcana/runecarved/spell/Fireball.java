package com.minerarcana.runecarved.spell;

import static com.minerarcana.runecarved.Runecarved.MODID;

import com.minerarcana.runecarved.RunecarvedContent;
import com.minerarcana.runecarved.api.entity.EntityProjectileSpell;
import com.minerarcana.runecarved.api.spell.ProjectileSpell;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Fireball extends ProjectileSpell {

    public Fireball() {
        super(new ResourceLocation(MODID, "fire"));
    }

    @Override
    public EntityProjectileSpell getEntityProjectileSpell(World world) {
        return new EntityProjectileSpell(world, this);
    }

    @Override
    public void onImpact(EntityProjectileSpell entitySpell, Entity entity) {
        entity.setFire(10);
    }

    @Override
    public void onImpact(EntityProjectileSpell entitySpell, BlockPos pos, EnumFacing impactedSize) {
        World world = entitySpell.getEntityWorld();
        if (!world.isRemote) {
            if (world.getTileEntity(pos) instanceof TileEntityFurnace) {
                TileEntityFurnace furnace = (TileEntityFurnace) world.getTileEntity(pos);
                if (furnace.getStackInSlot(1).isEmpty()) {
                    furnace.setInventorySlotContents(1, new ItemStack(RunecarvedContent.ember));
                }
            }
        }
    }
}
