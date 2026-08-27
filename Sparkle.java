import java.awt.Graphics2D;
import java.util.concurrent.ThreadLocalRandom;

public class Sparkle {
	public static void drawSparkle(Graphics2D g2d) {
		int width = 600;
		int height = 600;
		int margin = 100;

		for (int i = 0; i < 8; i++) {
			int startX, startY;
			int side = ThreadLocalRandom.current().nextInt(0, 4);

			switch (side) {
				case 0: // Top border (full width, 0 to 100 Y)
					startX = ThreadLocalRandom.current().nextInt(0, width - 60);
					startY = ThreadLocalRandom.current().nextInt(0, margin + 30);
					break;
				case 1: // Bottom border (full width, 500 to 600 Y)
					startX = ThreadLocalRandom.current().nextInt(0, width - 60);
					startY = ThreadLocalRandom.current().nextInt(height - margin, height - 60);
					break;
				case 2: // Left border (0 to 100 X, inner Y)
					startX = ThreadLocalRandom.current().nextInt(0, margin);
					startY = ThreadLocalRandom.current().nextInt(margin, height - margin);
					break;
				default: // Right border (500 to 600 X, inner Y)
					startX = ThreadLocalRandom.current().nextInt(width - margin, width - 60);
					startY = ThreadLocalRandom.current().nextInt(margin, height - margin);
					break;
			}

			int type = ThreadLocalRandom.current().nextInt(0, 2);

			switch (type) {
				case 0:
					sparkle0(g2d, startX, startY);
					break;
				default:
					sparkle1(g2d, startX, startY);
					break;
			}
		}
	}

	private static void sparkle0(Graphics2D g2d, int x, int y) {
		Tamagotchi.bezierCurve(g2d, x, y, x - 10, y + 30, x - 10, y + 20, x - 30, y + 30);
		Tamagotchi.bezierCurve(g2d, x, y, x + 10, y + 30, x + 10, y + 20, x + 30, y + 30);
		Tamagotchi.bezierCurve(g2d, x, y + 60, x - 10, y + 30, x - 10, y + 40, x - 30, y + 30);
		Tamagotchi.bezierCurve(g2d, x, y + 60, x + 10, y + 30, x + 10, y + 40, x + 30, y + 30);
	}

	private static void sparkle1(Graphics2D g2d, int x, int y) {
		Tamagotchi.bresenhamLine(g2d, x, y, x - 10, y + 10, 3);
		Tamagotchi.bresenhamLine(g2d, x, y, x + 10, y + 10, 3);
		Tamagotchi.bresenhamLine(g2d, x - 10, y + 10, x, y + 20, 3);
		Tamagotchi.bresenhamLine(g2d, x + 10, y + 10, x, y + 20, 3);
	}
}
