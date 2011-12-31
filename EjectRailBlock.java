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
				Location to = Location.getValidLocation(here, world);
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


}
