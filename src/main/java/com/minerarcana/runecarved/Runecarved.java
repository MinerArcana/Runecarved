package com.minerarcana.runecarved;

import static com.minerarcana.runecarved.Runecarved.*;

import com.minerarcana.runecarved.api.RunecarvedAPI;
import com.minerarcana.runecarved.api.spell.SpellRegistryEvent;
import com.minerarcana.runecarved.block.BlockRunestone;
import com.minerarcana.runecarved.block.BlockSimpleEnchanter;
import com.minerarcana.runecarved.item.*;
import com.teamacronymcoders.base.BaseModFoundation;
import com.teamacronymcoders.base.creativetabs.CreativeTabBase;
import com.teamacronymcoders.base.registrysystem.BlockRegistry;
import com.teamacronymcoders.base.registrysystem.ItemRegistry;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

@Mod(modid = MODID, name = NAME, version = VERSION, dependencies = DEPENDENCIES)
public class Runecarved extends BaseModFoundation<Runecarved> {
    public static final String MODID = "runecarved";
    public static final String NAME = "Runecarved";
    public static final String VERSION = "@VERSION@";
    public static final String DEPENDENCIES = "required-after:base@[0.0.0,);";

    @Instance(MODID)
    public static Runecarved instance;

    @SidedProxy(clientSide = "com.minerarcana.runecarved.client.ClientProxy", serverSide = "com.minerarcana.runecarved.CommonProxy")
    public static com.minerarcana.runecarved.CommonProxy proxy;

    public static final ToolMaterial MAGIC_TOOL = EnumHelper.addToolMaterial("TOOL_MAGIC", 2, -1, 12.0F, 3.0F, 0);

    @ObjectHolder(value = Runecarved.MODID + ":runestone.ice_burst")
    public static Item tabIcon;

    public Runecarved() {
        super(MODID, NAME, VERSION, new CreativeTabBase("runecarved", () -> new ItemStack(tabIcon)));
    }

    @Override
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        proxy.bindEntityRenderers();
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
        super.registerBlocks(registry);
    }

    @Override
    public void registerItems(ItemRegistry registry) {
        registry.register(new ItemScroll());
        registry.register(new ItemEmber());
        /*
         * registry.register(new ItemMagicTool()); registry.register(new
         * ItemMagicAxe("magic_axe")); registry.register(new ItemMagicHoe("magic_hoe"));
         * registry.register(new ItemMagicPickaxe("magic_pickaxe"));
         * registry.register(new ItemMagicShovel("magic_shovel")); registry.register(new
         * ItemMagicSword("magic_sword"));
         */
        registry.register(new ItemRunicArmor(EntityEquipmentSlot.HEAD, "runic_helmet"));
        registry.register(new ItemRunicArmor(EntityEquipmentSlot.CHEST, "runic_chestplate"));
        registry.register(new ItemRunicArmor(EntityEquipmentSlot.LEGS, "runic_leggings"));
        registry.register(new ItemRunicArmor(EntityEquipmentSlot.FEET, "runic_boots"));

        super.registerItems(registry);
    }

    @Override
    public Runecarved getInstance() {
        return this;
    }
}
