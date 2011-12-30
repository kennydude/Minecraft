package me.kennydude.mod;

import net.minecraft.src.World;

public class Location{
	public int x;
	public int y;
	public int z;

	public World world;

	public Location(World world, int x, int y, int z){
		this.x = x;
		this.y = y;
		this.z = z;
		this.world = world;
	}
}
