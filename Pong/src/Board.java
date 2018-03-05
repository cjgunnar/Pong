import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * Contains the paddles, walls, score, and runs update timer/keyboard input
 * @author cjgunnar
 *
 */
@SuppressWarnings("serial")
public class Board extends JPanel
{
	final static int WALL_THICKNESS = 10;
	
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
		
		//set the background to be black
		setBackground(Color.black);
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
}
