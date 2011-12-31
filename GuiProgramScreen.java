package net.minecraft.src;

import java.awt.Color;
import java.io.IOException;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.src.GuiButton;
import org.lwjgl.input.Keyboard;
import net.minecraft.src.EntityPlayer;

public class GuiProgramScreen extends GuiScreen
{
	// public String[] stations = new String[]{ "", "", "" };
	String screenTitle = "Station List";
    private int updateCounter;
    private int editLine;
    private static final String allowedCharacters;

	TileEntitySign tes;
	
	public GuiProgramScreen(World world1, EntityPlayer ep, Minecraft minecraft, TileEntitySign tes) {
		mc = minecraft;
		world = world1;
		this.ep = ep;	
		this.tes = tes;
		editLine = 0;
	}

	public void initGui() {       
		controlList.clear();
        Keyboard.enableRepeatEvents(true);
        controlList.add(new GuiButton(0, width / 2 - 100, height / 4 + 120, "Done"));
	}

	protected void actionPerformed(GuiButton guibutton) //Actions taken by the gui (mostly for gui buttons)
	{
        if(!guibutton.enabled)
        {
            return;
        }
        if(guibutton.id == 0)
        {
            // entitySign.onInventoryChanged();
            mc.displayGuiScreen(null);
        }
	}

    protected void keyTyped(char c, int i)
    {
        if(i == 200)
        {
            editLine = editLine - 1 & 3;
			if(editLine < 0){
				editLine = tes.signText.length - 1;
			}
        }
        if(i == 208 || i == 28)
        {
            editLine = editLine + 1 & 3;
			if(editLine > tes.signText.length - 1){
				editLine = 0;
			}
        }
        if(i == 14 && tes.signText[editLine].length() > 0)
        {
       		tes.signText[editLine] = tes.signText[editLine].substring(0, tes.signText[editLine].length() - 1);
        }
        if(allowedCharacters.indexOf(c) >= 0 && tes.signText[editLine].length() < 15)
        {
        	tes.signText[editLine] += c;
        }
    }


	public void onGuiClosed() //states what happens when the gui is closed
	{
		Keyboard.enableRepeatEvents(false);
	}

	public void drawScreen(int i, int j, float f) //What is displayed in the gui
	{
		drawDefaultBackground();
		drawCenteredString(fontRenderer, screenTitle, width / 2, 40, 0xffffff);

		for(int k = 0; k < tes.signText.length; k++) {
			String s = tes.signText[k];
			if(k == editLine) {
				s = (new StringBuilder()).append("> ").append(s).append(" <").toString();
				drawCenteredString(fontRenderer,s, width / 2, 60 + (20*k), 0xffffff);
			} else
			{
				drawCenteredString(fontRenderer,s, width / 2, 60 + (20*k), 0xffffff);
			}
		}

		for(int k = 0; k < controlList.size(); k++) {
			GuiButton guibutton = (GuiButton)controlList.get(k);
			guibutton.drawButton(mc, i, j);
		}

		super.drawScreen(i, j, f);
	}

	public boolean doesGuiPauseGame()
	{
		return true; //as it says, registers if the gui pauses the game.
	}

	private World world;
	private Minecraft mc; //Variable for minecraft
	public EntityPlayer ep;

    static 
    {
        allowedCharacters = ChatAllowedCharacters.allowedCharacters;
    }
}
