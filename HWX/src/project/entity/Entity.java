package project.entity;

import java.awt.Graphics;
import java.awt.Image;

import project.logic.Card;
import project.managment.Sprite;

public abstract class Entity 
{
	public boolean enemyUnoCalled;
	
	public boolean unoCalled;

	public abstract void RenderEntity(Graphics g);

	public abstract Card getCard(int i);
	
	public abstract int cardCount();
	
	public abstract void draw();
	
	public abstract void removeCard(int id);
	
	public abstract void refreshHand();

}
