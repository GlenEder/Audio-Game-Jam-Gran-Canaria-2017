
public class GameHandler implements Runnable{
	
	private Thread thread;
	private boolean running = false;


	public void run() {

		while(running) {

			tick();
			render();

		}

		stop();
	}

	public void tick() {

	}

	public void render() {
		
	}

	public synchronized void start() {
`		if(thread == null) {
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