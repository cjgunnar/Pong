import javax.swing.JFrame;

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
	
	public static void main(String[] args)
	{
		System.out.println("Starting");
		
		PongWindow runner = new PongWindow();
		runner.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		runner.setSize(WIDTH, HEIGHT);
		runner.setResizable(false);
		runner.setTitle("Pong");
		
		runner.add(new Board());
		
		runner.setVisible(true);
	}
}
