package com.minerarcana.runecarved;

import com.minerarcana.runecarved.api.RunecarvedAPI;
import com.minerarcana.runecarved.api.spell.SpellRegistryEvent;
import com.minerarcana.runecarved.block.BlockRuneIndex;
import com.minerarcana.runecarved.block.BlockRunestone;
import com.minerarcana.runecarved.block.BlockSimpleEnchanter;
import com.minerarcana.runecarved.container.HandlerRuneButton;
import com.minerarcana.runecarved.container.PacketRuneButton;
import com.minerarcana.runecarved.item.ItemEmber;
import com.minerarcana.runecarved.item.ItemRunestone;
import com.minerarcana.runecarved.item.ItemRunicArmor;
import com.minerarcana.runecarved.item.ItemScroll;
import com.minerarcana.runecarved.item.tool.manifested.*;
import com.teamacronymcoders.base.BaseModFoundation;
import com.teamacronymcoders.base.creativetabs.CreativeTabBase;
import com.teamacronymcoders.base.registrysystem.BlockRegistry;
import com.teamacronymcoders.base.registrysystem.ItemRegistry;
import com.teamacronymcoders.base.util.Platform;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import javax.annotation.Nonnull;

import static com.minerarcana.runecarved.Runecarved.*;

@Mod(modid = MODID, name = NAME, version = VERSION, dependencies = DEPENDENCIES)
@EventBusSubscriber
public class Runecarved extends BaseModFoundation<Runecarved> {
    public static final String MODID = "runecarved";
    public static final String NAME = "Runecarved";
    public static final String VERSION = "@VERSION@";
    public static final String DEPENDENCIES = "required-after:base@[0.0.0,);";
    public static final ToolMaterial MAGIC_TOOL = EnumHelper.addToolMaterial("TOOL_MAGIC", 2, -1, 12.0F, 3.0F, 0);
    @Instance(MODID)
    public static Runecarved instance;
    @SidedProxy(clientSide = "com.minerarcana.runecarved.client.ClientProxy", serverSide = "com.minerarcana.runecarved.CommonProxy")
    public static com.minerarcana.runecarved.CommonProxy proxy;

    public Runecarved() {
        super(MODID, NAME, VERSION, new CreativeTabBase("runecarved", null) {
            @Override
            @Nonnull
            public ItemStack createIcon() {
                return new ItemStack(RunecarvedContent.ember);
            }
        }, false);
    }

    // TODO Move away from using an event if possible. It is currently necessary to
    // work around bounding box trickery done in BlockRunestone
    @SubscribeEvent
    public static void onLeftClick(PlayerInteractEvent.LeftClickBlock event) {
        BlockPos pos = new BlockPos(event.getEntityPlayer().getPosition());
        Runecarved.instance.getLogger().devInfo(pos.toString());
        IBlockState blockState = event.getWorld().getBlockState(pos);
        Block block = blockState.getBlock();
        if (event.getItemStack().getItem() == RunecarvedContent.runepick && block == RunecarvedContent.runestoneBlock) {
            block.dropBlockAsItem(event.getWorld(), pos, blockState, 0);
            event.getWorld().setBlockToAir(pos);
        }
    }

    @Override
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        proxy.bindEntityRenderers();
        MinecraftForge.EVENT_BUS.post(new SpellRegistryEvent(RunecarvedAPI.getInstance().getSpellRegistry()));
        Runecarved.instance.getPacketHandler().registerPacket(HandlerRuneButton.class, PacketRuneButton.class,
                Side.SERVER);
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
        registry.register(new BlockRuneIndex());
        super.registerBlocks(registry);
    }

    @Override
    public void registerItems(ItemRegistry registry) {
        registry.register(new ItemScroll());
        registry.register(new ItemEmber());

        registry.register(new ItemMagicTool());
        registry.register(new ItemMagicAxe("magic_axe"));
        registry.register(new ItemMagicPickaxe("magic_pickaxe"));
        registry.register(new ItemMagicShovel("magic_shovel"));
        registry.register(new ItemMagicSword("magic_sword"));

        registry.register(new ItemRunicArmor(EntityEquipmentSlot.HEAD, "runic_helmet"));
        registry.register(new ItemRunicArmor(EntityEquipmentSlot.CHEST, "runic_chestplate"));
        registry.register(new ItemRunicArmor(EntityEquipmentSlot.LEGS, "runic_leggings"));
        registry.register(new ItemRunicArmor(EntityEquipmentSlot.FEET, "runic_boots"));

        RunecarvedAPI.getInstance().getSpellRegistry().getSpells().values()
                .forEach(item -> registry.register(new ItemRunestone(item)));

        super.registerItems(registry);
    }

    @Override
    public Runecarved getInstance() {
        return this;
    }
}
