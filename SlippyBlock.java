package net.minecraft.src;

import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            Block, Material

public class SlippyBlock extends Block
{

    public SlippyBlock(int i, int j)
    {
        super(i, j, Material.rock);
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k)
    {
        float f = 0.125F;
        return AxisAlignedBB.getBoundingBoxFromPool(i, j, k, i + 1, (float)(j + 1) - f, k + 1);
    }

	public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity)
    {
		entity.motionX *= 1.2;
        entity.motionZ *= 1.2;
	}
}
