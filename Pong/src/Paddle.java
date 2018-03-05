import java.awt.Color;
import java.awt.Graphics;

/**
 * Represents a paddle
 * @author cjgunnar
 *
 */
public class Paddle extends Sprite
{
	static final int WIDTH = 20;
	static final int HEIGHT = 200;
	
	public Paddle(int startPosX, int startPosY)
	{
		super(startPosX, startPosY);
		
		width = WIDTH;
		height = HEIGHT;
	}
	
	public void draw(Graphics g)
	{
		g.setColor(Color.white);
		g.fillRect(x, y, WIDTH, HEIGHT);
	}
}
