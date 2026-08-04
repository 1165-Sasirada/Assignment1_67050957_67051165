import java.awt.Graphics2D;

public class PonySleep {
	private static final String[] sleep0 = {
		
	};

	private static final String[] sleep1 = {

	};

	private static final String[] sleep2 = {

	};

	private static final String[] sleep3 = {

	};

	public static void draw(Graphics2D g2d, int scale, int frameIndex) {
		String[][] frames = {sleep0, sleep1, sleep2, sleep3 };

		int rowOffset = (frameIndex == 1 || frameIndex == 3) ? 26 : 25;

    	DrawElement.drawElement(g2d, frames[frameIndex], rowOffset, 25, scale);
	}
}