package me.kennydude.mod;

import net.minecraft.src.World;

public class Location{
	public int x;
	public int y;
	public int z;

	public World world;

	public Location(World world, int x, int y, int z){
		this.x = x;
		this.y = y;
		this.z = z;
		this.world = world;
	}

	public static Location getValidLocation(Location block, World world) {
		return getValidLocation(block, 3, world);
	}

	public static Location getValidLocation(Location block, int searchRange, World world) {
		int real_block = world.getBlockId(block.x, block.y, block.z);
		
		if (isSolidMaterial(real_block) ) {
				return new Location(world, block.x+1, block.y, block.z +1);
		}

		for (int range = 1; range < searchRange+1; range++) {
			for (int dx = -(range); dx <= range; dx++){
				for (int dy = -(range); dy <= range; dy++){
					for (int dz = -(range); dz <= range; dz++){
						int b = world.getBlockId(block.x + dx, block.y + dy, block.z + dz);
						if (isSolidMaterial(b) ) {
							return new Location(world, block.x + dx + 1, block.y + dy, block.z + 1 + dz);
						}
					}
				}
			}
		}
		return null;
	}

	public static boolean isSolidMaterial(int m) {
		return
			m == 3 ||
			m == 1 ||
			m == 2;
		// Stone/Grass/Dirt Block IDs
	}
}
