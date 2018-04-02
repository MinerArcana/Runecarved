package com.minerarcana.runecarved.block;

import com.minerarcana.runecarved.Runecarved;
import com.minerarcana.runecarved.api.caster.CasterTileEntity;
import com.minerarcana.runecarved.tileentity.TileEntityRunestone;
import com.teamacronymcoders.base.blocks.BlockTEBase;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockRunestone extends BlockTEBase<TileEntityRunestone> {

    protected static final AxisAlignedBB UNPRESSED_AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.0625D,
            0.9375D);
    // public static final PropertySpell SPELL = new PropertySpell();

    public BlockRunestone() {
        super(Material.ROCK, "runestone");
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return UNPRESSED_AABB;
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
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        if (!this.canBePlacedOn(worldIn, pos.down())) {
            this.dropBlockAsItem(worldIn, pos, state, 0);// TODO
            worldIn.setBlockToAir(pos);
        }
    }

    private boolean canBePlacedOn(World worldIn, BlockPos pos) {
        return worldIn.getBlockState(pos).isTopSolid() || worldIn.getBlockState(pos).getBlock() instanceof BlockFence;
    }

    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        if (!worldIn.isRemote) {
            if (getTileEntity(worldIn, pos).isPresent()) {
                TileEntityRunestone tile = getTileEntity(worldIn, pos).get();
                Runecarved.instance.getLogger().devInfo(tile.spell.getRegistryName().toString());
                tile.spell.cast(new CasterTileEntity(tile));
                // TODO Change caster type
                worldIn.setBlockToAir(pos);
            }
        }
    }

    @Override
    public Class<? extends TileEntity> getTileEntityClass() {
        return TileEntityRunestone.class;
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState blockState) {
        return new TileEntityRunestone();
    }

}
