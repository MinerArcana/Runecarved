package com.minerarcana.runecarved.block;

import com.minerarcana.runecarved.Runecarved;
import com.minerarcana.runecarved.api.caster.CasterTileEntity;
import com.minerarcana.runecarved.tileentity.TileEntityRunestone;
import com.teamacronymcoders.base.blocks.BlockTEBase;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

public class BlockRunestone extends BlockTEBase<TileEntityRunestone> {

    // public static final PropertySpell SPELL = new PropertySpell();
    public static final PropertyBool DO_RENDER = PropertyBool.create("render");

    public BlockRunestone() {
        super(Material.ROCK, "runestone");
        this.setDefaultState(this.blockState.getBaseState().withProperty(DO_RENDER, false));
    }

    @Override
    @Nonnull
    @SuppressWarnings("deprecation")
    public EnumBlockRenderType getRenderType(IBlockState state) {
        if (state.getValue(DO_RENDER)) {
            return EnumBlockRenderType.MODEL;
        }
        return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    @Override
    @Nullable
    @SuppressWarnings("deprecation")
    @ParametersAreNonnullByDefault
    public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World world, BlockPos pos) {
        return NULL_AABB;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isTranslucent(IBlockState state) {
        return true;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    @SuppressWarnings("deprecation")
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

    /**
     * Checks if this block can be placed exactly at the given position.
     */
    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        return this.canBePlacedOn(worldIn, pos.down());
    }

    /**
     * Called when a neighboring block was changed and marks that this state should
     * perform any checks during a neighbor change. Cases may include when redstone
     * power is updated, cactus blocks popping off due to a neighboring solid block,
     * etc.
     */
    @Override
    @SuppressWarnings("deprecation")
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos) {
        if (!this.canBePlacedOn(world, pos.down())) {
            this.dropBlockAsItem(world, pos, state, 0);// TODO
            world.setBlockToAir(pos);
        }
    }

    private boolean canBePlacedOn(World world, BlockPos pos) {
        return world.getBlockState(pos).isTopSolid() || world.getBlockState(pos).getBlock() instanceof BlockFence;
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

    @Override
    public int getMetaFromState(IBlockState state) {
        return 0;
    }

}
