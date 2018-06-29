package com.minerarcana.runecarved.util;

import javax.annotation.Nullable;

import com.minerarcana.runecarved.Runecarved;
import com.minerarcana.runecarved.RunecarvedContent;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.World;

public class TileUtils {
	private static int searchRadius = 3;

	@Nullable
	public static BlockPos searchForIndex(BlockPos start, World world) {
		MutableBlockPos pos = new MutableBlockPos(start);
		for (int x = -searchRadius; x < searchRadius; x++) {
			for (int z = 0; z < searchRadius; z++) {
				pos.add(x, 0, z);
				if (world.getBlockState(pos).getBlock() == RunecarvedContent.runeIndex) {
					Runecarved.instance.getLogger().devInfo("Found index at " + pos.toString());
					return new BlockPos(pos);
				}
			}
		}
		return null;
	}
}
