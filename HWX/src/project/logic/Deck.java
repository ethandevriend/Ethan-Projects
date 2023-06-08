package project.logic;

import java.util.Arrays;
import java.util.Collections;

/**
 * There are 108 cards in a deck.
 * Two of each card, except for:
 * one of each 0, four wild cards, four wild draws.
 * 
 */
public class Deck
{
	
	// create a 56-count deck.
	Card[] card = new Card[56];
	
	// this integer refers where in the deck we are.
	int depth = 0;
	
	public Deck()
	{
		
		for(int i = 0; i < card.length; i++)
			card[i] = new Card();

		// initialize card information and then shuffle deck.
		initalize();
		shuffle();		
		
	}
	
	private void shuffle()
	{
		Collections.shuffle(Arrays.asList(card));
	}
	
	/**
	 * Return the card we drew.
	 */
	public Card draw()
	{
		// the deck is empty!
		if(depth >= 55)
		{
			// I completing null checks everytime I call draw.
			return null;
		}
		
		Card drew = card[depth];
		depth++;
		
		return drew;
	}
	
	/**
	 * Initializes the values for the cards.
	 */
	private void initalize()
	{
		
		// Set the cards colors.
		for(int i = 0; i < 13; i++)
		{
			card[i].color = 0;		// cards 0-12 are yellow.
			card[i + 13].color = 1;	// cards 13-25 are red.
			card[i + 26].color = 2; // cards 26-38 are blue.
			card[i + 39].color = 3; // cards 39-51 are green.
		}
		for(int i = 0; i < 4; i++)
		{
			card[i + 52].color = -1; // cards 52-56 are wild cards.
		}
		
		// Set the cards numbers.
		
		int offset = 0;
		
		for(int i = 0; i < 4; i++)
		{
			card[0 + offset].number = 0;
			card[1 + offset].number = 1;
			card[2 + offset].number = 2;
			card[3 + offset].number = 3;
			card[4 + offset].number = 4;
			card[5 + offset].number = 5;
			card[6 + offset].number = 6;
			card[7 + offset].number = 7;
			card[8 + offset].number = 8;
			card[9 + offset].number = 9;
			card[10 + offset].number = 10;
			card[11 + offset].number = 11;
			card[12 + offset].number = 12;
		
			offset += 13;
		}
			
		// Wild cards
			card[52].number = 13;
			card[53].number = 13;
			card[54].number = 13;
			card[55].number = 13;
			

	}
	
	

}
