package project.managment;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Sprite
{
	BufferedImage img = null;
	BufferedImage sub = null;
	
	/*
	 * Normal constructor
	 */
	public Sprite(String sprite)
	{
		
		loadImage(sprite);

	}
	
	/*
	 * Sprite-Sheet constructor
	 */
	public Sprite(String sprite, int x, int y, int width, int height)
	{
		
		loadImage(sprite, x, y, width, height);

	}
	
	/*
	 * Loads an entire image.
	 */
	private void loadImage(String name)
	{
		try
		{
			
			img = ImageIO.read(getClass().getResource("/sprites/" + name));
			
			//img = ImageIO.read(new File("\\sprites\\" + name));
		} catch (IOException e)
		{
			System.out.println("Image failed to load");
		}
	
	}
	
	/*
	 * Loads the specified part of the image.
	 */
	private void loadImage(String name, int x, int y, int width, int height)
	{
		try
		{
			img = ImageIO.read(getClass().getResource("/sprites/" + name));
			
			//img = ImageIO.read(new File("\\sprites\\" + name));
			sub = img.getSubimage(x, y, width, height);
			
		} catch (IOException e)
		{
			System.out.println("Image failed to load");
		}
	
	}
	
	/*
	 * Will return the normal whole image.
	 */
	public BufferedImage getSprite()
	{
		return img;
	}
	
	/*
	 * Returns the specified part of an image.
	 */
	
	public BufferedImage getSubSprite()
	{
		return sub;
	}

}