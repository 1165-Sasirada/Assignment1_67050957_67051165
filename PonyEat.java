import java.awt.Graphics2D;

public class PonyEat {
	private static String[] cookie0 = {
		"4X 4c",
		"3X 1c 3o 1k 2c",
		"2X 1c 1k 4o 2k 1c",
		"1X 1c 2o 2c 5o 1c",
		"1X 1c 2o 1c 1k 2o 1c 3o 1c",
		"1c 8o 1k 1c 1o 1c",
		"1c 1k 1o 1c 2o 1k 3o 1c 1o 1c",
		"1c 1k 2o 1c 2o 2c 3o 1c",
		"1X 1c 1k 4o 2c 3o 1c",
		"1X 1c 1k 1o 1c 5o 1k 1c",
		"2X 1c 1k 6o 1c",
		"3X 2c 2k 2o 1c",
		"5X 4c"
	};

	private static String[] cookie1 = {
		"4X 2c",
		"3X 1c 2o 1c",
		"2X 1c 1k 2o 1c",
		"1X 1c 2o 2c 1o 1c",
		"1X 1c 2o 1c 1k 2o 1c",
		"1c 8o 1c",
		"1c 1k 1o 1c 2o 1k 3o 2c",
		"1c 1k 2o 1c 2o 2c 3o 1c",
		"1X 1c 1k 4o 2c 3o 1c",
		"1X 1c 1k 1o 1c 5o 1k 1c",
		"2X 1c 1k 6o 1c",
		"3X 2c 2k 2o 1c",
		"5X 4c"
	};

	private static String[] cookie2 = {
		"4X 2c",
		"3X 1c 2o 1c",
		"2X 1c 1k 2o 1c",
		"1X 1c 2o 2c 1o 1c",
		"1X 1c 2o 1c 1k 2o 1c",
		"1c 8o 1c",
		"1c 1k 1o 1c 2o 1k 3o 1c",
		"1X 2c 1o 1c 2o 2c 3o 1c",
		"3X 1c 3o 2c 3o 1c",
		"4X 1c 5o 1k 1c",
		"5X 1c 4o 1c",
		"6X 1c 2o 1c",
		"7X 2c"	
	};

	private static String[] cookie3 = {
		"3X 3c",
		"2X 1c 1k 2o 1c",
		"1X 1c 5o 1c",
		"1c 3o 1k 3o 1c",
		"1c 1o 1c 2o 2c 1o 1c",
		"1x 1c 3o 3c",
		"2X 2c 2o 1c",
		"4X 2c"
	};

	public static void draw(Graphics2D g2d, int scale, int frameIndex) {
		String[][] frames = { cookie0, cookie1, cookie2, cookie3 };

    	int rowOffset = (frameIndex == 3) ? 39 : 37;
    	int colOffset = (frameIndex == 3) ? 21 : 18;

    	DrawElement.drawElement(g2d, frames[frameIndex], rowOffset, colOffset, scale);
	}
}
