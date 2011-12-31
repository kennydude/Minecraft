package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.src.minecart.IRail;
import me.kennydude.mod.Location;

import net.minecraft.client.Minecraft;
import net.minecraft.src.*;

// Referenced classes of package net.minecraft.src:
//            Block, Material

public class ProgramDirectedRailBlock extends BlockContainer implements IRail{
	// public List<String> stations = new ArrayList<String>();

    public ProgramDirectedRailBlock(int i)
    {
        super(i, 128, Material.circuits);
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
    }

	@Override
	public int getBasicRailMetadata(IBlockAccess world, EntityMinecart cart, int i, int j, int k){
		Location here = new Location(world, i, j, k);
		Location to = Location.getNearestRail(here, 1, world);
		if(here.x + 1 == to.x || here.x - 1 == to.x){ // in-front?
			return 0x0;
		}
		return 0x1;
	}
	@Override
	public boolean isFlexibleRail(World world, int i, int j, int k){
		return false;
	}
	@Override
	public boolean canMakeSlopes(World world, int i, int j, int k){
		return false;
	}
	@Override
	public float getRailMaxSpeed(World world, EntityMinecart cart, int i, int j, int k){
		return 0.4f;
	}
	@Override
	public void onMinecartPass(World world, EntityMinecart cart, int i, int j, int k) {
		/* This could probably be used instead of the stuff bellow :/ */
	}

	@Override
	public boolean hasPowerBit(World world, int i, int j, int k) {
		return false;
	}

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k)
    {
        return null;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    public void setBlockBoundsBasedOnState(IBlockAccess iblockaccess, int i, int j, int k)
    {
        int l = iblockaccess.getBlockMetadata(i, j, k);
        if(l >= 2 && l <= 5)
        {
            setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.625F, 1.0F);
        } else
        {
            setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
        }
    }

	public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity)
    {
		if(EntityDirectedMinecart.class.isInstance(entity)){
			// Directed Minecart
			TileEntitySign tes = (TileEntitySign)world.getBlockTileEntity(i, j, k);
			EntityDirectedMinecart cart = (EntityDirectedMinecart)entity;
			for(String station: tes.signText){
				if(!station.equals("") && !cart.stations.contains(station)){
					cart.stations.add(station);
				}
			}
			// NOW PROGRAMMED! :D
		}
	}

    public boolean blockActivated(World world, int i, int j, int k, EntityPlayer entityPlayer)
    {
		TileEntitySign tes = (TileEntitySign)world.getBlockTileEntity(i, j, k);
		Minecraft mc = ModLoader.getMinecraftInstance();
		mc.displayGuiScreen(new GuiProgramScreen(world, entityPlayer, mc, tes));
		return true;
	}


    public int getMobilityFlag()
    {
        return 0;
    }
	
    public TileEntity getBlockEntity()
    {
        return new TileEntitySign();
    }
}
