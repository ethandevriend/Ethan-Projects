package project.gamestate;

import java.awt.Graphics;

public interface GameState
{

	/**
	 * @return This function renders the classes graphical contents
	 */
	public void RenderGame(Graphics g);

	/**
	 * @return This function handles mouse clicks.
	 */
	public void MouseClicked(int x, int y);
	
}
