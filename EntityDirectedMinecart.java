package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.src.*;

/**
 * A minecart which knows where it's going! :D
 */
public class EntityDirectedMinecart extends EntityMinecart{
	public List<String> stations = new ArrayList<String>();

	public EntityDirectedMinecart(World world, double d, double d1, double d2){
		super(world, d, d1, d2, 0); // MinecartType 0
	}

    /*public boolean interact(EntityPlayer entityplayer) {
		// TODO: Show GUI
		
	}*/

	public boolean isUseableByPlayer(EntityPlayer entityplayer)
    {
		// You can't use it if we don't know where to go
		if(stations.size() != 0){
			return super.isUseableByPlayer(entityplayer);
		} else{
			return false;
		}
	}
}
