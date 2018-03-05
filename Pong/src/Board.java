import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Contains the paddles, walls, score, and runs update timer/keyboard input
 * @author cjgunnar
 *
 */
@SuppressWarnings("serial")
public class Board extends JPanel implements ActionListener
{
	/** Width of the Board */
	final static int WIDTH = 700;
	/** Height of the Board */
	final static int HEIGHT = 500;
	
	/** Thickness of the border walls */
	final static int WALL_THICKNESS = 2;
	
	/** Time between frames (ms) */
	final static int FRAME_DELAY = 5;
	
	/** the frame updater */
	private Timer timer;
	
	/** The paddle on the left */
	Paddle leftPad;
	/** The paddle on the right */
	Paddle rightPad;
	
	/** The ball getting hit around */
	Ball ball;
	
	/** Scoreboard showing the current scores */
	ScoreBoard sb;
	
	/** List of all the sprites */
	ArrayList<Sprite> sprites;
	
	/** Create the Board */
	public Board()
	{
		createSprites();
		
		//set the background to be black
		setBackground(Color.black);
		
		//add the input handler to listen for keys
		addKeyListener(new InputHandler());
		//allow the board to listen
		setFocusable(true);
		
		setSize(WIDTH, HEIGHT);
		
		timer = new Timer(FRAME_DELAY, this);
		timer.start();
	}
	
	/** Create all the sprites */
	private void createSprites()
	{
		sprites = new ArrayList<Sprite>();
		
		int distFromWall = 10;

		//set starting positions of paddles (x, y) so they are centered on height and distFromWall away from L/R border
		leftPad = new Paddle(distFromWall, (HEIGHT / 2) - (Paddle.HEIGHT / 2));
		
		rightPad = new Paddle(PongWindow.WIDTH - Paddle.WIDTH - distFromWall, (HEIGHT / 2) - (Paddle.HEIGHT) / 2);
		
		//set starting position of ball in center of screen
		ball = new Ball(PongWindow.WIDTH / 2 - Ball.SIZE / 2, HEIGHT / 2 - Ball.SIZE);
		
		createWalls();
		
		sb = new ScoreBoard(PongWindow.WIDTH / 2 - 300, 45);
		
		//add created sprites to sprites list
		sprites.add(leftPad);
		sprites.add(rightPad);
		sprites.add(ball);
		sprites.add(sb);
	}
	
	/** Creates the boundary walls */
	private void createWalls()
	{
		//create the top wall
		Sprite topWall = new Sprite(0,0)
		{
			@Override
			public void draw(Graphics g)
			{
				g.setColor(Color.white);
				g.fillRect(x, y, PongWindow.WIDTH, WALL_THICKNESS);
			}
			@Override
			public void onCollision(Sprite other)
			{
				// walls won't be colliding with things
				return;
			}
		};
		topWall.setWidth(PongWindow.WIDTH);
		topWall.setHeight(WALL_THICKNESS);
		topWall.setName("wall");
		
		//create the bottom wall
		Sprite bottomWall = new Sprite(0, HEIGHT)
		{
			@Override
			public void draw(Graphics g)
			{
				g.setColor(Color.white);
				g.fillRect(x, y, PongWindow.WIDTH, WALL_THICKNESS);
			}
			@Override
			public void onCollision(Sprite other)
			{
				// walls won't be colliding with things
				return;
			}
		};
		bottomWall.setWidth(PongWindow.WIDTH);
		bottomWall.setHeight(WALL_THICKNESS);
		bottomWall.setName("wall");
		
		sprites.add(topWall);
		sprites.add(bottomWall);
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		//paints the background black
		super.paintComponent(g);
		
		//draw all the sprites
		for(Sprite sprite: sprites)
			sprite.draw(g);
	}

	/** Checks all sprites in sprite list for intersections and calls onCollision on them */
	private void checkCollisions()
	{
		if(sprites == null) {System.out.println("null sprites");}
		for(Sprite a: sprites)
		{
			Rectangle boundA = a.getBounds();
			for(Sprite b: sprites)
			{
				//if is is the same sprite, skip it
				if(a.equals(b))
					continue;
				
				Rectangle boundB = b.getBounds();
				
				//if they intersect, call the collision function
				if(boundA.intersects(boundB))
					a.onCollision(b);
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		//move everything
		ball.move();
		leftPad.move();
		rightPad.move();
		
		checkCollisions();			
		
		//check if someone scored
		
		//went out on left, right scored
		if(ball.getX() < 0)
		{
			sb.scoreRight();
			newBall();
		}
		//went out on right, left scored
		else if(ball.getX() > PongWindow.WIDTH)
		{
			sb.scoreLeft();
			newBall();
		}
		
		//redraw the screen
		repaint();
	}
	
	/** resets the ball and randomly serves it again */
	private void newBall()
	{
		//stop the ball from moving
		ball.stop();
		
		//set it back to center of court
		ball.setX(PongWindow.WIDTH / 2 - Ball.SIZE / 2);
		ball.setY(HEIGHT / 2 - Ball.SIZE);
		
		//randomly decide who to serve ball back to
		if(Math.random() * 2 >= 1)
			ball.setDX(-1);
		else
			ball.setDX(1);
		
		//serve up or down?
		if(Math.random() * 2 >= 1)
			ball.setDY(-1);
		else
			ball.setDY(1);
		
	}
	
	/**
	 * Handles keyboard input to move sprites
	 * @author cjgunnar
	 *
	 */
	private class InputHandler extends KeyAdapter
	{
		/**
		 * CONTROLS
		 * UP ARROW - RIGHT PADDLE UP
		 * DOWN ARROW - RIGHT PADDLE DOWN
		 * 
		 * W KEY - LEFT PADDLE UP
		 * S KEY - LEFT PADDLE DOWN
		 */
		
		final static int RIGHT_UP = KeyEvent.VK_UP;
		final static int RIGHT_DOWN = KeyEvent.VK_DOWN;
		
		final static int LEFT_UP = KeyEvent.VK_W;
		final static int LEFT_DOWN = KeyEvent.VK_S;
		
		@Override
		public void keyPressed(KeyEvent e)
		{
			int key = e.getKeyCode();
			
			int speed = Paddle.SPEED;
			
			//respond to keypress
			if(key == RIGHT_UP)
			{
				rightPad.setDY(-speed);
			}
			else if(key == RIGHT_DOWN)
			{
				rightPad.setDY(speed);
			}
			else if(key == LEFT_UP)
			{
				leftPad.setDY(-speed);
			}
			else if(key == LEFT_DOWN)
			{
				leftPad.setDY(speed);
			}
		}
		
		@Override
		public void keyReleased(KeyEvent e)
		{
			int key = e.getKeyCode();
			
			//stop moving when key released
			if(key == RIGHT_UP)
			{
				rightPad.setDY(0);
			}
			else if(key == RIGHT_DOWN)
			{
				rightPad.setDY(0);
			}
			else if(key == LEFT_UP)
			{
				leftPad.setDY(0);
			}
			else if(key == LEFT_DOWN)
			{
				leftPad.setDY(0);
			}
		}
	}
}
