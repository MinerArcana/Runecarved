package com.minerarcana.runecarved.tileentity;

import com.minerarcana.runecarved.Runecarved;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.model.animation.FastTESR;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

public class TileEntityRunestoneRenderer extends FastTESR<TileEntityRunestone> {

    @ObjectHolder(value = Runecarved.MODID + ":runestone.runecarved.ice_burst")
    public static final Item runestone = null;

    protected static BlockRendererDispatcher blockRenderer;
    private static Minecraft mc = Minecraft.getMinecraft();

    // TODO
    @Override
    public void renderTileEntityFast(TileEntityRunestone te, double x, double y, double z, float partialTicks,
            int destroyStage, float partial, BufferBuilder renderer) {
        if (mc.player.getHeldItemMainhand().getItem() == Items.GOLD_INGOT) {
            ItemStack item = new ItemStack(runestone);

            if (blockRenderer == null)
                blockRenderer = Minecraft.getMinecraft().getBlockRendererDispatcher();
            BlockPos pos = te.getPos();
            int lightCoords = mc.world.getBlockState(pos).getPackedLightmapCoords(mc.world, pos);
            int lightX = lightCoords >> 16 & 65535;
            int lightY = lightCoords & 65535;
            renderer.lightmap(lightX, lightY);
            renderer.setTranslation(x - pos.getX(), y - pos.getY(), z - pos.getZ());
            IBlockAccess world = MinecraftForgeClient.getRegionRenderCache(te.getWorld(), pos);
            mc.getBlockRendererDispatcher().renderBlock(Blocks.BEDROCK.getDefaultState()/* TODO */, pos, world,
                    renderer);
            renderer.putPosition(x, y, z);
        }
    }

}
