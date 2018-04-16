package com.minerarcana.runecarved;

import com.minerarcana.runecarved.item.ItemRunicArmor;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

import static com.minerarcana.runecarved.Runecarved.MODID;

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
}
