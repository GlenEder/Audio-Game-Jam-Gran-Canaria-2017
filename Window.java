import javax.swing.JFrame;

public class Window extends JFrame{
	
	private int width = 600;
	private int height = 400;

	public Window() {
		super("Can You Hear Me?");
		setSize(width, height);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setBackground(java.awt.Color.black);

		setVisible(true);
	}
}