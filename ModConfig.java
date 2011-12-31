package me.kennydude.mod;

import net.minecraft.src.*;
import java.util.Properties;
import java.io.File;
import net.minecraft.client.Minecraft;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Provides an easy to use config for Mods
 * @author kennydude
 */
public class ModConfig{
	public ModConfig(){
	}

	File cfgdir = new File(Minecraft.getMinecraftDir(), "/config/");
	Properties props = new Properties();
	File cfgfile;
	
	/**
	 * This loads the configuration from where you please
	 */
	public ModConfig loadConfig(String filename){
		try{
			cfgfile = new File(cfgdir, filename);
			if(!cfgfile.getParentFile().exists()){
				cfgfile.getParentFile().mkdirs();
			}
			if(cfgfile.exists()){
				FileInputStream fileinputstream = new FileInputStream(cfgfile);
		        props.load(fileinputstream);
		        fileinputstream.close();
			}
		} catch(Exception e){
			ModLoader.getLogger().fine((new StringBuilder(e.toString())).toString());
		}
		return this;
	}

	/**
	 * Saves it back
	 */
	public void saveConfig(){
		try{
			FileOutputStream fileoutputstream = new FileOutputStream(cfgfile);
		    props.store(fileoutputstream, "Mod Config");
		    fileoutputstream.close();
		} catch(Exception e){
			ModLoader.getLogger().fine((new StringBuilder(e.toString())).toString());
		}
	}

	/**
	 * Gets a property. If it doesn't exist, the default is returned and set
	 */
	public String getProperty(String propName, String def){
		String val = props.getProperty(propName);
		if(val == null){
			val = def;
			props.setProperty(propName, val);
		}
		return val;
	}

	/**
	 * Same as getProperty() but uses Integers
	 */
	public Integer getInt(String propName, Integer def){
		return Integer.parseInt(getProperty(propName, def.toString()));
	}
}
