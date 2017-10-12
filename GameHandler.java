import java.io.*;
import javax.sound.sampled.*;

public class GameHandler implements Runnable{
	
	private Thread thread;
	private boolean running = false;
	private Window window;
	private InputHandler input;
	private MapHandler mapHandler;
	private int currLevel = 1;

	private int x;
	private int y;
	private int endX;
	private int endY;
	private int[][] currMap;

	public void run() {
		System.out.println("running main thread...");
		init();


		while(running) {
			
			System.out.print("");
			if(input.getUp()) {
				if(y - 1 >= 0 && currMap[y - 1][x] != 1) {
					y--;
					playSound("Sounds/Beacon.wav");
					System.out.println("x, y: " + x +", " + y); 
					coolDown();
				}
			}else if (input.getDown()) {
				if(y + 1 < currMap.length && currMap[y + 1][x] != 1) {
					y++;
					//play sound
					playSound("Sounds/Beacon.wav");
					System.out.println("x, y: " + x +", " + y);
					coolDown();
				}
			}else if (input.getRight()) {
				if(x + 1 < currMap[y].length && currMap[y][x + 1] != 1) {
					x++;
					//play sound
					playSound("Sounds/Beacon.wav");
					System.out.println("x, y: " + x +", " + y);
					coolDown();
				}
			}else if (input.getLeft()) {
				if(x - 1 >= 0 && currMap[y][x - 1] != 1) {
					x--;
					//play sound
					playSound("Sounds/Beacon.wav");
					System.out.println("x, y: " + x +", " + y);
					coolDown();
				}
			}else if (input.getSpace()) {
				playSound("Sounds/Beacon.wav");
				coolDown();
			}

			if(currMap[y][x] == 3) {
				goToNextLevel();
			}


		}

		stop();
	}

	public int getReduction() {
		int xpart = (x - endX) * (x - endX);
		int ypart = (y - endY) * (y - endY);
		int distance = (int)(Math.sqrt(xpart + ypart));
		distance *= -5;
		if(distance < -30) {
			distance = -30;
		}
		return distance;

	}

	public float getSoundBalance() {
		int xdif = endX - x;
		double ratio = (xdif * 2) / 10.0;
		if(ratio > 1.0) {
			ratio = 1.0;
		}else if (ratio < -1.0) {
			ratio = -1.0;
		}
		System.out.println(ratio);
		return (float)ratio;
	}

	public void coolDown() {
		try {
			thread.sleep(200);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void goToNextLevel() {
		mapHandler.loadLevel("Level" + currLevel);
		mapHandler.printMap();
		currMap = mapHandler.getMap();
		int[] spawn = mapHandler.getSpawnPos();
		x = spawn[0];
		y = spawn[1];
		System.out.println("x, y: " + x +", " + y);

		int[] endPos = mapHandler.getEndPos();
		endX = endPos[0];
		endY = endPos[1];

		//play sound "Level ..."
		currLevel++;
	}

	public void init() {
		window = new Window();
		input = new InputHandler();
		window.addKeyListener(input);
		mapHandler = new MapHandler();
		goToNextLevel();

	}

	public void playSound(String soundFile) {
		try {
			Clip clip = AudioSystem.getClip();
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File(soundFile));
			clip.open(ais);
			FloatControl gainContorl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainContorl.setValue(getReduction());

			FloatControl balanceControl = (FloatControl)clip.getControl(FloatControl.Type.BALANCE);
			balanceControl.setValue(getSoundBalance());

			clip.loop(0);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	public synchronized void start() {
		if(thread == null) {
			running = true;
			thread = new Thread(this);
			thread.start();
		}
	}

	public synchronized void stop() {
		try {
			thread.join();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			System.exit(0);
		}
	}
}