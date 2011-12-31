package net.minecraft.src;

import java.util.Random;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.src.minecart.IRail;
import me.kennydude.mod.Location;

// Referenced classes of package net.minecraft.src:
//            Block, Material

public class DirectedChangeBlock extends BlockContainer implements IRail
{

    public TileEntity getBlockEntity()
    {
        return new TileEntitySign();
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
		if(!EntityDirectedMinecart.class.isInstance(cart)){
			return;
		}
		boolean light = false;
		TileEntitySign tes = (TileEntitySign)world.getBlockTileEntity(i, j, k);
		for(String station: tes.signText){
			if(station.equals( ((EntityDirectedMinecart)cart).station ) && !station.equals("")){
				light = true;
			}
		}

		if(light == true){
			world.setBlockMetadataWithNotify(i, j, k, 1);
			world.markBlocksDirty(i, j, k, i, j, k);
			world.scheduleBlockUpdate(i, j, k, blockID, tickRate());
		}
	}

	@Override
	public boolean hasPowerBit(World world, int i, int j, int k) {
		return false;
	}

    public boolean blockActivated(World world, int i, int j, int k, EntityPlayer entityPlayer)
    {
		TileEntitySign tes = (TileEntitySign)world.getBlockTileEntity(i, j, k);
		Minecraft mc = ModLoader.getMinecraftInstance();
		mc.displayGuiScreen(new GuiProgramScreen(world, entityPlayer, mc, tes));
		return true;
	}

    public void updateTick(World world, int i, int j, int k, Random random)
    {
		// System.out.println("tick.1");
        int l = world.getBlockMetadata(i, j, k);
        setStateIfMinecartInteractsWithRail(world, i, j, k, l);
    }

    public DirectedChangeBlock(int i)
    {
		super(i, 128, Material.circuits);
    	setTickOnLoad(true);
		setRequiresSelfNotify();;
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
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


    public boolean isPoweringTo(IBlockAccess iblockaccess, int i, int j, int k, int l)
    {
        return iblockaccess.getBlockMetadata(i, j, k) > 0;
    }

    public boolean isIndirectlyPoweringTo(World world, int i, int j, int k, int l)
    {
        if(world.getBlockMetadata(i, j, k) == 0)
        {
            return false;
        } else
        {
            return l == 1;
        }
    }

    public int tickRate()
    {
        return 30;
    }

	public boolean canProvidePower()
    {
        return true;
    }

	private void setStateIfMinecartInteractsWithRail(World world, int i, int j, int k, int l) {
		//boolean flag = (l & 8) != 0;
		boolean flag1 = false;
		//float f = 0.125F;
		List list = world.getEntitiesWithinAABB(EntityDirectedMinecart.class, 	
			AxisAlignedBB.getBoundingBoxFromPool(
				(float)i, j, (float)k,
				(float)(i + 2),	(double)j + 2, (float)(k + 2))
			);
		if(list.size() > 0) {
			TileEntitySign tes = (TileEntitySign)world.getBlockTileEntity(i, j, k);
			for(Object e : list){
				for(String station: tes.signText){
					if(station.equals( ((EntityDirectedMinecart)e).station ) && !station.equals("")){
						flag1 = true;
					}
				}
			}
		}
		if(!flag1 /*&& !flag*/)
        {
            world.setBlockMetadataWithNotify(i, j, k, 0);
            //world.notifyBlocksOfNeighborChange(i, j, k, blockID);
            //world.notifyBlocksOfNeighborChange(i, j - 1, k, blockID);
            world.markBlocksDirty(i, j, k, i, j, k);
        }
        if(flag1/* && /*flag*/)
        {
            world.setBlockMetadataWithNotify(i, j, k, 1);
            //world.notifyBlocksOfNeighborChange(i, j, k, blockID);
            //world.notifyBlocksOfNeighborChange(i, j - 1, k, blockID);
            world.markBlocksDirty(i, j, k, i, j, k);
        }
        if(flag1)
        {
            world.scheduleBlockUpdate(i, j, k, blockID, tickRate());
        }
	}
}
