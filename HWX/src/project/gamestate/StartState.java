package project.gamestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import project.managment.Sprite;
import project.managment.World;

public class StartState implements GameState
{
	Sprite start = new Sprite("start_btn.png");
	
	public void RenderGame(Graphics g)
	{	
		Graphics2D g2d = (Graphics2D) g;
		
			g2d.setColor(new Color(255,255, 255,200));
			g2d.fillRect(30, 15, 937, 575);
			g2d.drawImage(getSprite(start), 250, 605, null);
			
			g2d.setColor(Color.black);
			
			g.setFont(new Font("TimesRoman", Font.PLAIN, 45));
			g2d.drawString("Welcome to UNO!", 285, 100);
			
			g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
			g2d.drawString("UNO is a 2+ player card game where the objective of the game is to empty your hand.", 100, 200);
			
			g2d.drawString("A game is started by each player drawing seven cards, and a base card being placed.", 100, 250);
			g2d.drawString("You then play like cards by either color or number, taking turns. ", 100, 290);
			g2d.drawString("If you have one card press UNO as fast as you can or you will have to draw a card. ", 100, 325);
			g2d.drawString("If you are unable to play a card you must draw and your turn will end.  ", 100, 360);
			g2d.drawString("Your hand will have a maximum cap of 14 cards for spacial reasons. ", 100, 400);
			
			
			g2d.drawString("If you have any more questions about how to play visit: ", 200, 500);
			g2d.drawString(" http://www.wikihow.com/Play-UNO ", 275, 550);
			
		
	}
	
	public void MouseClicked(int x, int y)
	{
		if((x >= 251) && (y >= 635) && (y <= 726) && (x <= 759))
		{
			if((World.state != null) && (World.game != null))
				World.state = new PlayState();
		}
	}
	
	public Image getSprite(Sprite s)
	{
		
		return s.getSprite();
	}


}
