import java.awt.Graphics;

/**
 * Abstract class of sprites
 * @author cjgunnar
 *
 */
public abstract class Sprite
{
	int x;
	int y;
	
	int height;
	int width;
	
	public Sprite(int startX, int startY)
	{
		x = startX;
		y = startY;
	}
	
	public abstract void draw(Graphics g);
}
