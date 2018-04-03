package com.minerarcana.runecarved;

import com.minerarcana.runecarved.tileentity.TileEntityRunestone;
import com.minerarcana.runecarved.tileentity.TileEntityRunestoneRenderer;

import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {
    @Override
    public void bindTESRs() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRunestone.class, new TileEntityRunestoneRenderer());
    }
}
