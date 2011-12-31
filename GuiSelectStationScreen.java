package net.minecraft.src;

import java.awt.Color;
import java.io.IOException;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.src.GuiButton;
import org.lwjgl.input.Keyboard;
import net.minecraft.src.EntityPlayer;

public class GuiSelectStationScreen extends GuiScreen
{
	String screenTitle = "Select your destination";
    private int updateCounter;
    private int editLine;

	EntityDirectedMinecart cart;
	
	public GuiSelectStationScreen(Minecraft minecraft, EntityDirectedMinecart tes) {
		mc = minecraft;
		this.cart = tes;
		editLine = 0;
	}


	public void onGuiClosed() //states what happens when the gui is closed
	{
		Keyboard.enableRepeatEvents(false);
	}

	public void initGui() {       
		controlList.clear();
        Keyboard.enableRepeatEvents(true);

		// Add all stations!
		int i = 0;
		for(String station : cart.stations){
        	controlList.add(new GuiButton(i, width / 2 - 100, 60 + (30*i), station));
			i = i + 1;
		}
	}


	public void drawScreen(int i, int j, float f) //What is displayed in the gui
	{
		drawDefaultBackground();
		drawCenteredString(fontRenderer, screenTitle, width / 2, 40, 0xffffff);

		for(int k = 0; k < controlList.size(); k++) {
			GuiButton guibutton = (GuiButton)controlList.get(k);
			guibutton.drawButton(mc, i, j);
		}

		super.drawScreen(i, j, f);

	}

	protected void actionPerformed(GuiButton guibutton) //Actions taken by the gui (mostly for gui buttons)
	{
		if(!guibutton.enabled)
        {
            return;
        }
		cart.station = cart.stations.get(guibutton.id);
		mc.displayGuiScreen(null);
	}

	public boolean doesGuiPauseGame()
	{
		return false; //as it says, registers if the gui pauses the game.
	}

	private Minecraft mc; //Variable for minecraft

}
