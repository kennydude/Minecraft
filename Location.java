package me.kennydude.mod;

import net.minecraft.src.IBlockAccess;
import net.minecraft.src.BlockRail;
import net.minecraft.src.Block;
import net.minecraft.src.minecart.IRail;

public class Location{
	public int x;
	public int y;
	public int z;

	public IBlockAccess world;

	public Location(IBlockAccess world, int x, int y, int z){
		this.x = x;
		this.y = y;
		this.z = z;
		this.world = world;
	}

	public String toString(){
		return "X: " + x + ", Y:" + y + ", Z: " + z;
	}

	public static Location getValidLocation(Location block, IBlockAccess world) {
		return getValidLocation(block, 3, world);
	}

	public static Location getValidLocation(Location block, int searchRange, IBlockAccess world) {
	
		if (isSolidMaterial(world, block) ) {
				return new Location(world, block.x+1, block.y, block.z +1);
		}

		for (int range = 1; range < searchRange+1; range++) {
			for (int dx = -(range); dx <= range; dx++){
				for (int dy = -(range); dy <= range; dy++){
					for (int dz = -(range); dz <= range; dz++){
						if (isSolidMaterial(world, new Location(world, block.x + dx, block.y + dy, block.z + dz)) ) {
							return new Location(world, block.x + dx + 1, block.y + dy, block.z + 1 + dz);
						}
					}
				}
			}
		}
		return null;
	}

	public static Location getNearestRail(Location block, int searchRange, IBlockAccess world) {
	
		if (isRailBlockAt(world, block.x, block.y, block.z) ) {
				return new Location(world, block.x+1, block.y, block.z +1);
		}

		for (int range = 1; range < searchRange+1; range++) {
			for (int dx = -(range); dx <= range; dx++){
				for (int dy = -(range); dy <= range; dy++){
					for (int dz = -(range); dz <= range; dz++){
						if (isRailBlockAt(world, block.x + dx, block.y + dy, block.z + dz) ) {
							return new Location(world, block.x + dx, block.y + dy, block.z + dz);
						}
					}
				}
			}
		}
		return null;
	}

	static boolean isRailBlockAt(IBlockAccess world, int i, int j, int k){
        int l = world.getBlockId(i, j, k);
		return Block.blocksList[l] instanceof IRail;
    }

	public static boolean isSolidMaterial(IBlockAccess world, Location m) {
		return
			world.getBlockMaterial(m.x, m.y, m.z).isSolid();
		// Stone/Grass/Dirt Block IDs
	}
}
