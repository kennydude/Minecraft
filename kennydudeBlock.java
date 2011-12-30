package net.minecraft.src;

import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            Block, Material

public class kennydudeBlock extends Block
{

    public kennydudeBlock(int i, int j)
    {
        super(i, j, Material.rock);
    }

    public int idDropped(int i, Random random)
    {
        return Block.cobblestone.blockID;
    }

}
