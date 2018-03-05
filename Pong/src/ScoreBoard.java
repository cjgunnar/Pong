import java.awt.Graphics;

import javax.swing.JOptionPane;

/**
 * Displays the score
 * @author cjgunnar
 *
 */
public class ScoreBoard extends Sprite
{
	int leftScore;
	int rightScore;
	
	public ScoreBoard(int startX, int startY)
	{
		super(startX, startY);
		
		leftScore = 0;
		rightScore = 0;
	}
	
	public void scoreLeft()
	{
		leftScore++;
		
		if(leftScore == 10)
		{
			JOptionPane.showConfirmDialog(null, "Left Wins!");
			reset();
		}
			
	}
	
	public void scoreRight()
	{
		rightScore++;
		
		if(rightScore == 10)
		{
			JOptionPane.showMessageDialog(null, "Left Wins!");
			reset();
		}
	}
	
	public int getLeftScore()
	{
		return leftScore;
	}
	
	public int getRightScore()
	{
		return rightScore;
	}
	
	public void reset()
	{
		leftScore = 0;
		rightScore = 0;
	}

	@Override
	public void draw(Graphics g)
	{
		int offset = 200;
		g.drawString(leftScore + "", x, y);
		g.drawString(rightScore + "", x + offset, y);
	}

	@Override
	public void onCollision(Sprite other)
	{
		//nothing will happen if scoreboard is hit
	}
}
