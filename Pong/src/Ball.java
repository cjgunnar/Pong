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
	
	static final String NAME = "ball";
	
	public Ball(int startPosX, int startPosY)
	{
		super(startPosX, startPosY);
		
		name = NAME;
		
		height = SIZE;
		width = SIZE;
		
		dx = -1;
		dy = -1;
	}
	
	public void draw(Graphics g)
	{
		g.setColor(Color.white);
		g.fillRect(x, y, width, height);
	}
	
	@Override
	public void onCollision(Sprite other)
	{
		//paddles will handle bouncing back, so do nothing if it hits a paddle
		
		//if it hits a wall, then bounce off it on the y-axis only
		if(other.getName() != null && other.getName().equals("wall"))
		{
			dy *= -1;
		}
	}
}
