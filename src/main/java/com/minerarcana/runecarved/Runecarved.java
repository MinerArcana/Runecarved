package com.minerarcana.runecarved;

import static com.minerarcana.runecarved.Runecarved.*;

import com.minerarcana.runecarved.api.RunecarvedAPI;
import com.minerarcana.runecarved.api.spell.SpellRegistryEvent;
import com.minerarcana.runecarved.block.BlockRunestone;
import com.minerarcana.runecarved.block.BlockSimpleEnchanter;
import com.minerarcana.runecarved.item.ItemScroll;
import com.teamacronymcoders.base.BaseModFoundation;
import com.teamacronymcoders.base.registrysystem.BlockRegistry;
import com.teamacronymcoders.base.registrysystem.ItemRegistry;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;

@Mod(modid = MODID, name = NAME, version = VERSION, dependencies = DEPENDENCIES)
public class Runecarved extends BaseModFoundation<Runecarved> {
    public static final String MODID = "runecarved";
    public static final String NAME = "Runecarved";
    public static final String VERSION = "@VERSION@";
    public static final String DEPENDENCIES = "required-after:base@[0.0.0,);";

    @Instance(MODID)
    public static Runecarved instance;

    @SidedProxy(clientSide = "com.minerarcana.runecarved.ClientProxy", serverSide = "com.minerarcana.runecarved.CommonProxy")
    public static com.minerarcana.runecarved.CommonProxy proxy;

    public Runecarved() {
        super(MODID, NAME, VERSION, CreativeTabs.MISC);
    }

    @Override
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        MinecraftForge.EVENT_BUS.post(new SpellRegistryEvent(RunecarvedAPI.getInstance().getSpellRegistry()));
    }

    @Override
    @EventHandler
    public void init(FMLInitializationEvent event) {
        super.init(event);
        proxy.bindTESRs();
    }

    @Override
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }

    @Override
    public void registerBlocks(BlockRegistry registry) {
        registry.register(new BlockSimpleEnchanter());
        registry.register(new BlockRunestone());
    }

    @Override
    public void registerItems(ItemRegistry registry) {
        registry.register(new ItemScroll());
    }

    @Override
    public Runecarved getInstance() {
        return this;
    }
}
