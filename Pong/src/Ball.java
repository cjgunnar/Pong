import java.awt.Color;
import java.awt.Graphics;

/**
 * The ball that gets hit around
 * @author cjgunnar
 *
 */
public class Ball extends Sprite
{
	static final int SIZE = 10;
	
	int dx;
	int dy;
	
	public Ball(int startPosX, int startPosY)
	{
		super(startPosX, startPosY);
		
		height = SIZE;
		width = SIZE;
	}
	
	public void draw(Graphics g)
	{
		g.setColor(Color.white);
		g.fillRect(x, y, width, height);
	}
}
