
public class GameHandler implements Runnable{
	
	private Thread thread;
	private boolean running = false;
	private Window window;
	private InputHandler input;

	public void run() {
		System.out.println("running main thread...");
		init();

		while(running) {
			System.out.println("running");
			tick();
			render();

		}

		stop();
	}

	public void init() {
		window = new Window();
		input = new InputHandler();
		window.addKeyListener(input);

	}

	public void tick() {
		if(input.getDown()){
			System.out.println("Down");
		}
	}

	public void render() {

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