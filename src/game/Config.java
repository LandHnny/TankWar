package game;

import java.util.Random;

public class Config {

	static public final int UNIT = 60;

	static public final int WINDOW_width = 11*UNIT;
	static public final int WINDOW_height = 9*UNIT;
	static public final int WINDOW_refresh = 60;

	static public int[][] map = new int[][]{
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{ 0, 0, 0, 0, 0,33, 0, 0, 0, 0, 0},
			{ 0, 0, 0, 0, 0,31, 0, 0, 0, 0, 0},
			{ 0,34, 0, 0, 0,32, 0, 0, 0,34, 0},
			{ 0, 0, 0, 0,34,34,34, 0, 0, 0, 0},
			{ 0,34, 0, 0, 0,32, 0, 0, 0,34, 0},
			{ 0, 0, 0, 0, 0,31, 0, 0, 0, 0, 0},
			{ 0, 0, 0, 0, 0,33, 0, 0, 0, 0,31},
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
	};

	static int random = 0;
	public static int getRandom(int max){
		return (int)(Math.random()*max);
	}
}
