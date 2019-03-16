package com.minerarcana.runecarved.block;

import com.minerarcana.runecarved.tileentity.TileEntityRuneIndex;
import com.teamacronymcoders.base.blocks.BlockTEBase;
import com.teamacronymcoders.base.guisystem.GuiOpener;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class BlockRuneIndex extends BlockTEBase<TileEntityRuneIndex> {

    public BlockRuneIndex() {
        super(Material.ROCK, "rune_index");
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player,
                                    EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            GuiOpener.openTileEntityGui(getMod(), player, world, pos);
        }
        return true;
    }

    @Override
    public Class<? extends TileEntity> getTileEntityClass() {
        return TileEntityRuneIndex.class;
    }

    @Override
    @Nonnull
    public TileEntity createTileEntity(@Nonnull World world, @Nonnull IBlockState blockState) {
        return new TileEntityRuneIndex();
    }

}
