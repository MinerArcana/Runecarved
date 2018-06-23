package com.minerarcana.runecarved.block;

import com.minerarcana.runecarved.tileentity.TileEntityCarvingTable;
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

public class BlockCarvingTable extends BlockTEBase<TileEntityCarvingTable> {

    public BlockCarvingTable() {
        super(Material.ROCK, "carving_table");
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
            EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote) {
            return true;
        } else {
            GuiOpener.openTileEntityGui(getMod(), playerIn, worldIn, pos);
            this.getTileEntity(worldIn, pos).ifPresent(tile -> tile.searchForIndex());
            return true;
        }
    }

    @Override
    public Class<? extends TileEntity> getTileEntityClass() {
        return TileEntityCarvingTable.class;
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState blockState) {
        return new TileEntityCarvingTable();
    }

}
