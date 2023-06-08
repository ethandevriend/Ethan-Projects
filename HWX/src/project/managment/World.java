package project.managment;

import project.gamestate.GameState;
import project.logic.Deck;
import project.logic.Game;

/**
 * This class will act as a container for important game information.
 */
public class World
{
	// The Game State
	public static GameState state;
	
	// The Deck
	public static Deck deck;
	
	// Game session
	public static Game game;
	
	
	// Card rendering details.
	public static int card_width = 143;
	public static int card_height = 215;
	
	// Game status
	// 0 for win, 1 for loss
	public static int status = -1;
	
	// It will be the players turn by default.
	public static boolean players_turn = true;
	
	public static boolean choose_color = false;
	

	
}
