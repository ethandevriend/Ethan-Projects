package project.entity;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import project.logic.Card;
import project.managment.Sprite;
import project.managment.World;

public class Player extends Entity
{

	/**
	 *  hand - The cards in our hands.
	 *  sprite - The sprite's to our cards.
	 */
	private Card[] hand = new Card[14];
	private Sprite[] sprite = new Sprite[14];
	private int length = 0;
	

	public Player()
	{
		

		
	}
	
	@Override
	public void draw()
	{
		if(length >= 14)
		{
			// computer just won.
			World.status = 1;
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
	
	// remove this card from your hand.
	@Override
	public void removeCard(int i)
	{
		hand[i] = null;
		length--;
		
		if(length == 0)
		{
			World.status = 0;
		}
		
		sprite[i] = null;
	}
	
	@Override
	public void RenderEntity(Graphics g)
	{
		
		Graphics2D g2d = (Graphics2D) g;
		
		int z = 0;
		
		
		for(int i = 0; i < hand.length; i++)
		{
			
			if(sprite[i] == null)
			{
				z+= 70;
				continue;
			}
				
			g2d.drawImage(getSubSprite(sprite[i]), 5 + z, 600,
					sprite[i].getSubSprite().getWidth() / 2, 
					sprite[i].getSubSprite().getHeight() / 2, null);
			
			z += 70;
			
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
	
	public void getCardSprites()
	{
		for(int i = 0; i < hand.length; i++)
		{
			if(hand[i] == null)
				continue;
			
			if(hand[i].number <= 12)
			{
				sprite[i] = new Sprite("uno_cards_deck.png",
						143 * hand[i].number, 214 * hand[i].color, World.card_width, World.card_height);
			}
			
			if(hand[i].number == 13)
			{
				sprite[i] = new Sprite("uno_cards_deck.png", 1855, 214,
						World.card_width, World.card_height);
			}
			
			if(hand[i].number == 14)
			{
				sprite[i] = new Sprite("uno_cards_deck.png", 1855, 5 * 214,
						World.card_width, World.card_height);
			}
			
		}
	}
	
	@Override
	public Card getCard(int id)
	{
		
		return hand[id];
	}
	
	@Override
	public int cardCount()
	{
	
		return length;
	}

	public void refreshHand()
	{
		getCardSprites();
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
