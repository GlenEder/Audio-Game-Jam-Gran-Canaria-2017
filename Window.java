import javax.swing.*;
import java.awt.*;

public class Window extends JPanel{
	
	private int width = 600;
	private int height = 400;

	public Window() {
		JFrame frame = new JFrame("Can You Hear Me?");
		frame.setSize(width, height);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.getContentPane().setBackground(java.awt.Color.black);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);

		frame.setVisible(true);
		repaint();
	}

	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.green);
		g.setFont(new Font("Impact", Font.BOLD, 24));
		g.drawString("This game is best played with headphones on", 50, 200);
	}

}