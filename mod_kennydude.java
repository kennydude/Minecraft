package net.minecraft.src;

import net.minecraft.src.*;

public class mod_kennydude extends BaseMod
{

	public static final Block slippy = (new SlippyBlock(201, 86)).setBlockName("SlippyBlock").setHardness(0.5F);
    public static final Block yourBlock = (new kennydudeBlock(200, 86)).setBlockName("KennydudeBlock");
	public static final Block superLight = (new SuperLight(202)).setHardness(3F).setBlockName("SuperLight").setLightValue(5F);

	public static final Block mcg = (new MinecartGeneratorBlock(207)).setHardness(3F).setBlockName("MineCartGen").setLightValue(5F);
	public static final Block er = (new EjectRailBlock(208)).setBlockName("MineEject");

    public mod_kennydude ()
    {

		yourBlock.blockIndexInTexture = ModLoader.addOverride("/terrain.png", "/kennydude/block.png");
		er.blockIndexInTexture = ModLoader.addOverride("/terrain.png", "/kennydude/ejector.png"); 
		
		ModLoader.RegisterBlock(yourBlock);
    	ModLoader.AddName(yourBlock, "Kennydude Block");  

		ModLoader.RegisterBlock(slippy);
		ModLoader.AddName(slippy, "Slippy Block");

		ModLoader.RegisterBlock(superLight);
		ModLoader.AddName(superLight, "Super Light");

		ModLoader.RegisterBlock(mcg);
		ModLoader.AddName(mcg, "Minecart Generator");

		ModLoader.RegisterBlock(er);
		ModLoader.AddName(er, "Minecart Ejector Rail");
	

		CreativeAPI.addBlock(yourBlock);
		CreativeAPI.addBlock(slippy);
		CreativeAPI.addBlock(superLight);
		CreativeAPI.addBlock(mcg);
		CreativeAPI.addBlock(er);
	}
	public void load(){    
    }

    public String getVersion()
    {
        return "0.1";
    }

}
