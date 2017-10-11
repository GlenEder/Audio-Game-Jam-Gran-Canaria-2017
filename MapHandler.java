import java.io.*;

public class MapHandler {
	
	private int[][] map;

	public void loadLevel(String levelName) {
		int width;
		int height;
		try {
			BufferedReader br = new BufferedReader(new FileReader("Levels/" + levelName + ".txt"));
			width = Integer.parseInt(br.readLine());
			height = Integer.parseInt(br.readLine());
			System.out.println("Width: " + width + "\nHeight: " + height);
			map = new int[height][width];
			for(int i = 0; i < height; i++) {
				String line = br.readLine();
				String[] parts = line.split(" ");
				for(int j = 0; j < width; j++) {
					map[i][j] = Integer.parseInt(parts[j]);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	
	}	

	public void printMap() {
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[i].length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println("");
		}
	}
}