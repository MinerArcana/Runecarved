package com.minerarcana.runecarved.gui;

import com.minerarcana.runecarved.Runecarved;
import com.minerarcana.runecarved.container.ContainerMeldingAltar;
import com.minerarcana.runecarved.tileentity.TileEntityMeldingAltar;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiMeldingAltar extends GuiContainer {

	private static final ResourceLocation GUI_TEX = new ResourceLocation(Runecarved.MODID,
			"textures/gui/melding_altar.png");

	public GuiMeldingAltar(ContainerMeldingAltar inventorySlotsIn, TileEntityMeldingAltar tile) {
		super(inventorySlotsIn);

	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		this.mc.renderEngine.bindTexture(GUI_TEX);
		int x = (this.width - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
	}
}
