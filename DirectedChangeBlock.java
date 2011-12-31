package net.minecraft.src;

import java.util.Random;
import java.util.List;
import net.minecraft.client.Minecraft;

// Referenced classes of package net.minecraft.src:
//            Block, Material

public class DirectedChangeBlock extends BlockContainer
{
    public TileEntity getBlockEntity()
    {
        return new TileEntitySign();
    }

    public boolean blockActivated(World world, int i, int j, int k, EntityPlayer entityPlayer)
    {
		TileEntitySign tes = (TileEntitySign)world.getBlockTileEntity(i, j, k);
		Minecraft mc = ModLoader.getMinecraftInstance();
		mc.displayGuiScreen(new GuiProgramScreen(world, entityPlayer, mc, tes));
		return true;
	}
    public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity)
    {
        int l = world.getBlockMetadata(i, j, k);
        if((l & 8) != 0)
        {
            return;
        } else
        {
            setStateIfMinecartInteractsWithRail(world, i, j, k, l);
            return;
        }
    }

    public void updateTick(World world, int i, int j, int k, Random random)
    {
		System.out.println("tick.1");
        int l = world.getBlockMetadata(i, j, k);
        if((l & 8) == 0)
        {
            return;
        } else
        {
            setStateIfMinecartInteractsWithRail(world, i, j, k, l);
            return;
        }
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
        return (iblockaccess.getBlockMetadata(i, j, k) & 8) != 0;
    }

    public boolean isIndirectlyPoweringTo(World world, int i, int j, int k, int l)
    {
        if((world.getBlockMetadata(i, j, k) & 8) == 0)
        {
            return false;
        } else
        {
            return l == 1;
        }
    }

    public int tickRate()
    {
        return 20;
    }

	public boolean canProvidePower()
    {
        return true;
    }

	private void setStateIfMinecartInteractsWithRail(World world, int i, int j, int k, int l) {
		System.out.println("TICK");
		boolean flag = (l & 8) != 0;
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
				System.out.println("O: " + ((EntityDirectedMinecart)e).station );
				for(String station: tes.signText){
					if(station.equals( ((EntityDirectedMinecart)e).station ) && !station.equals("")){
						System.out.println(station);
						flag1 = true;
					}
				}
			}
		}
		if(flag1 && !flag)
        {
            world.setBlockMetadataWithNotify(i, j, k, l | 8);
            world.notifyBlocksOfNeighborChange(i, j, k, blockID);
            world.notifyBlocksOfNeighborChange(i, j - 1, k, blockID);
            world.markBlocksDirty(i, j, k, i, j, k);
        }
        if(!flag1 && flag)
        {
            world.setBlockMetadataWithNotify(i, j, k, l & 7);
            world.notifyBlocksOfNeighborChange(i, j, k, blockID);
            world.notifyBlocksOfNeighborChange(i, j - 1, k, blockID);
            world.markBlocksDirty(i, j, k, i, j, k);
        }
        if(flag1)
        {
            world.scheduleBlockUpdate(i, j, k, blockID, tickRate());
        }
	}
}
