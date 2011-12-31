package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import me.kennydude.mod.Location;

import net.minecraft.client.Minecraft;
import net.minecraft.src.*;

// Referenced classes of package net.minecraft.src:
//            Block, Material

public class ProgramDirectedRailBlock extends BlockRail{
	// public List<String> stations = new ArrayList<String>();

    public ProgramDirectedRailBlock(int i)
    {
        super(i, 128, false);
    }

	public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity)
    {
		if(EntityDirectedMinecart.class.isInstance(entity)){
			// Directed Minecart
			TileEntitySign tes = (TileEntitySign)world.getBlockTileEntity(i, j, k);
			EntityDirectedMinecart cart = (EntityDirectedMinecart)entity;
			for(String station: tes.signText){
				if(station != ""){
					ModLoader.getLogger().fine( station );
					cart.stations.add(station);
				}
			}
			// NOW PROGRAMMED! :D
		}
	}

    public boolean blockActivated(World world, int i, int j, int k, EntityPlayer entityPlayer)
    {
		System.out.println( i + "," + j + "," + k + "" );

		TileEntitySign tes = (TileEntitySign)world.getBlockTileEntity(i, j, k);

		System.out.println( tes.toString() );
		Minecraft mc = ModLoader.getMinecraftInstance();
		mc.displayGuiScreen(new GuiProgramScreen(world, entityPlayer, mc, tes));
		return true;
	}

    public void onBlockAdded(World world, int i, int j, int k)
    {
		world.setBlockTileEntity(i, j, k, getBlockEntity());
		System.out.println( i + "," + j + "," + k + "" );
        super.onBlockAdded(world, i, j, k);
    }

    public void onBlockRemoval(World world, int i, int j, int k)
    {
        super.onBlockRemoval(world, i, j, k);
        world.removeBlockTileEntity(i, j, k);
    }
	
    public TileEntity getBlockEntity()
    {
        return new TileEntitySign();
    }
}
