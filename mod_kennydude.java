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

	public static Block dmp;
	public static Block dms;

    public mod_kennydude ()
    {
		int bI = mcfg.getInt("kennydudeBlock", 180);
		if(bI > 0){
			yourBlock = (new kennydudeBlock(bI, 86)).setBlockName("KennydudeBlock");
			yourBlock.blockIndexInTexture = ModLoader.addOverride("/terrain.png", "/kennydude/block.png");
			ModLoader.RegisterBlock(yourBlock);
			ModLoader.AddName(yourBlock, "Kennydude Block"); 
			addBlock(yourBlock);
		}

		bI = mcfg.getInt("slippyBlock", 181);
		if(bI > 0){
			slippy = (new SlippyBlock(bI, 86)).setBlockName("SlippyBlock").setHardness(0.5F);
			ModLoader.RegisterBlock(slippy);
			ModLoader.AddName(slippy, "Slippy Block");
			addBlock(slippy);
		}

		bI = mcfg.getInt("superLight", 182);
		if(bI > 0){
			superLight = (new SuperLight(bI)).setHardness(3F).setBlockName("SuperLight").setLightValue(5F);
			ModLoader.RegisterBlock(superLight);
			ModLoader.AddName(superLight, "Super Light");
			addBlock(superLight);
		}

		bI = mcfg.getInt("minecartGenerator", 183);
		if(bI > 0){
			mcg = (new MinecartGeneratorBlock(bI)).setHardness(3F).setBlockName("MineCartGen").setLightValue(5F);
			ModLoader.RegisterBlock(mcg);
			ModLoader.AddName(mcg, "Minecart Generator");
			addBlock(mcg);
		}

		bI = mcfg.getInt("ejectorRail", 184);
		if(bI > 0){
			er = (new EjectRailBlock(bI)).setBlockName("MineEject");
			er.blockIndexInTexture = ModLoader.addOverride("/terrain.png", "/kennydude/ejector.png"); 
			ModLoader.RegisterBlock(er);
			ModLoader.AddName(er, "Minecart Ejector Rail");
			addBlock(er);
		}

		bI = mcfg.getInt("directedProgrammer", 185);
		if(bI > 0){
			dmp = (new ProgramDirectedRailBlock(bI)).setBlockName("MineProgrammer");
			dmp.blockIndexInTexture = ModLoader.addOverride("/terrain.png", "/kennydude/programmer.png"); 
			ModLoader.RegisterBlock(dmp);
			ModLoader.AddName(dmp, "Directed Minecart Programmer Rail");
			addBlock(dmp);
		}

		bI = mcfg.getInt("directedSwitcher", 186);
		if(bI > 0){
			dms = (new DirectedChangeBlock(bI)).setBlockName("MineSwapper");
			dms.blockIndexInTexture = ModLoader.addOverride("/terrain.png", "/kennydude/switcher.png"); 
			ModLoader.RegisterBlock(dms);
			ModLoader.AddName(dms, "Directed Minecart Switcher");
			addBlock(dms);
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
