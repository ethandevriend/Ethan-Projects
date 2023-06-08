package project.logic;

import java.util.Random;

import project.entity.Computer;
import project.entity.Entity;
import project.entity.Player;
import project.managment.Sprite;
import project.managment.World;

/**
 * This class will handle game logic.
 */
public class Game implements Runnable
{
	// our player & the computer
	public Entity player = new Player();
	public Entity computer = new Computer();
	
	Random rand = new Random();
	
	// card last played
	public Card lastPlayed;
	
	// card picked
	public boolean card_picked = false;
	// position in hand.
	public int card_position = -1;

	
	public Game()
	{
		
		//create a top of discard pile.
		lastPlayed = World.deck.draw();
		
		while(lastPlayed.number >= 13)
			lastPlayed = World.deck.draw();
		
		for(int i = 0; i < 7; i++)
		{
			player.draw();
			computer.draw();
		}
		
		player.refreshHand();
		
		Thread thread = new Thread(this);
		thread.start();
	}
  
	 // 0 - your turn
	 // 1 - computers turn
	public int turn = 0;
	
	public void resetChoice()
	{
		card_picked = false;
		card_position = -1;
	}


	// We want player and computer management separate
	// from the graphical updating.
	public void run()
	{
		// While there is no winner, lets manage turns!
		while(World.status == -1)
		{
			// The players turn is active.
			if(World.players_turn && !World.choose_color)
			{
				if(card_picked)
				{
					// a card has been selected.
					if(card_position >= 0)
					{
						Card card = player.getCard(card_position);
						
						// lets see if the card is like color or number.
						// it should be noted only cards 0-12 make it here by design.
						if(card == null)
							continue;
						
						if(lastPlayed.color == card.color || lastPlayed.number == card.number)
						{
							
							// the opponent must draw 2 cards.
							if(card.number == 12)
							{
								for(int z = 0; z < 2; z++)
								{
									computer.draw();
									player.removeCard(card_position);
									
									World.players_turn = false;
									continue;
									
								}
								
							}
							
							// nullpointer exception was coming from card 10,
							// so i added a check to make sure it doesn't happen.
							if(player.getCard(card_position) == null)
								continue;
							
							if(player.getCard(card_position).number == 10)
							{

								
								lastPlayed = player.getCard(card_position);
								player.removeCard(card_position);
								// do not allow for your turn to end. restart the loop.
								continue;
							}
							
							lastPlayed = player.getCard(card_position);
							player.removeCard(card_position);
							
							World.players_turn = false;
							continue;
							
							
							
						}
						
						if(player.getCard(card_position).number == 13)
						{
							World.choose_color = true;
								
							lastPlayed = player.getCard(card_position);
							player.removeCard(card_position);
							continue;
						}
																	
					}	
				}
				
			} else if(!World.players_turn && !World.choose_color) {
				// Its the computers turn.
				
					boolean move_made = false;
					
					if(computer.cardCount() == 1 && !player.enemyUnoCalled)
					{
						computer.unoCalled = true;
					} else if (player.cardCount() == 1 && !player.unoCalled)
					{
						computer.enemyUnoCalled = true;
						computer.draw();
					}
	
					// look through hand and match up card by color or id
					for(int i = 0; i < 14; i++)
					{
						
						// check other cards.
						if(computer.getCard(i) == null)
							continue;
					
						if(lastPlayed.color == computer.getCard(i).color || 
								lastPlayed.number == computer.getCard(i).number)
						{
							
							// the opponent must draw 2 cards.
							if(computer.getCard(i).number == 12)
							{
								for(int z = 0; z < 2; z++)
								{
									player.draw();
									World.game.player.refreshHand();
								}
							
							}
						
							if(computer.getCard(i).number == 10)
							{
								lastPlayed = computer.getCard(i);
								computer.removeCard(i);
								continue;
							}

							lastPlayed = computer.getCard(i);
							computer.removeCard(i);
						
							move_made = true;
							World.players_turn = true;
							break;
						}
					
					if(computer.getCard(i).number == 13)
					{
						
						lastPlayed = computer.getCard(i);
						computer.removeCard(i);
						
						lastPlayed.color = rand.nextInt(4);
						World.players_turn = true;
						move_made = true;

					}
					
					if(!move_made)
					{
						computer.draw();
						World.players_turn = false;
						break;
					}
					
				} // hand loop ends.
					
				
					if(computer.cardCount() == 0)
					{
						World.status = 1;
					}
					
					World.players_turn = true;
				
				}
			}
		} // this bracket ends the run function.
	
	
}

