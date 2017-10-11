import java.awt.event.*;

public class InputHandler implements KeyListener {

	private static boolean up = false;
	private static boolean down = false;
	private static boolean right = false;
	private static boolean left = false;
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP)		{up = true;}
		if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) 	{down = true;}
		if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT)	{right = true;}
		if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) 	{left = true;}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP)		{up = false;}
		if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) 	{down = false;}
		if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT)	{right = false;}
		if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) 	{left = false;}
	}

	public void keyTyped(KeyEvent e) {

	}

	public boolean getUp() {
		return up;
	}

	public boolean getDown() {
		return down;
	}

	public boolean getRight() {
		return right;
	}

	public boolean getLeft() {
		return left;
	}
}