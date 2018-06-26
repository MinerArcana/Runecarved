package com.minerarcana.runecarved.block;

import javax.annotation.Nonnull;

import com.minerarcana.runecarved.tileentity.TileEntitySimpleEnchanter;
import com.teamacronymcoders.base.blocks.BlockTEBase;
import com.teamacronymcoders.base.guisystem.GuiOpener;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSimpleEnchanter extends BlockTEBase<TileEntitySimpleEnchanter> {

	protected static final AxisAlignedBB AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D);

	public BlockSimpleEnchanter() {
		super(Material.ROCK, "simple_enchanter");
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (worldIn.isRemote) {
			return true;
		} else {
			GuiOpener.openTileEntityGui(getMod(), playerIn, worldIn, pos);
			return true;
		}
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	@Nonnull
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	@Nonnull
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return AABB;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean hasTileEntity(IBlockState blockState) {
		return true;
	}

	@Override
	@Nonnull
	public TileEntity createTileEntity(@Nonnull World world, @Nonnull IBlockState blockState) {
		return new TileEntitySimpleEnchanter();
	}

	@Override
	public Class<? extends TileEntity> getTileEntityClass() {
		return TileEntitySimpleEnchanter.class;
	}
}
