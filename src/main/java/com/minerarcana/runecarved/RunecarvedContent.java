package com.minerarcana.runecarved;

import static com.minerarcana.runecarved.Runecarved.MODID;

import com.minerarcana.runecarved.item.ItemEmber;
import com.minerarcana.runecarved.item.ItemRunicArmor;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

@ObjectHolder(MODID)
public class RunecarvedContent {
    @ObjectHolder("runic_helmet")
    public static ItemRunicArmor runicHelmet;
    @ObjectHolder("runic_chestplate")
    public static ItemRunicArmor runicChestplate;
    @ObjectHolder("runic_leggings")
    public static ItemRunicArmor runicLeggings;
    @ObjectHolder("runic_boots")
    public static ItemRunicArmor runicBoots;
    @ObjectHolder("ember")
    public static ItemEmber ember;
    @ObjectHolder("scroll")
    public static Item scroll;
    @ObjectHolder("runestone")
    public static Block runestoneBlock;
}
