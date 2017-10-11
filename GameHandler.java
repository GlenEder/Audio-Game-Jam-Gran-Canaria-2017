
public class GameHandler implements Runnable{
	
	private Thread thread;
	private boolean running = false;
	private Window window;
	private InputHandler input;
	private MapHandler mapHandler;

	public void run() {
		System.out.println("running main thread...");
		init();

		long now = System.nanoTime();
		long lastTime = now;
		long sleepTime;

		while(running) {

			lastTime = now;
			tick();
			now = System.nanoTime();

			try {
				sleepTime = (1000000000 / 60) - (now - lastTime);
				if(sleepTime > 0) {
					thread.sleep(sleepTime / 1000000);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}

		}

		stop();
	}

	public void init() {
		window = new Window();
		input = new InputHandler();
		window.addKeyListener(input);
		mapHandler = new MapHandler();
		mapHandler.loadLevel("TestLevel");
		mapHandler.printMap();

	}

	public void tick() {
		if(input.getDown()){
			System.out.println("Down");
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