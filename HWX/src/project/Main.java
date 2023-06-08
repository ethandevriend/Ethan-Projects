package project;

import project.gamestate.StartState;
import project.logic.Deck;
import project.logic.Game;
import project.managment.Window;
import project.managment.World;

public class Main
{

	/**
	 * Construct the window
	 * Initiate game information.
	 */
	public static void main(String[] args)
	{
		// Create the window.
		Window window = new Window(1000, 750);
		
		newGame();
		
	}
	
	public static void newGame()
	{
		World.deck = new Deck();
		
		World.state = new StartState();	
		World.game = new Game();
	}

}
