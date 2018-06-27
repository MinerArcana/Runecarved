package com.minerarcana.runecarved.block;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.minerarcana.runecarved.Runecarved;
import com.minerarcana.runecarved.api.caster.CasterTileEntity;
import com.minerarcana.runecarved.tileentity.TileEntityRunestone;
import com.teamacronymcoders.base.blocks.BlockTEBase;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.*;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockRunestone extends BlockTEBase<TileEntityRunestone> {

	// public static final PropertySpell SPELL = new PropertySpell();
	public static final PropertyBool DO_RENDER = PropertyBool.create("render");
	public static final AxisAlignedBB TINY = new AxisAlignedBB(0.5D, 0.5D, 0.5D, 0.50001D, 0.50001D, 0.50001D);

	public BlockRunestone() {
		super(Material.ROCK, "runestone");
		this.setDefaultState(this.blockState.getBaseState().withProperty(DO_RENDER, false));
		// this.setBlockUnbreakable();
	}

	@Override
	@Nonnull
	public EnumBlockRenderType getRenderType(IBlockState state) {
		if (state.getValue(DO_RENDER)) {
			return EnumBlockRenderType.MODEL;
		}
		return EnumBlockRenderType.INVISIBLE;
	}

	@Override
	public boolean canCollideCheck(IBlockState state, boolean hitIfLiquid) {
		return state.getValue(DO_RENDER);
	}

	@Override
	// Determines passability only, DOES NOT relate to firing
	// onEntityCollidedWithBlock. Because Logic.
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

	@Override
	public boolean isTranslucent(IBlockState state) {
		return true;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	/**
	 * Determines if an entity can path through this block
	 */
	@Override
	public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
		return true;
	}

	@Override
	public boolean canSpawnInBlock() {
		return true;
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
		if (!world.isRemote) {
			if (getTileEntity(world, pos).isPresent()) {
				TileEntityRunestone tile = getTileEntity(world, pos).get();
				if (tile.spell == null)
					return;
				Runecarved.instance.getLogger().devInfo(tile.spell.getRegistryName().toString());
				tile.spell.cast(new CasterTileEntity(tile));
				// TODO Change caster type
				world.setBlockToAir(pos);
			}
		}
	}

	@Override
	public Class<? extends TileEntity> getTileEntityClass() {
		return TileEntityRunestone.class;
	}

	@Override
	@Nonnull
	@ParametersAreNonnullByDefault
	public TileEntity createTileEntity(World world, IBlockState blockState) {
		return new TileEntityRunestone();
	}

	@Override
	@Nonnull
	public BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, DO_RENDER);
	}

	// Render state is never saved
	@Override
	public int getMetaFromState(IBlockState state) {
		return 0;
	}

	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state,
			int fortune) {
		drops.clear();
		if (world.getTileEntity(pos) instanceof TileEntityRunestone) {
			TileEntityRunestone stone = (TileEntityRunestone) world.getTileEntity(pos);
			drops.add(new ItemStack(
					Item.getByNameOrId("runecarved:runestone." + stone.spell.getRegistryName().getResourcePath())));
		}
	}

}
