import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Shows a frame with the board in it
 * @author cjgunnar
 *
 */
@SuppressWarnings("serial")
public class PongWindow extends JFrame
{
	static final int WIDTH = 700;
	static final int HEIGHT = 615;
	
	public PongWindow()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		setResizable(false);
		setTitle("Pong by Caden");
		
		add(new Board());
	}
	
	public static void main(String[] args)
	{
		//for thread safety
		EventQueue.invokeLater(new Runnable() 
		{
			@Override
			public void run() 
			{
				if(JOptionPane.showConfirmDialog(null, "Welcome to Pong by Caden.\n"
						+ "Right Player: use up/down arrow keys.\n"
						+ "Left Player: use w/s.\n"
						+ "\nFirst to 10 points wins.", "Pong Game", 
						JOptionPane.OK_CANCEL_OPTION) != JOptionPane.OK_OPTION)
				{
					return;
				}
				
				JFrame app = new PongWindow();
				app.setVisible(true);                
			}
		});
	}
}
