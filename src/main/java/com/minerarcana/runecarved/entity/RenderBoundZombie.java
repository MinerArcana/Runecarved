package com.minerarcana.runecarved.entity;

import com.minerarcana.runecarved.Runecarved;

import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBoundZombie extends RenderBiped<EntityBoundZombie> {
    private static final ResourceLocation BOUND = new ResourceLocation(Runecarved.MODID,
            "textures/entity/bound_zombie.png");
    private static final ResourceLocation MINDLESS = new ResourceLocation(Runecarved.MODID,
            "textures/entity/bound_zombie_mindless.png");

    public RenderBoundZombie(RenderManager renderManagerIn) {
        super(renderManagerIn, new ModelZombie(), 0.5F);
        LayerBipedArmor layerbipedarmor = new LayerBipedArmor(this) {
            protected void initArmor() {
                this.modelLeggings = new ModelZombie(0.5F, true);
                this.modelArmor = new ModelZombie(1.0F, true);
            }
        };
        this.addLayer(layerbipedarmor);
    }

    @Override
    protected boolean canRenderName(EntityBoundZombie entity) {
        return false;
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless
     * you call Render.bindEntityTexture.
     */
    @Override
    protected ResourceLocation getEntityTexture(EntityBoundZombie entity) {
        if (entity.controller == null) {
            return MINDLESS;
        }
        return BOUND;
    }
}
