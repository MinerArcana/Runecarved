package com.minerarcana.runecarved;

import com.minerarcana.runecarved.block.BlockSimpleEnchanter;
import com.teamacronymcoders.base.BaseModFoundation;
import com.teamacronymcoders.base.registrysystem.BlockRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import static com.minerarcana.runecarved.Runecarved.*;

@EventBusSubscriber
@Mod(modid = MODID, name = NAME, version = VERSION, dependencies = DEPENDENCIES)
public class Runecarved extends BaseModFoundation<Runecarved> {
    public static final String MODID = "runecarved";
    public static final String NAME = "Runecarved";
    public static final String VERSION = "@VERSION@";
    public static final String DEPENDENCIES = "required-after:base@[0.0.0,);";

    @Instance(MODID)
    public static Runecarved instance;

    public Runecarved() {
        super(MODID, NAME, VERSION, CreativeTabs.MISC);
    }

    @Override
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
    }

    @Override
    @EventHandler
    public void init(FMLInitializationEvent event) {
        super.init(event);
    }

    @Override
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }

    @Override
    public void registerBlocks(BlockRegistry registry) {
        registry.register(new BlockSimpleEnchanter());
    }

    @Override
    public Runecarved getInstance() {
        return this;
    }
}
