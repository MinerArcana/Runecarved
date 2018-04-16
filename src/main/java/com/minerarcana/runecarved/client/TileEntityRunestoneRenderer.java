package com.minerarcana.runecarved.client;

import com.minerarcana.runecarved.RunecarvedContent;
import com.minerarcana.runecarved.block.BlockRunestone;
import com.minerarcana.runecarved.potion.PotionSeeInvisible;
import com.minerarcana.runecarved.tileentity.TileEntityRunestone;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.model.animation.FastTESR;

public class TileEntityRunestoneRenderer extends FastTESR<TileEntityRunestone> {

    protected static BlockRendererDispatcher blockRenderer;
    private static Minecraft mc = Minecraft.getMinecraft();

    // TODO
    @Override
    public void renderTileEntityFast(TileEntityRunestone te, double x, double y, double z, float partialTicks,
            int destroyStage, float partial, BufferBuilder renderer) {
        if (mc.player.getActivePotionEffect(PotionSeeInvisible.SEE_INVISIBLE) != null
        /* || mc.player.getHeldItemMainhand().getItem() == Items.GOLD_INGOT */) {
            // renderer.pos(x, y, z);
            if (blockRenderer == null)
                blockRenderer = Minecraft.getMinecraft().getBlockRendererDispatcher();
            BlockPos pos = te.getPos();
            int lightCoords = mc.world.getBlockState(pos).getPackedLightmapCoords(mc.world, pos);
            int lightX = lightCoords >> 16 & 65535;
            int lightY = lightCoords & 65535;
            renderer.lightmap(lightX, lightY);
            renderer.setTranslation(x - pos.getX(), y - pos.getY(), z - pos.getZ());
            IBlockAccess world = MinecraftForgeClient.getRegionRenderCache(te.getWorld(), pos);
            mc.getBlockRendererDispatcher().renderBlock(RunecarvedContent.runestoneBlock.getDefaultState()
                    .withProperty(BlockRunestone.DO_RENDER, true)/* TODO */, pos, world, renderer);

        }
    }

}
