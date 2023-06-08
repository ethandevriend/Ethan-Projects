package project.managment;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;


import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import project.gamestate.GameState;
import project.gamestate.StartState;

public class Window extends JPanel implements MouseListener, MouseMotionListener
{

	private JFrame frame;
	
	private int WIDTH = 0;
	private int HEIGHT = 0;
	
	public Window(int height, int width)
	{
		WIDTH = width;
		HEIGHT = height;
		CreateWindow();
		
	}

	private void CreateWindow() 
	{

		frame = new JFrame("UNO! The Card Game!");
		frame.setSize(HEIGHT, WIDTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setContentPane(this);
		frame.setResizable(false);
		frame.addMouseListener((MouseListener) this);
		frame.addMouseMotionListener((MouseMotionListener)this);
		
	}
	
	@Override
	public void paintComponent(Graphics g)
	{	
		Graphics2D g2d = (Graphics2D) g;
		
		// My attempt to make a card table.
		g2d.setColor(new Color(139, 69, 19));
		g2d.fillRect(0, 0, HEIGHT, WIDTH);
		g2d.setColor(new Color(0, 153, 0));
		g2d.fillRect(50, 50, 900, 625);
		
		g2d.setColor(Color.WHITE);
		
		if(World.state != null)
			World.state.RenderGame(g);
	}
	
	
	public void mouseClicked(MouseEvent arg0)
	{
		
		// Mouse clicks are sent to appropriate GameState
		if(World.state != null)
			World.state.MouseClicked(arg0.getX(), arg0.getY());
			
	}
	
	public void mouseMoved(MouseEvent arg0) 
	{

		repaint();
		
	}

	
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
}
