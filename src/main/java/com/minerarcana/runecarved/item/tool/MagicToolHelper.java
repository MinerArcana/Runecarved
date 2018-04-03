package com.minerarcana.runecarved.item.tool;

import com.minerarcana.runecarved.Runecarved;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

@ObjectHolder(value = Runecarved.MODID)
public class MagicToolHelper {

    public static final Item magic_sword = null;
    public static final Item magic_hoe = null;
    public static final Item magic_pickaxe = null;
    public static final Item magic_shovel = null;
    public static final Item magic_axe = null;

    public static void convertToTool(ItemStack existing, String target, EntityPlayer player) {
        Item toolItem = null;
        switch (target) {
        case "sword":
            toolItem = magic_sword;
        case "hoe":
            toolItem = magic_hoe;
        case "pickaxe":
            toolItem = magic_pickaxe;
        case "shovel":
            toolItem = magic_shovel;
        case "axe":
            toolItem = magic_axe;
        }
        ItemStack newStack = new ItemStack(toolItem);
        player.inventory.setInventorySlotContents(player.inventory.currentItem, newStack);
    }

}
