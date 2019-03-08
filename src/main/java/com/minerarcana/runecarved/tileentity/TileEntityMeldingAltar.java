package com.minerarcana.runecarved.tileentity;

import com.google.common.collect.Lists;
import com.minerarcana.runecarved.container.ContainerMeldingAltar;
import com.minerarcana.runecarved.gui.GuiMeldingAltar;
import com.minerarcana.runecarved.tileentity.TileEntityRuneIndex.ItemHandlerRunic;
import com.minerarcana.runecarved.util.IngredientSpell;
import com.teamacronymcoders.base.guisystem.IHasGui;
import com.teamacronymcoders.base.tileentities.IOnSlotChanged;
import com.teamacronymcoders.base.tileentities.TileEntityInventoryBase;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.RecipeMatcher;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;

public class TileEntityMeldingAltar extends TileEntityInventoryBase implements IHasGui, IOnSlotChanged {

    public RecipeMeldingAltar currentRecipe;
    private BlockPos indexPos;

    public TileEntityMeldingAltar() {
        super(new ItemStackHandlerExposeInternal(10));
    }

    public BlockPos getIndexPos() {
        return indexPos;
    }

    public void setIndexPos(BlockPos indexPos) {
        this.indexPos = indexPos;
    }

    @Override
    public Gui getGui(EntityPlayer entityPlayer, World world, BlockPos blockPos) {
        return new GuiMeldingAltar(new ContainerMeldingAltar(entityPlayer, world, this), this);
    }

    @Override
    public Container getContainer(EntityPlayer entityPlayer, World world, BlockPos blockPos) {
        return new ContainerMeldingAltar(entityPlayer, world, this);
    }

    // TODO Slot changed isn't the best place to run this logic
    @Override
    public void onSlotChanged(Slot slot) {
        if (world.isRemote) {
            return;
        }
        ItemStackHandlerExposeInternal handler = (ItemStackHandlerExposeInternal) this
                .getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
        TileEntityRuneIndex index = ((TileEntityRuneIndex) this.getWorld().getTileEntity(this.getIndexPos()));
        ItemHandlerRunic indexHandler = (ItemHandlerRunic) index
                .getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
        // OUTPUT
        if (slot.getSlotIndex() == 9 && slot.getHasStack()) {
            // Remove recipe ingredients when output is 'claimed'
            handler.getStacks().clear(); // TODO This is lazy :D
            for (IngredientSpell spell : currentRecipe.requiredRunes) {
                // Runecarved.instance.getLogger().devInfo(spell.getSpell().getRegistryName().toString());
                indexHandler.extractItem(indexHandler.getContainedSpells().get(spell.getSpell()), 1, false);
                this.currentRecipe = null;
            }
        }
        // CRAFTING GRID
        else {
            // Search for matching recipe each time craftin grid is changed
            // TODO: This doesn't re-search each time runic index changes (may be more
            // trouble than it is worth)
            List<ItemStack> nonEmpty = Lists.newArrayList();
            for (ItemStack stack : handler.getStacks()) {
                if (!stack.isEmpty()) {
                    nonEmpty.add(stack);
                }
            }
            for (RecipeMeldingAltar recipe : RecipeMeldingAltar.getRecipeList().values()) {
                if (RecipeMatcher.findMatches(nonEmpty, Arrays.asList(recipe.inputs)) != null) {
                    if (Arrays.asList(recipe.requiredRunes).stream().map(ingredient -> ingredient.getSpell())
                            .allMatch(spell -> indexHandler.getContainedSpells().containsKey(spell))) {
                        if (handler.getStackInSlot(9).isEmpty()) {
                            handler.insertItem(9, recipe.getOutput(), false);
                            this.currentRecipe = recipe;
                        }
                    } else {
                        // TODO Index doesn't notify altar of inventory changes. Either fix this or move
                        // this validation to when item is removed.
                        handler.setStackInSlot(9, ItemStack.EMPTY);

                    }
                } else {
                    handler.setStackInSlot(9, ItemStack.EMPTY);
                }
            }
        }
        // TODO
        this.sendBlockUpdate();
        this.markDirty();
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeToDisk(nbt);
        if (currentRecipe != null) {
            nbt.setInteger("curentRecipeID", currentRecipe.id);
        }
        return new SPacketUpdateTileEntity(pos, 3, nbt);
    }

    @Nonnull
    @Override
    public NBTTagCompound getUpdateTag() {
        NBTTagCompound nbt = super.writeToNBT(new NBTTagCompound());
        if (currentRecipe != null) {
            nbt.setInteger("curentRecipeID", currentRecipe.id);
        }
        return nbt;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        this.readFromDisk(pkt.getNbtCompound());
        this.currentRecipe = RecipeMeldingAltar.getRecipeList().get(pkt.getNbtCompound().getInteger("currentRecipeID"));
    }

    public static class ItemStackHandlerExposeInternal extends ItemStackHandler {
        public ItemStackHandlerExposeInternal(int i) {
            super(i);
        }

        public NonNullList<ItemStack> getStacks() {
            return this.stacks;
        }
    }
}
