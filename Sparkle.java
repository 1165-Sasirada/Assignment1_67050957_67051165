import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.awt.Color;

public class Sparkle {
	private static Color spark = new Color(247, 216, 157);

	public static void drawSparkle(Graphics2D g2d) {
		int width = 600;
		int height = 600;
		int margin = 100;

		int minDistance = 50;

		List<Point> placedPoints = new ArrayList<>();

		for (int i = 0; i < 8; i++) {
			int startX = 0, startY = 0;
			boolean validPosition = false;
			int attemps = 0;

			while (!validPosition && attemps < 50) {
				attemps++;
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

				validPosition = true;
				for (Point p : placedPoints) {
					if (Math.hypot(p.x - startX, p.y - startY) < minDistance) {
						validPosition = false;
						break;
					}
				}
			}

			if (validPosition) {
				placedPoints.add(new Point(startX, startY));

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
	}

	private static void sparkle0(Graphics2D g2d, int x, int y) {
		g2d.setColor(spark);
    	int colorX = x - 2;
    	int colorY = y + 7;

		// X offset
    	int[] xOffsets = {0, 3, 6, 12, 24, 21, 9, 6, 3, 0};
    	for (int i = 0; i < xOffsets.length; i++) {
    		int dx = xOffsets[i];
    		int dy = i * 5;
    		Tamagotchi.bresenhamLine(g2d, colorX - dx, colorY + dy, colorX + dx, colorY + dy, 5);
    	}

		g2d.setColor(Color.BLACK);
		Tamagotchi.bezierCurve(g2d, x, y, x - 10, y + 30, x - 10, y + 20, x - 30, y + 30,3);
		Tamagotchi.bezierCurve(g2d, x, y, x + 10, y + 30, x + 10, y + 20, x + 30, y + 30,3);
		Tamagotchi.bezierCurve(g2d, x, y + 60, x - 10, y + 30, x - 10, y + 40, x - 30, y + 30,3);
		Tamagotchi.bezierCurve(g2d, x, y + 60, x + 10, y + 30, x + 10, y + 40, x + 30, y + 30,3);
	}

	private static void sparkle1(Graphics2D g2d, int x, int y) {
		g2d.setColor(spark);
		int colorX = x;
		int colorY = y + 3;

		// Combined Upper and Lower filling (6 iterations total)
		for (int i = 0; i < 6; i++) {
			int yOffset = i * 3;
			int widthOffset = (i <= 3 ? i : 6 - i) * 3;
			Tamagotchi.bresenhamLine(g2d, colorX - widthOffset, colorY + yOffset, colorX + widthOffset, colorY + yOffset, 3);
		}

		g2d.setColor(Color.BLACK);
		Tamagotchi.bresenhamLine(g2d, x, y, x - 10, y + 10, 3);
		Tamagotchi.bresenhamLine(g2d, x, y, x + 10, y + 10, 3);
		Tamagotchi.bresenhamLine(g2d, x - 10, y + 10, x, y + 20, 3);
		Tamagotchi.bresenhamLine(g2d, x + 10, y + 10, x, y + 20, 3);
	}

	
}
