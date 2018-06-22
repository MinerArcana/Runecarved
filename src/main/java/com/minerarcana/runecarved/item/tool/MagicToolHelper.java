package com.minerarcana.runecarved.item.tool;

import com.minerarcana.runecarved.Runecarved;
import com.minerarcana.runecarved.RunecarvedContent;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class MagicToolHelper {

    public static void convertToTool(ItemStack existing, String target, EntityLivingBase user) {
        Item toolItem = null;
        switch (target) {
        case "sword":
            toolItem = RunecarvedContent.magicSword;
            break;
        // case "hoe":
        // toolItem = RunecarvedContent.magicHoe;
        // break;
        case "pickaxe":
            toolItem = RunecarvedContent.magicPickaxe;
            break;
        case "shovel":
            toolItem = RunecarvedContent.magicShovel;
            break;
        case "axe":
            toolItem = RunecarvedContent.magicAxe;
            break;
        default:
            Runecarved.instance.getLogger().devError("Maigc tool got confused");
            break;
        }
        ItemStack newStack = new ItemStack(toolItem);
        newStack.setTagCompound(existing.getTagCompound());
        user.setHeldItem(EnumHand.MAIN_HAND, newStack);
    }

    public static void hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        convertToTool(stack, "sword", attacker);
    }

    public static void onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing,
            float hitX, float hitY, float hitZ) {
        // ItemStack stack = player.getHeldItem(hand);
        // if (Items.IRON_HOE.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY,
        // hitZ) == EnumActionResult.SUCCESS) {
        // MagicToolHelper.convertToTool(stack, "hoe", player);
        // }
    }

    // Necessary to use an event since there is no 'left click on block' method in
    // Item
    @SubscribeEvent
    public static void onBlockLeftClicked(PlayerInteractEvent.LeftClickBlock event) {
        if (event.getEntityPlayer().getHeldItemMainhand().getItem() instanceof IManifestedTool) {
            IBlockState state = event.getWorld().getBlockState(event.getPos());
            if (state.getBlock().isToolEffective("pickaxe", state)) {
                convertToTool(event.getItemStack(), "pickaxe", event.getEntityLiving());
            } else if (state.getBlock().isToolEffective("axe", state)) {
                convertToTool(event.getItemStack(), "axe", event.getEntityLiving());
            } else if (state.getBlock().isToolEffective("shovel", state)) {
                convertToTool(event.getItemStack(), "shovel", event.getEntityLiving());
            }
        }
    }
}
