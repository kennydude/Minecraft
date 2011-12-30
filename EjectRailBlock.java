package net.minecraft.src;

import java.util.Random;
import me.kennydude.mod.Location;

// Referenced classes of package net.minecraft.src:
//            Block, Material

public class EjectRailBlock extends BlockRail
{

    public EjectRailBlock(int i)
    {
        super(i,128, false);
    }

	public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity)
    {
		if(EntityMinecart.class.isInstance(entity)){
			// Minecart
			if(entity.riddenByEntity != null){
				Location here = new Location(world, i, j, k);
				Location to = getValidLocation(here, world);
				entity.riddenByEntity.setPosition(to.x, to.y + 3 , to.z );
				if(entity.riddenByEntity.ridingEntity == entity)
		        {
		            entity.riddenByEntity.ridingEntity = null;
		        }
				entity.riddenByEntity = null;
			}
			entity.setEntityDead();
		}
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
