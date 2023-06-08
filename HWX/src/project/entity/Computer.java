package project.entity;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import project.logic.Card;
import project.managment.Sprite;
import project.managment.World;

public class Computer extends Entity
{
	// arraylist of cards in hand.
	Card[] hand = new Card[14];
	private int length = 0;
	
	// uno card back for computers side.
	Sprite sprite = new Sprite("uno_back.png");
	
	
	// Constructor
	public Computer()
	{
		
	}
	
	// Draw function
	@Override
	public void draw() 
	{
		if(length >= 14)
		{
			World.status = 0;
			return;
		}
			
		Card card = World.deck.draw();
		
		// I am making the game avoid card 14. I could not get it to stop crashing.
		while(card.number == 14)
			card = World.deck.draw();
		
		for(int i = 0; i < hand.length; i++)
		{
			if(hand[i] == null)
			{
				hand[i] = card;
				length++;
				break;
			}
		}
		
	}
	
	// Remove card from hand.
	@Override
	public void removeCard(int id) 
	{
			hand[id] = null;
			length--;
	}
	
	
	/**
	 * The only thing that the we will be rendering is card backs!
	 */
	@Override
	public void RenderEntity(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		
		int z = 0;
		
		for(int i = 0; i < hand.length; i++)
		{
			if(hand[i] == null)
				continue;
			
			g2d.drawImage(getSprite(sprite), 30 + z, 5, sprite.getSprite().getWidth() / 2,
					sprite.getSprite().getHeight() / 2, null);
			z = i * 72;
		}
		
		Sprite lastCard = null;
		
		// draw last card played.
				if(World.game.lastPlayed.number <= 12)
				{
				lastCard = new Sprite("uno_cards_deck.png", 
						143 * World.game.lastPlayed.number, 214 * World.game.lastPlayed.color,
						World.card_width, World.card_height);
				
				}
				if(World.game.lastPlayed.number == 13)
				{
				lastCard = new Sprite("uno_cards_deck.png", 1855, 214,
						World.card_width, World.card_height);
				
				}
				if(World.game.lastPlayed.number == 14)
				{
				lastCard = new Sprite("uno_cards_deck.png", 1855, 5 * 214,
						World.card_width, World.card_height);
				
				}
				g2d.drawImage(getSubSprite(lastCard), 460, 320, lastCard.getSubSprite().getWidth() / 2
						, lastCard.getSubSprite().getHeight() / 2, null);
		
	}

	@Override
	public Card getCard(int id) 
	{
		return	hand[id];
	}
	
	@Override
	public int cardCount()
	{
		return length;
	}

	@Override
	public void refreshHand()
	{
		
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
