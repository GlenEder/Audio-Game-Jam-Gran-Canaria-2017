
public class GameHandler implements Runnable{
	
	private Thread thread;
	private boolean running = false;
	private Window window;
	private InputHandler input;
	private MapHandler mapHandler;
	private int currLevel = 1;

	private int x;
	private int y;
	private int[][] currMap;

	public void run() {
		System.out.println("running main thread...");
		init();


		while(running) {
			
			System.out.print("");
			if(input.getUp()) {
				if(y - 1 >= 0 && currMap[y - 1][x] != 1) {
					y--;
					//play sound
					System.out.println("x, y: " + x +", " + y); 
					coolDown();
				}
			}else if (input.getDown()) {
				if(y + 1 < currMap.length && currMap[y + 1][x] != 1) {
					y++;
					//play sound
					System.out.println("x, y: " + x +", " + y);
					coolDown();
				}
			}else if (input.getRight()) {
				if(x + 1 < currMap[y].length && currMap[y][x + 1] != 1) {
					x++;
					//play sound
					System.out.println("x, y: " + x +", " + y);
					coolDown();
				}
			}else if (input.getLeft()) {
				if(x - 1 >= 0 && currMap[y][x - 1] != 1) {
					x--;
					//play sound
					System.out.println("x, y: " + x +", " + y);
					coolDown();
				}
			}

			if(currMap[y][x] == 3) {
				goToNextLevel();
			}


		}

		stop();
	}

	public void coolDown() {
		try {
			thread.sleep(500);
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