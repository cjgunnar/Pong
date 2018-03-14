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
	
	static final int DEFAULT_SPEED = 2;
	
	static final String NAME = "paddle";
	
	static final int DEFAULT_COLOR = 0;
	static final int DOUBLE_COLOR = 1;
	static final int SHRINK_COLOR = 2;
	
	public Paddle(int startPosX, int startPosY)
	{
		super(startPosX, startPosY);
		
		name = NAME;
		
		width = WIDTH;
		height = HEIGHT;
		
		speed = DEFAULT_SPEED;
	}
	
	public void draw(Graphics g)
	{
		if(colorMode == DEFAULT_COLOR)
			g.setColor(Color.white);
		else if(colorMode == DOUBLE_COLOR)
			g.setColor(Color.red);
		else if(colorMode == SHRINK_COLOR)
			g.setColor(Color.blue);
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
