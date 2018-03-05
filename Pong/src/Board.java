import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
	final static int WALL_THICKNESS = 10;
	
	final static int FRAME_DELAY = 5;
	
	private Timer timer;
	
	Paddle leftPad;
	Paddle rightPad;
	
	Ball ball;
	
	public Board()
	{
		int distFromWall = 10;

		//set starting positions of paddles (x, y) so they are centered on height and distFromWall away from L/R border
		leftPad = new Paddle(distFromWall, (PongWindow.HEIGHT / 2) - (Paddle.HEIGHT / 2));
		
		rightPad = new Paddle(PongWindow.WIDTH - Paddle.WIDTH - distFromWall, (PongWindow.HEIGHT / 2) - (Paddle.HEIGHT) / 2);
		
		//set starting position of ball in center of screen
		ball = new Ball(PongWindow.WIDTH / 2 - Ball.SIZE / 2, PongWindow.HEIGHT / 2 - Ball.SIZE);
		
		//ball.setDX(1);
		
		//set the background to be black
		setBackground(Color.black);
		
		//add the input handler to listen for keys
		addKeyListener(new InputHandler());
		//allow the board to listen
		setFocusable(true);
		
		timer = new Timer(FRAME_DELAY, this);
		timer.start();
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		//the top wall/boundary
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), WALL_THICKNESS);
		
		//the bottom wall/boundary
		g.fillRect(0, getHeight() - WALL_THICKNESS, getWidth(), WALL_THICKNESS);
		
		ball.draw(g);
		
		leftPad.draw(g);
		rightPad.draw(g);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		//move everything
		ball.move();
		leftPad.move();
		rightPad.move();
		
		//redraw the screen
		repaint();
	}
	
	/**
	 * Handles keyboard input to move sprites
	 * @author cjgunnar
	 *
	 */
	class InputHandler extends KeyAdapter
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
			
			//respond to keypress
			if(key == RIGHT_UP)
			{
				rightPad.setDY(-1);
			}
			else if(key == RIGHT_DOWN)
			{
				rightPad.setDY(1);
			}
			else if(key == LEFT_UP)
			{
				leftPad.setDY(-1);
			}
			else if(key == LEFT_DOWN)
			{
				leftPad.setDY(1);
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
