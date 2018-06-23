package com.minerarcana.runecarved.gui;

import java.util.ArrayList;

import com.minerarcana.runecarved.Runecarved;
import com.minerarcana.runecarved.api.spell.Spell;
import com.minerarcana.runecarved.container.ContainerCarvingTable;
import com.minerarcana.runecarved.gui.GuiInscriptionBench.GuiButtonRune;
import com.minerarcana.runecarved.tileentity.TileEntityCarvingTable;
import com.minerarcana.runecarved.tileentity.TileEntityRuneIndex.ItemHandlerRunic;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;

@SideOnly(Side.CLIENT)
public class GuiCarvingTable extends GuiContainer {

    private static final ResourceLocation GUI_TEX = new ResourceLocation(Runecarved.MODID,
            "textures/gui/carving_table.png");
    private TileEntityCarvingTable tile;

    public GuiCarvingTable(ContainerCarvingTable inventorySlotsIn, TileEntityCarvingTable tile) {
        super(inventorySlotsIn);
        this.tile = tile;
    }

    @Override
    public void initGui() {
        super.initGui();
        this.buttonList.clear();
        if (tile.getIndex() != null) {
            int buttonID = 0;
            ArrayList<Spell> spells = ((ItemHandlerRunic) tile.getIndex()
                    .getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)).getContainedSpells();
            for (int vertical = 0; vertical < spells.size(); vertical++) {
                for (int horizontal = 0; horizontal < 3; horizontal++) {
                    if (buttonID <= spells.size()) {
                        Spell spell = spells.get(buttonID++);
                        this.addButton(new GuiButtonRune(buttonID, this.guiLeft + 8 + (horizontal * 19),
                                this.guiTop + 16 + (vertical * 19), 18, 18, spell));
                    }
                }
            }
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        this.mc.renderEngine.bindTexture(GUI_TEX);
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
    }
}
