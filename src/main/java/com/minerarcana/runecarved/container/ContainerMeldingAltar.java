package com.minerarcana.runecarved.container;

import com.minerarcana.runecarved.tileentity.TileEntityMeldingAltar;
import com.teamacronymcoders.base.containers.ContainerBase;
import com.teamacronymcoders.base.containers.slots.SlotChanged;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nonnull;

public class ContainerMeldingAltar extends ContainerBase {

    TileEntityMeldingAltar tile;
    EntityPlayer player;

    public ContainerMeldingAltar(EntityPlayer player, World world, TileEntityMeldingAltar tile) {
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 3; ++x) {
                this.addSlotToContainer(
                        new SlotChanged(tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null), tile,
                                x + y * 3, 61 + x * 18, 15 + y * 18));
            }
        }

        this.addSlotToContainer(new SlotChanged(tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null),
                tile, 9, 145, 35) {
            @Override
            public boolean isItemValid(@Nonnull ItemStack stack) {
                return false;
            }
        });
        this.createPlayerSlots(player.inventory);
        this.tile = tile;
        this.player = player;
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        // TODO Auto-generated method stub
        return true;
    }

}
