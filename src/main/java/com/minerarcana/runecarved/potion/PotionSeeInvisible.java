package com.minerarcana.runecarved.potion;

import java.awt.Color;

import com.minerarcana.runecarved.Runecarved;

import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PotionSeeInvisible extends Potion {
    public static final PotionSeeInvisible SEE_INVISIBLE = new PotionSeeInvisible();

    private static final ResourceLocation texture = new ResourceLocation(Runecarved.MODID,
            "textures/potion/see_invisible.png");

    public PotionSeeInvisible() {
        super(false, Color.YELLOW.getRGB());
        this.setPotionName("potion.see_invisible");
        this.setRegistryName(new ResourceLocation(Runecarved.MODID, "see_invisible"));
        this.setIconIndex(0, 0);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getStatusIconIndex() {
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
        return super.getStatusIconIndex();
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return true;
    }
}
