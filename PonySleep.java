import java.awt.Graphics2D;

public class PonySleep {
	private static String[] sleep0 = {
		"4X 10O",
		"3X 1O 2H 1P 2I 2H 1A 2S 1O",
		"2X 1O 2H 1P 2I 2H 2A 3S 1O",
		"1X 1O 2S 1H 1P 2I 1H 1A 3S 1D 1S 1O",
		"1X 1O 2H 1P 2I 2H 1A 3S 1D 1S 1O",
		"1O 3H 1P 2I 1H 1A 6S 1O",
		"1O 3H 1P 2I 1H 1A 5S 1A 1O",
		"1O 3H 1P 2I 1H 1A 5S 1A 1O",
		"3O 6A 5S 1A 1H 1O",
		"3X 1O 8S 1E 1D 1A 1H 1O",
		"2X 2O 1D 3S 1E 3S 1E 1S 1A 1H 1O",
		"2X 1O 6S 3E 2S 1A 1H 1O",
		"3X 1O 9S 1A 2H 1O",
		"4X 3O 5D 1S 4A 1O",
		"6X 1O 7S 1O 2H 1O",
		"5X 1O 2D 7S 3O",
		"5X 1O 2D 2S 3O 2S 1O",
		"6X 4O 3X 2O"
	};

	private static String[] sleep1 = {
		"4X 7O 1X 3O",
		"2X 2O 1H 1P 2I 2H 1A 1O 3S 1O",
		"1X 1O 2S 1H 1P 2I 1H 1A 3S 1D 1S 1O",
		"1X 1O 2H 1P 2I 2H 1A 3S 1D 1S 1O",
		"1O 3H 1P 2I 1H 1A 6S 1O",
		"1O 3H 1P 2I 1H 1A 5S 1A 1O",
		"1O 3H 1P 2I 1H 1A 5S 1A 1O",
		"3O 1H 1P 2I 1H 1A 5S 1A 1H 1O",
		"3X 1O 4A 4S 1E 1D 1A 1H 1O",
		"2X 2O 1D 3S 1E 3S 1E 1S 1A 1H 1O",
		"2X 1O 6S 3E 2S 1A 1H 1O",
		"3X 1O 9S 1A 2H 1O",
		"4X 3O 5D 1S 4A 1O",
		"6X 1O 7S 1O 2H 1O",
		"5X 1O 2D 7S 2O",
		"5X 1O 2D 2S 3O 2S 1O",
		"6X 4O 3X 2O"
	};

	private static String[] z0 = {
		"3O",
		"2X 1O",
		"1X 1O",
		"1O",
		"3O"
	};

	private static String[] z1 =  {
		"4O",
		"3X 1O",
		"2x 1O",
		"1X 1O",
		"1O",
		"4O"
	};

	private static String[] b0 = {
		"1X 2K",
		"1K 1B 1W 1K",
		"1K 2B 1K",
		"1X 2K"
	};

	private static String[] b1 = {
		"1X 3K",
		"1K 2B 1W 1K",
		"1K 1B 2W 1K",
		"1K 4B 1K",
		"1X 1K 4B 1K",
		"2X 4K"
	};

	public static void draw(Graphics2D g2d, int scale, int frameIndex) {
		String[][] frames = { sleep0, sleep1 };
		int targetFrame = frameIndex % 2;
    	int rowOffset = (targetFrame == 1) ? 26 : 25;

  		DrawElement.drawElement(g2d, frames[targetFrame], rowOffset, 25, scale);
	}
}