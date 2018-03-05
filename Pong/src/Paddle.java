import java.awt.Color;
import java.awt.Graphics;

/**
 * Represents a paddle
 * @author cjgunnar
 *
 */
public class Paddle extends Sprite
{
	static final int WIDTH = 10;
	static final int HEIGHT = 100;
	
	static final int SPEED = 2;
	
	static final String NAME = "paddle";
	
	public Paddle(int startPosX, int startPosY)
	{
		super(startPosX, startPosY);
		
		name = NAME;
		
		width = WIDTH;
		height = HEIGHT;
	}
	
	public void draw(Graphics g)
	{
		g.setColor(Color.white);
		g.fillRect(x, y, width, height);
	}

	@Override
	public void move()
	{
		super.move();
		
		//check boundaries
		if(y < 1) //top
			y = 1;
		if(y + height > Board.HEIGHT) //bottom
			y = Board.HEIGHT - height;
	}
	
	@Override
	public void onCollision(Sprite other)
	{
		if(other.getName() != null && other.getName().equals(Ball.NAME))
		{
			//reflect it back
			other.setDX(-other.getDX());
		}
	}
}
