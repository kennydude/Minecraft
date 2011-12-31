package net.minecraft.src;

import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            Block, Material

public class SuperLight extends Block
{

    public SuperLight(int i)
    {
        super(i, Material.rock);
		setLightValue(5F);
    }
}
