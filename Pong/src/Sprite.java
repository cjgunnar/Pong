import java.awt.Graphics;

/**
 * Abstract class of sprites
 * @author cjgunnar
 *
 */
public abstract class Sprite
{
	/** Current x pos */
	int x;
	/** Current y pos */
	int y;
	
	/** Direction of x */
	int dx;
	/** Direction of y */
	int dy;
	
	/** Height of the sprite */
	int height;
	/** Width of the sprite */
	int width;
	
	public Sprite(int startX, int startY)
	{
		x = startX;
		y = startY;
	}
	
	/**
	 * Draw what the Sprite looks like
	 * @param g Graphics
	 */
	public abstract void draw(Graphics g);
	
	public void move()
	{
		//update position
		x += dx;
		y += dy;
		
		//check boundaries
		if(x < 1) //left
			x = 1;
		if(y < 1) //top
			y = 1;
		if(x > PongWindow.WIDTH) //right
			x = PongWindow.WIDTH;
		if(y + height > PongWindow.HEIGHT) //bottom
			y = PongWindow.HEIGHT - height;
	}
	
	/**
	 * Set the direction and speed of x
	 * @param dx neg for reverse, use as speed
	 */
	public void setDX(int dx)
	{
		this.dx = dx;
	}
	
	/**
	 * Set the direction and speed of y
	 * @param dy neg for reverse, use as speed
	 */
	public void setDY(int dy)
	{
		this.dy = dy;
	}
}
