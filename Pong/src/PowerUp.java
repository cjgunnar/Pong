import java.awt.Color;
import java.awt.Graphics;

/**
 * Item that changes the way the game works, lasts temporary
 * @author cjgunnar
 */
public abstract class PowerUp extends Sprite
{
	/** How long the powerup effect will last */
	int duration;
	/** How long the powerup item will last */
	int despawnTime;
	/** How long this item has been on the board */
	int timeAlive;
	
	/** The radius of the PowerUp */
	static final int SIZE = 20;
	
	/** The color of the power up */
	Color color;
	
	/** Is this powerup active? */
	boolean active;
	
	boolean isDespawned;
	
	boolean effectEnded;
	
	Sprite activeSprite;
	
	static String NAME = "powerup";
	
	public PowerUp(int startX, int startY)
	{
		super(startX, startY);

		timeAlive = 0;
		duration = 10;
		despawnTime = 20;
		color = Color.blue;
		
		height = SIZE;
		width = SIZE;
		
		name = NAME;
		
		isDespawned = false;
		effectEnded = false;
	}
	
	public PowerUp(int startX, int startY, int duration, int despawnTime, Color color)
	{
		super(startX, startY);
		this.duration = duration;
		this.despawnTime = despawnTime;
		this.color = color;
		
		height = SIZE;
		width = SIZE;
		
		name = NAME;
		
		isDespawned = false;
		effectEnded = false;
	}

	public void age()
	{
		timeAlive++;
		
		//use until duration exceeded
		if(active)
		{
			//end the effect
			if(timeAlive > duration)
			{
				stopEffect();
				effectEnded = true;
			}
		}
		else
		{
			//despawn the item
			if(timeAlive > despawnTime)
			{
				isDespawned = true;
			}
		}
	}
	
	public abstract void startEffect();
	
	public abstract void stopEffect();
	
	/**
	 * @return the isDespawned
	 */
	public boolean isDespawned()
	{
		return isDespawned;
	}

	/**
	 * @return the effectEnded
	 */
	public boolean isEffectEnded()
	{
		return effectEnded;
	}

	@Override
	public void draw(Graphics g)
	{
		g.setColor(color);
		g.drawArc(x, y, width, height, 0, 360);
	}

	@Override
	public void onCollision(Sprite other)
	{
		if(other.getName() != null && other.getName().equals(Ball.NAME))
		{
			if(visible)
			{
				visible = false;
				startEffect();
			}
			
		}
	}
	
}
