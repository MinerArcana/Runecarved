package com.minerarcana.runecarved.gui;

import java.util.Map;

import com.minerarcana.runecarved.Runecarved;
import com.minerarcana.runecarved.api.RunecarvedAPI;
import com.minerarcana.runecarved.api.spell.Spell;
import com.minerarcana.runecarved.gui.GuiCarvingTable.GuiButtonRune;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiInscriptionBench extends GuiContainer {

    private static final ResourceLocation GUI_TEX = new ResourceLocation(Runecarved.MODID,
            "textures/gui/rune_index.png");

    public GuiInscriptionBench(Container inventorySlotsIn) {
        super(inventorySlotsIn);
    }

    public void initGui() {
        super.initGui();
        this.buttonList.clear();
        int buttonID = 0;
        Map<ResourceLocation, Spell> spells = RunecarvedAPI.getInstance().getSpellRegistry().getSpells();
        for (int vertical = 0; vertical < spells.size(); vertical++) {
            for (int horizontal = 0; horizontal < 3; horizontal++) {
                if (buttonID <= spells.size()) {
                    this.addButton(new GuiButtonRune(buttonID, this.guiLeft + 8 + (horizontal * 19),
                            this.guiTop + 16 + (vertical * 19), 18, 18, (Spell) spells.values().toArray()[buttonID++]));
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
