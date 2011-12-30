package net.minecraft.src;

import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            Block, Material

public class MinecartGeneratorBlock extends Block
{
	public static final int minecartType = 0; // Makes normal carts
	int frontTexture = 0;

    public MinecartGeneratorBlock(int i)
    {
        super(i, Material.rock);
		blockIndexInTexture = 45;
		frontTexture = ModLoader.addOverride("/terrain.png", "/kennydude/generator.png");
    }


	/* Copied from BlockDispenser.java */
    public int getBlockTexture(IBlockAccess iblockaccess, int i, int j, int k, int l)
    {
        if(l == 1)
        {
            return blockIndexInTexture + 17;
        }
        if(l == 0)
        {
            return blockIndexInTexture + 17;
        } else
        {
            int i1 = iblockaccess.getBlockMetadata(i, j, k);
            return l == i1 ? blockIndexInTexture + 1 : frontTexture;
        }
    }

    public int getBlockTextureFromSide(int i)
    {
        return i != 1 ? i != 0 ? i != 3 ? frontTexture : blockIndexInTexture + 1 : blockIndexInTexture + 17 : blockIndexInTexture + 17;
    }

    public void onBlockAdded(World world, int i, int j, int k)
    {
        super.onBlockAdded(world, i, j, k);
        setDispenserDefaultDirection(world, i, j, k);
    }

    public void onBlockPlacedBy(World world, int i, int j, int k, EntityLiving entityliving)
    {
        int l = MathHelper.floor_double((double)((entityliving.rotationYaw * 4F) / 360F) + 0.5D) & 3;
        if(l == 0)
        {
            world.setBlockMetadataWithNotify(i, j, k, 2);
        }
        if(l == 1)
        {
            world.setBlockMetadataWithNotify(i, j, k, 5);
        }
        if(l == 2)
        {
            world.setBlockMetadataWithNotify(i, j, k, 3);
        }
        if(l == 3)
        {
            world.setBlockMetadataWithNotify(i, j, k, 4);
        }
    }

    private void setDispenserDefaultDirection(World world, int i, int j, int k)
    {
        if(!world.multiplayerWorld)
        {
            int l = world.getBlockId(i, j, k - 1);
            int i1 = world.getBlockId(i, j, k + 1);
            int j1 = world.getBlockId(i - 1, j, k);
            int k1 = world.getBlockId(i + 1, j, k);
            byte byte0 = 3;
            if(Block.opaqueCubeLookup[l] && !Block.opaqueCubeLookup[i1])
            {
                byte0 = 3;
            }
            if(Block.opaqueCubeLookup[i1] && !Block.opaqueCubeLookup[l])
            {
                byte0 = 2;
            }
            if(Block.opaqueCubeLookup[j1] && !Block.opaqueCubeLookup[k1])
            {
                byte0 = 5;
            }
            if(Block.opaqueCubeLookup[k1] && !Block.opaqueCubeLookup[j1])
            {
                byte0 = 4;
            }
            world.setBlockMetadataWithNotify(i, j, k, byte0);
        }
    }

    public void onNeighborBlockChange(World world, int i, int j, int k, int l)
    {
        if(l > 0 && Block.blocksList[l].canProvidePower())
        {
            boolean flag = world.isBlockIndirectlyGettingPowered(i, j, k) || world.isBlockIndirectlyGettingPowered(i, j + 1, k);
            if(flag)
            {
                world.scheduleBlockUpdate(i, j, k, blockID, tickRate());
            }
        }
    }

	/* END OF COPY */
    public void updateTick(World world, int i, int j, int k, Random random)
    {
        if(!world.multiplayerWorld && (world.isBlockIndirectlyGettingPowered(i, j, k) || world.isBlockIndirectlyGettingPowered(i, j + 1, k)))
        {
            // Make minecart! :D
			/* BlockDispenser.java */
			int l = world.getBlockMetadata(i, j, k);
			int i1 = 0;
			int j1 = 0;
			if(l == 3)
			{
			    j1 = 2;
			} else
			if(l == 2)
			{
			    j1 = -2;
			} else
			if(l == 5)
			{
			    i1 = 2;
			} else
			{
			    i1 = -2;
			}
			double d = (double)i + (double)i1 * 0.59999999999999998D + 0.5D;
            double d1 = (double)j + 0.5D;
            double d2 = (double)k + (double)j1 * 0.59999999999999998D + 0.5D;
			/* END OF COPY */
			world.entityJoinedWorld(new EntityMinecart(world, d, d1, d2, minecartType));
        }
    }
}
