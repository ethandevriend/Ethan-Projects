package project.gamestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import project.managment.Sprite;
import project.managment.World;
import project.logic.Game;

public class PlayState implements GameState
{

	Sprite draw = new Sprite("draw_btn.png");
	Sprite uno = new Sprite("uno_btn.png");
	
	public void RenderGame(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		
		
		// draw buttons to the screen.
		g2d.drawImage(getSprite(draw), 805, 250, null);
		g2d.drawImage(getSprite(uno), 0, 250, null);
		
		// have the user choose a color!
		if(World.choose_color == true)
		{
			
			g2d.setColor(Color.blue);
			g2d.fillRect(550, 500, 50, 50);
			
			g2d.setColor(Color.red);
			g2d.fillRect(400, 500, 50, 50);
			
			g2d.setColor(Color.yellow);
			g2d.fillRect(450, 500, 50, 50);
			
			g2d.setColor(Color.green);
			g2d.fillRect(500, 500, 50, 50);

		}
		
		g2d.setColor(Color.white);
		
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		g2d.drawString("Your hand count: " + World.game.player.cardCount(), 30, 230);
		g2d.drawString("Your Opponent count:" + World.game.computer.cardCount(), 800, 230);
		
		if(World.game.lastPlayed.color == 0)
			g2d.drawString("The card color is red", 30, 150);
		if(World.game.lastPlayed.color == 1)
			g2d.drawString("The card color is yellow", 30, 150);
		if(World.game.lastPlayed.color == 2)
			g2d.drawString("The card color is green", 30, 50);
		if(World.game.lastPlayed.color == 3)
			g2d.drawString("The card color is blue", 30, 50);
		
		g2d.setColor(Color.white);
		
		// print out whose turn it is!
		if(World.players_turn)
		{
			g.setFont(new Font("TimesRoman", Font.PLAIN, 45));
			g2d.drawString("Its your turn", 400, 200);
		} else {
			g.setFont(new Font("TimesRoman", Font.PLAIN, 45));
			g2d.drawString("Its your opponents turn", 285, 200);
		}
		
		// render entity components
		World.game.player.RenderEntity(g2d);
		World.game.computer.RenderEntity(g2d);
		
		
		if(World.status == 0)
		{
			g2d.setColor(new Color(255, 255, 255, 200));
			g2d.fillRect(0, 0, 1000, 750);
			
			g2d.setColor(Color.black);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 100));
			g2d.drawString("YOU HAVE WON.", 100, 500);
			
		} else if(World.status == 1) 
		{
			g2d.setColor(new Color(255, 255, 255, 200));
			g2d.fillRect(0, 0, 1000, 750);
			
			g2d.setColor(Color.black);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 75));
			g2d.drawString("THE COMPUTER WINS.", 100, 500);
		}
		
	}
	

	public void MouseClicked(int x, int y)
	{
		// if the game has ended clicks will not work.
		if(World.status != -1)
			return;
		
		// if game isn't initialized yet, clicks can cause errors.
		if(World.game == null)
			return;

		// Uno Button
		if((x >= 7) && (y >= 279) &&  (y <= 366) && (x <= 191))
		{
			if(World.players_turn)
			{
				// the uno button has been called.
				if(World.game.player.cardCount() == 1 && !World.game.computer.enemyUnoCalled)
				{
					World.game.player.unoCalled = true;
				}
			} else if(World.game.computer.cardCount() == 1 && !World.game.computer.unoCalled)
				{
					World.game.player.enemyUnoCalled = true;
					World.game.computer.draw();
				}
				
				
		}
		
		// Draw Button
		if((x >= 806) && (y >= 280) && (y <= 366) && (x <= 995))
		{
			// not your turn, you cannot draw.
			if(!World.players_turn)
					return;
			
			// You are about to lose your turn!
			if(World.game.player.cardCount() >= 14)
			{
				World.status = 1;
				return;
			}
			
			World.game.player.draw();
			World.game.player.refreshHand();
			
			World.players_turn = false;
			
		}

		// Players Cards
		for(int i = 0; i < 14; i++)
		{
			if((x >= 5 + (i * 70)) && (y >= 630) && (y <= 739) && (x <= (70 * (1 + i)) + 5))
			{
			
				// Its the players turn, lets see if thats a legal move.
				if(World.players_turn)
				{
					// if that card has nothing in it we will be not allowing this change.
					if(World.game.player.getCard(i) != null)
					{
						World.game.card_picked = true;
						World.game.card_position = i;
					}
				}
						
			}
		}
		
		// new color is being chosen.
		if(World.choose_color == true)
		{
			for(int i = 0; i < 4; i++)
			{
				if((x >= 400 + (i * 50)) && (y >= 500) &&  (y <= 580) && (x <= 400 + ((1 + i) * 50)))
				{
							
					World.game.lastPlayed.color = i;
							
					World.choose_color = false;
				}
			}
					
				World.players_turn = false;
			}
		
	}
	
	
	
	public Image getSprite(Sprite s)
	{
		
		return s.getSprite();
	}
	
	public Image getSubSprite(Sprite s)
	{
		
		return s.getSubSprite();
	}


}
