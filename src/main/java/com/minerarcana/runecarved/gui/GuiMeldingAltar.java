package com.minerarcana.runecarved.gui;

import com.minerarcana.runecarved.Runecarved;
import com.minerarcana.runecarved.api.spell.Spell;
import com.minerarcana.runecarved.container.ContainerMeldingAltar;
import com.minerarcana.runecarved.tileentity.TileEntityMeldingAltar;
import com.minerarcana.runecarved.tileentity.TileEntityRuneIndex.ItemHandlerRunic;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

@SideOnly(Side.CLIENT)
public class GuiMeldingAltar extends GuiContainer {

	private static final ResourceLocation GUI_TEX = new ResourceLocation(Runecarved.MODID,
			"textures/gui/melding_altar.png");
	public TileEntityMeldingAltar tile;

	public GuiMeldingAltar(ContainerMeldingAltar inventorySlotsIn, TileEntityMeldingAltar tile) {
		super(inventorySlotsIn);
		this.tile = tile;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		this.mc.renderEngine.bindTexture(GUI_TEX);
		int x = (this.width - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);

		ItemStackHandler altar = (ItemStackHandler) this.tile
				.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		if (this.tile.getIndexPos() != null) {
			ItemHandlerRunic index = (ItemHandlerRunic) this.tile.getWorld().getTileEntity(this.tile.getIndexPos())
					.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
			if (tile.currentRecipe != null && !altar.getStackInSlot(9).isEmpty()) {
				for (int i = 0; i < tile.currentRecipe.getRequiredSpells().length; i++) {
					Spell spell = tile.currentRecipe.getRequiredSpells()[i].getSpell();
					int yPos = this.guiTop + 25;
					// if (i % 2 == 0) {
					// xPos += 16;
					// }
					renderSpellIcon(spell, this.guiLeft + 15 + (i * 19), yPos,
							index.getContainedSpells().containsKey(spell));
				}
			}
		}
	}

	private void renderSpellIcon(Spell spell, int xPos, int yPos, boolean isPresent) {
		GlStateManager.pushMatrix();
		mc.getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		mc.getTextureManager().getTexture(TextureMap.LOCATION_BLOCKS_TEXTURE).setBlurMipmap(false, false);
		GlStateManager.enableRescaleNormal();
		GlStateManager.enableAlpha();
		GlStateManager.alphaFunc(516, 0.1F);
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
		GlStateManager.translate((float) xPos, (float) yPos, 100.0F + this.zLevel);
		GlStateManager.translate(8.0F, 8.0F, 0.0F);
		GlStateManager.scale(1.0F, -1.0F, 1.0F);
		GlStateManager.scale(16.0F, 16.0F, 16.0F);
		if (!isPresent) {
			GlStateManager.scale(0.5F, 0.5F, 0.5F);
		}
		ItemStack stack = new ItemStack(
				Item.getByNameOrId("runecarved:runestone." + spell.getRegistryName().getPath()));
		mc.getRenderItem().renderItem(stack, mc.getRenderItem().getItemModelWithOverrides(stack, null, null));
		GlStateManager.disableAlpha();
		GlStateManager.disableRescaleNormal();
		GlStateManager.disableLighting();
		GlStateManager.popMatrix();
		mc.getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		mc.getTextureManager().getTexture(TextureMap.LOCATION_BLOCKS_TEXTURE).restoreLastBlurMipmap();
	}
}
