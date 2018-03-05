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
	
	int height;
	int width;
	
	public Sprite(int startX, int startY)
	{
		x = startX;
		y = startY;
	}
	
	public abstract void draw(Graphics g);
	
	public void move()
	{
		x += dx;
		y += dy;
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
