package net.minecraft.src;

import net.minecraft.src.*;
import me.kennydude.mod.ModConfig;

public class mod_kennydude extends BaseMod
{

	public static final ModConfig mcfg = new ModConfig().loadConfig("mod_kennydude.cfg");

	public static Block slippy;
    public static Block yourBlock;
	public static Block superLight;

	public static Block mcg;
	public static Block er;

    public mod_kennydude ()
    {
		int bI = mcfg.getInt("kennydudeBlock", 200);
		if(bI > 0){
			yourBlock = (new kennydudeBlock(bI, 86)).setBlockName("KennydudeBlock");
			yourBlock.blockIndexInTexture = ModLoader.addOverride("/terrain.png", "/kennydude/block.png");
			ModLoader.RegisterBlock(yourBlock);
			ModLoader.AddName(yourBlock, "Kennydude Block"); 
			addBlock(yourBlock);
		}

		bI = mcfg.getInt("slippyBlock", 201);
		if(bI > 0){
			slippy = (new SlippyBlock(bI, 86)).setBlockName("SlippyBlock").setHardness(0.5F);
			ModLoader.RegisterBlock(slippy);
			ModLoader.AddName(slippy, "Slippy Block");
			addBlock(slippy);
		}

		bI = mcfg.getInt("superLight", 202);
		if(bI > 0){
			superLight = (new SuperLight(bI)).setHardness(3F).setBlockName("SuperLight").setLightValue(5F);
			ModLoader.RegisterBlock(superLight);
			ModLoader.AddName(superLight, "Super Light");
			addBlock(superLight);
		}

		bI = mcfg.getInt("minecartGenerator", 207);
		if(bI > 0){
			mcg = (new MinecartGeneratorBlock(bI)).setHardness(3F).setBlockName("MineCartGen").setLightValue(5F);
			ModLoader.RegisterBlock(mcg);
			ModLoader.AddName(mcg, "Minecart Generator");
			addBlock(slippy);
		}

		bI = mcfg.getInt("ejectorRail", 208);
		if(bI > 0){
			er = (new EjectRailBlock(bI)).setBlockName("MineEject");
			er.blockIndexInTexture = ModLoader.addOverride("/terrain.png", "/kennydude/ejector.png"); 
			ModLoader.RegisterBlock(er);
			ModLoader.AddName(er, "Minecart Ejector Rail");
			addBlock(er);
		}

		mcfg.saveConfig();
	}
	public void load(){    
    }

	public void addBlock(Block id){
		try{
			CreativeAPI.addBlock(id);
		} catch (NoClassDefFoundError e) {
			ModLoader.getLogger().fine((new StringBuilder("Creative API not found! ")).toString());
        }
	}

    public String getVersion()
    {
        return "0.1";
    }

}
