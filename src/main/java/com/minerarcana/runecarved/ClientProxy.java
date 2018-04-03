package com.minerarcana.runecarved;

import com.minerarcana.runecarved.entity.EntityBoundZombie;
import com.minerarcana.runecarved.entity.RenderBoundZombie;
import com.minerarcana.runecarved.tileentity.TileEntityRunestone;
import com.minerarcana.runecarved.tileentity.TileEntityRunestoneRenderer;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.*;

public class ClientProxy extends CommonProxy {
    @Override
    public void bindTESRs() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRunestone.class, new TileEntityRunestoneRenderer());
    }

    @Override
    public void bindEntityRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(EntityBoundZombie.class, new RenderFactoryBoundZombie());
    }

    public static class RenderFactoryBoundZombie implements IRenderFactory {

        @Override
        public Render createRenderFor(RenderManager manager) {
            return new RenderBoundZombie(manager);
        }

    }
}
