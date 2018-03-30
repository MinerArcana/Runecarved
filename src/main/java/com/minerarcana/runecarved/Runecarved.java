package com.minerarcana.runecarved;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.*;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@EventBusSubscriber
@Mod(modid = Runecarved.MODID, name = Runecarved.NAME, version = Runecarved.VERSION)
public class Runecarved {
	public static final String MODID = "runecarved";
	public static final String NAME = "Runecarved";
	public static final String VERSION = "0.0.0";

	@Instance(Runecarved.MODID)
	public static Runecarved instance;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {

	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		// TODO

		event.getRegistry().register(new BlockSimpleEnchanter());
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().register(new ItemScroll());
		// TODO
		event.getRegistry().register(new ItemBlock(new BlockSimpleEnchanter()).setRegistryName("simple_enchanter"));
	}
}
