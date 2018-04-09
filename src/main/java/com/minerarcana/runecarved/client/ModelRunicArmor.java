package com.minerarcana.runecarved.client;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;

/**
 * ModelPlayer - Either Mojang or a mod author Created using Tabula 6.0.0
 */
public class ModelRunicArmor extends ModelBiped {
    public ModelRenderer FacePlateL;
    public ModelRenderer FacePlateR;
    public ModelRenderer FaceBar;
    public ModelRenderer PauldronL;
    public ModelRenderer Pauldron2L;
    public ModelRenderer ChestL;
    public ModelRenderer ChestR;
    public ModelRenderer ChestL_1;
    public ModelRenderer PauldronR;
    public ModelRenderer Pauldron2R;
    public ModelRenderer BracerR;
    public ModelRenderer Pauldron2L_1;
    public ModelRenderer LegR;
    public ModelRenderer LegL;
    public ModelRenderer Leg2R;
    public ModelRenderer FootR;
    public ModelRenderer FootL;
    public ModelRenderer Leg2L;

    EntityEquipmentSlot slot;

    public ModelRunicArmor(EntityEquipmentSlot slot) {
        super(0f);
        this.slot = slot;

        this.textureWidth = 128;
        this.textureHeight = 64;

        this.Pauldron2L = new ModelRenderer(this, 64, 48);
        this.Pauldron2L.setRotationPoint(0, 0, 0);
        this.Pauldron2L.addBox(0.7F, -1.5F, -2.0F, 3, 4, 4, 0.45F);
        this.setRotateAngle(Pauldron2L, 0.0F, 0.0F, -0.08726646259971647F);
        this.bipedLeftArm.addChild(Pauldron2L);

        this.LegL = new ModelRenderer(this, 64, 31);
        this.LegL.setRotationPoint(0, 0, 0);
        this.LegL.addBox(-2.0F, 0.0F, -2.5F, 4, 5, 3, 0.26F);
        this.setRotateAngle(LegL, 0.08726646259971647F, -0.08726646259971647F, 0.0F);
        this.bipedLeftLeg.addChild(LegL);

        this.Pauldron2R = new ModelRenderer(this, 64, 48);
        this.Pauldron2R.mirror = true;
        this.Pauldron2R.setRotationPoint(0, 0, 0);
        this.Pauldron2R.addBox(-3.7F, -1.5F, -2.0F, 3, 4, 4, 0.45F);
        this.setRotateAngle(Pauldron2R, 0.0F, 0.0F, 0.08726646259971647F);
        this.bipedRightArm.addChild(Pauldron2R);

        this.ChestL_1 = new ModelRenderer(this, 64, 10);
        this.ChestL_1.setRotationPoint(0, 0, 0);
        this.ChestL_1.addBox(-1.0F, 0.0F, -4.4F, 4, 6, 2, 0.25F);
        this.setRotateAngle(ChestL_1, 0.2617993877991494F, -0.4363323129985824F, 0.0F);
        this.bipedBody.addChild(ChestL_1);

        this.FacePlateL = new ModelRenderer(this, 72, 0);
        this.FacePlateL.setRotationPoint(0.65F, -8.5F, -5.55F);
        this.FacePlateL.addBox(0.0F, 0.0F, 0.0F, 4, 9, 1, 0.0F);
        this.setRotateAngle(FacePlateL, 0.0F, -0.2617993877991494F, 0.0F);
        this.bipedHead.addChild(FacePlateL);

        this.ChestR = new ModelRenderer(this, 64, 10);
        this.ChestR.mirror = true;
        this.ChestR.setRotationPoint(0, 0, 0);
        this.ChestR.addBox(-3.0F, 0.0F, -4.4F, 4, 6, 2, 0.25F);
        this.setRotateAngle(ChestR, 0.2617993877991494F, 0.4363323129985824F, 0.0F);
        this.bipedBody.addChild(ChestR);

        this.Leg2R = new ModelRenderer(this, 64, 18);
        this.Leg2R.mirror = true;
        this.Leg2R.setRotationPoint(0, 0, 0);
        this.Leg2R.addBox(-2.0F, 3.1F, -4.0F, 4, 6, 2, 0.2F);
        this.setRotateAngle(Leg2R, 0.17453292519943295F, 0.17453292519943295F, 0.0F);
        this.bipedRightLeg.addChild(Leg2R);

        this.FootR = new ModelRenderer(this, 64, 26);
        this.FootR.mirror = true;
        this.FootR.setRotationPoint(0, 0, 0);
        this.FootR.addBox(-2.0F, 10.0F, -2.8F, 4, 2, 1, 0.26F);
        this.bipedRightLeg.addChild(FootR);

        this.FaceBar = new ModelRenderer(this, 64, 0);
        this.FaceBar.setRotationPoint(-1.0F, -9.5F, -5.7F);
        this.FaceBar.addBox(0.0F, 0.0F, 0.0F, 2, 8, 2, 0.0F);
        this.bipedHead.addChild(FaceBar);

        this.PauldronL = new ModelRenderer(this, 64, 56);
        this.PauldronL.setRotationPoint(0, 0, 0);
        this.PauldronL.addBox(0.7F, -3.0F, -2.0F, 3, 3, 4, 0.35F);
        this.setRotateAngle(PauldronL, 0.0F, 0.0F, -0.2617993877991494F);
        this.bipedLeftArm.addChild(PauldronL);

        this.Pauldron2L_1 = new ModelRenderer(this, 64, 39);
        this.Pauldron2L_1.setRotationPoint(0, 0, 0);
        this.Pauldron2L_1.addBox(0.7F, 2.2F, -2.0F, 3, 5, 4, 0.35F);
        this.setRotateAngle(Pauldron2L_1, 0.0F, 0.0F, 0.08726646259971647F);
        this.bipedLeftArm.addChild(Pauldron2L_1);

        this.Leg2L = new ModelRenderer(this, 64, 18);
        this.Leg2L.setRotationPoint(0, 0, 0);
        this.Leg2L.addBox(-2.0F, 3.1F, -4.0F, 4, 6, 2, 0.2F);
        this.setRotateAngle(Leg2L, 0.17453292519943295F, -0.17453292519943295F, 0.0F);
        this.bipedLeftLeg.addChild(Leg2L);

        this.BracerR = new ModelRenderer(this, 64, 39);
        this.BracerR.mirror = true;
        this.BracerR.setRotationPoint(0, 0, 0);
        this.BracerR.addBox(-3.7F, 2.2F, -2.0F, 3, 5, 4, 0.35F);
        this.setRotateAngle(BracerR, 0.0F, 0.0F, -0.08726646259971647F);
        this.bipedRightArm.addChild(BracerR);

        this.LegR = new ModelRenderer(this, 64, 31);
        this.LegR.mirror = true;
        this.LegR.setRotationPoint(0, 0, 0);
        this.LegR.addBox(-2.0F, 0.0F, -2.5F, 4, 5, 3, 0.26F);
        this.setRotateAngle(LegR, 0.08726646259971647F, 0.08726646259971647F, 0.0F);
        this.bipedRightLeg.addChild(LegR);

        this.FacePlateR = new ModelRenderer(this, 72, 0);
        this.FacePlateR.mirror = true;
        this.FacePlateR.setRotationPoint(-4.5F, -8.5F, -4.5F);
        this.FacePlateR.addBox(0.0F, 0.0F, 0.0F, 4, 9, 1, 0.0F);
        this.setRotateAngle(FacePlateR, 0.0F, 0.2617993877991494F, 0.0F);
        this.bipedHead.addChild(FacePlateR);

        this.PauldronR = new ModelRenderer(this, 64, 56);
        this.PauldronR.setRotationPoint(0, 0, 0);
        this.PauldronR.addBox(-3.7F, -3.0F, -2.0F, 3, 3, 4, 0.35F);
        this.setRotateAngle(PauldronR, 0.0F, 0.0F, 0.2617993877991494F);
        this.bipedRightArm.addChild(PauldronR);

        this.FootL = new ModelRenderer(this, 64, 26);
        this.FootL.setRotationPoint(0, 0, 0);
        this.FootL.addBox(-2.0F, 10.0F, -2.8F, 4, 2, 1, 0.26F);
        this.bipedLeftLeg.addChild(FootL);

        this.ChestL = new ModelRenderer(this, 76, 10);
        this.ChestL.setRotationPoint(0, 0, 0);
        this.ChestL.addBox(-1.0F, -0.3F, -4.7F, 2, 11, 2, 0.25F);
        this.setRotateAngle(ChestL, 0.2617993877991494F, 0.0F, 0.0F);
        this.bipedBody.addChild(ChestL);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
            float headPitch, float scale) {
        this.bipedHead.showModel = slot == EntityEquipmentSlot.HEAD;

        this.bipedBody.showModel = slot == EntityEquipmentSlot.CHEST;
        this.bipedLeftArm.showModel = slot == EntityEquipmentSlot.CHEST;
        this.bipedRightArm.showModel = slot == EntityEquipmentSlot.CHEST;

        this.LegL.showModel = slot == EntityEquipmentSlot.LEGS;
        this.LegR.showModel = slot == EntityEquipmentSlot.LEGS;

        this.Leg2L.showModel = slot == EntityEquipmentSlot.FEET;
        this.Leg2R.showModel = slot == EntityEquipmentSlot.FEET;
        this.FootL.showModel = slot == EntityEquipmentSlot.FEET;
        this.FootR.showModel = slot == EntityEquipmentSlot.FEET;
        super.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        // setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw,
        // headPitch, scale, entity);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
