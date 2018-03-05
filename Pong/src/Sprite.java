import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Abstract class of sprites
 * @author cjgunnar
 *
 */
public abstract class Sprite
{
	/** Used to identify in collisions */
	protected String name;
	
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
	
	/**
	 * Create a new Sprite with starting positions (x, y)
	 * @param startX X
	 * @param startY Y
	 */
	public Sprite(int startX, int startY)
	{
		x = startX;
		y = startY;
		
		name = "none";
	}
	
	/**
	 * Draw what the Sprite looks like
	 * @param g Graphics
	 */
	public abstract void draw(Graphics g);
	
	/**
	 * Called when this sprite collides with something
	 * @param other the sprite this collided with
	 */
	public abstract void onCollision(Sprite other);
	
	public void move()
	{
		//update position
		x += dx;
		y += dy;
	}
	
	/** Sets motion to 0 */
	public void stop()
	{
		dx = 0;
		dy = 0;
	}
	
	/**
	 * returns a rectangle representing space taken up by this sprite
	 * @return
	 */
	public Rectangle getBounds() 
	{
        return new Rectangle(x, y, width, height);
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
	 * Returns the direction the sprite is moving in on the x-axis
	 * @return direction sprite is moving (x)
	 */
	public int getDX()
	{
		return dx;
	}
	
	/**
	 * Set the direction and speed of y
	 * @param dy neg for reverse, use as speed
	 */
	public void setDY(int dy)
	{
		this.dy = dy;
	}
	
	/**
	 * Gets the direction the sprite is moving on the y-axis
	 * @return direction sprite moving (y)
	 */
	public int getDY()
	{
		return dy;
	}
	
	/**
	 * @return the x
	 */
	public int getX()
	{
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x)
	{
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY()
	{
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y)
	{
		this.y = y;
	}

	/**
	 * @return the height
	 */
	public int getHeight()
	{
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height)
	{
		this.height = height;
	}

	/**
	 * @return the width
	 */
	public int getWidth()
	{
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width)
	{
		this.width = width;
	}

	/**
	 * Sets the name of the sprite
	 * @param name Name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	 * Gets the name of the sprite
	 * @return the name of the sprite
	 */
	public String getName()
	{
		return name;
	}
}
