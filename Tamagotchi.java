import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Queue;

public class Tamagotchi extends JPanel {
	private static final int CANVAS_GRID_SIZE = 64;

	private int tickCount = 0;
	private BufferedImage tamagotchiDeviceImage;

	// Color definitions
	public static final Color TRANSPARENT = new Color(0, 0, 0, 0);
	public static final Color pinkFrame = new Color(229, 160, 192);
	public static final Color palePinkBG = new Color(255, 239, 246);
	public static final Color blueBG = new Color(122, 138, 181);
	public static final Color lineColor = Color.decode("#3F3850");
	public static final Color tamagotchiColor = Color.decode("#A9B8F5");
	public static final Color tamagotchiShadow = Color.decode("#8495D8");
	public static final Color screenColor = Color.decode("#FFFDF4");
	public static final Color frameColor = Color.decode("#F7A9CF");
	public static final Color frameShadow = Color.decode("#DB82B2");
	public static final Color buttonColor = Color.decode("#FFE778");
	public static final Color buttonShadow = Color.decode("#D9BD4F");

	public Tamagotchi() {
		setBackground(Color.WHITE);
		tamagotchiDeviceImage = createBufferedImage(TRANSPARENT, this::drawTamagotchiShell);
	}

	public void tick() {
		tickCount++;
		repaint();
	}

	public int getTicks() {
		return tickCount;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g.create();

		try {
			// 1. Draw outer Tamagotchi Device Frame
			renderImage(g2,tamagotchiDeviceImage, 0, 0, 1.0);

			// 2. Setup screen graphics boundary (Screen viewport)
			Graphics2D screenG = (Graphics2D) g2.create();
			try {
				screenG.clipRect(185, 168, 230, 230);
				screenG.translate(168, 151);
				screenG.scale(0.42, 0.42);

				int scale = Math.max(1, Math.min(getWidth(), getHeight()) / CANVAS_GRID_SIZE);

				// Render permanent background screen components
				// DrawElement.drawFrame(screenG, pinkFrame, 9, 13, 44, scale);
				// DrawElement.drawMenu(screenG, scale, 56, 17);

				// 3. Render content based on active Scene
				switch (Main.getCurrentScene()) {
					case IDLE:
						screenG.setColor(palePinkBG);
						screenG.fillRect(48, 48, 640, 640);
						DrawElement.drawFrame(screenG, pinkFrame, 9, 13, 44, scale);
						// DrawElement.drawMenuDay(screenG, scale, 56, 17);
						// DrawElement.drawWindow(screenG, 15, 18, scale, DrawElement.day);
						// PonyIdle.draw(screenG, scale, tickCount % 4);
						break;

					case BATH:
						screenG.setColor(palePinkBG);
						screenG.fillRect(48, 48, 640, 640);
						DrawElement.drawFrame(screenG, pinkFrame, 9, 13, 44, scale);
						DrawElement.drawMenuDay(screenG, scale, 56, 17);
						DrawElement.drawWindow(screenG, 15, 18, scale, DrawElement.day);
						PonyIdle.draw(screenG, scale, tickCount % 4);
						DrawElement.drawBathTub(screenG, 25, 40, scale);
						DrawElement.randomBubbles(screenG, scale);
						break;

					case SLEEP:
						screenG.setColor(blueBG);
						screenG.fillRect(48, 48, 640, 640);
						DrawElement.drawFrame(screenG, DrawElement.night, 9, 13, 44, scale);
						DrawElement.drawMenuNight(screenG, scale, 56, 17);
						DrawElement.drawWindow(screenG, 15, 18, scale, DrawElement.night);
						PonySleep.draw(screenG, scale, tickCount % 4);
						break;

					case EAT:
						screenG.setColor(palePinkBG);
						screenG.fillRect(48, 48, 640, 640);
						DrawElement.drawFrame(screenG, pinkFrame, 9, 13, 44, scale);
						DrawElement.drawMenuDay(screenG, scale, 56, 17);
						DrawElement.drawWindow(screenG, 15, 18, scale, DrawElement.day);
						PonyIdle.draw(screenG, scale, tickCount % 4);
						PonyEat.draw(screenG, scale, tickCount % 4);
						break;
				}

				// TEST GIRL
				Girl.drawGirlOutline(g2);

			} finally {
				screenG.dispose();
			}

			int cycleTick = tickCount % 36;

			if (cycleTick >= 8 && cycleTick < 10) {
				Fingers.drawFingerRightStill(g2);
				Fingers.drawFingerLeftPress(g2);
			}
			else if (cycleTick >= 18 && cycleTick < 20) {
				Fingers.drawFingerRightPress(g2);
				Fingers.drawFingerLeftStill(g2);
			}
			else if (cycleTick == 28) {
				Fingers.drawFingerRightReach0(g2);
				Fingers.drawFingerLeftStill(g2);
			}
			else if (cycleTick == 29) {
				Fingers.drawFingerRightReach1(g2);
				Fingers.drawFingerLeftStill(g2);
			}
			else if (cycleTick == 30) {
				Fingers.drawFingerRightPressMiddle(g2);
				Fingers.drawFingerLeftStill(g2);
			}
			else {
				Fingers.drawFingerRightStill(g2);
				Fingers.drawFingerLeftStill(g2);
			}


		} finally {
			g2.dispose();
		}
	}

	public void renderImage(Graphics2D g2, BufferedImage image, int x, int y, double scale) {
		int drawWidth = (int) (image.getWidth() * scale);
		int drawHeight = (int) (image.getHeight() * scale);

		g2.drawImage(image, x, y, drawWidth, drawHeight, null);
	}

	// --- Device Shell Drawing Methods ---
	public static BufferedImage createBufferedImage(Color bgColor, java.util.function.BiConsumer<Graphics2D, BufferedImage> drawFunc) {
		BufferedImage image = new BufferedImage(600, 600, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();
		try {
			g2.setColor(bgColor);
			g2.fillRect(0, 0, 600, 600);
			drawFunc.accept(g2, image);
		} finally {
			g2.dispose();
		}
		return image;
	}

	private void drawTamagotchiShell(Graphics2D g2, BufferedImage buffer) {
		int lineSize = 3;

		// Tamagotchi Body
		g2.setColor(lineColor);
		midpointEllipse(g2, 300, 300, 250, 280, lineSize);
		floodFill(buffer, 300, 300, TRANSPARENT, tamagotchiShadow);
		g2.setColor(tamagotchiColor);
		midpointEllipse(g2, 302, 304, 243, 271, 3);
		floodFill(buffer, 300, 300, tamagotchiShadow, tamagotchiColor);

		// Screen
		g2.setColor(lineColor);
		bresenhamLine(g2, 185, 168, 415, 168, lineSize);
		bresenhamLine(g2, 185, 168, 185, 398, lineSize);
		bresenhamLine(g2, 415, 168, 415, 398, lineSize);
		bresenhamLine(g2, 185, 398, 415, 398, lineSize);
		floodFill(buffer, 300, 300, tamagotchiColor, screenColor);

		// Frame Shadow & Outer Details
		g2.setColor(frameShadow);
		bresenhamLine(g2, 185, 164, 413, 164, 4);
		bresenhamLine(g2, 181, 168, 181, 396, 4);
		bresenhamLine(g2, 138, 196, 165, 245, 4);
		bresenhamLine(g2, 166, 314, 135, 278, 4);
		bresenhamLine(g2, 166, 386, 135, 358, 4);
		bresenhamLine(g2, 213, 407, 173, 422, 4);
		bresenhamLine(g2, 223, 409, 265, 431, 4);
		bresenhamLine(g2, 267, 428, 297, 409, 4);
		bresenhamLine(g2, 304, 410, 338, 423, 4);
		bresenhamLine(g2, 337, 423, 388, 408, 4);
		bresenhamLine(g2, 392, 409, 427, 423, 4);
		bresenhamLine(g2, 439, 370, 461, 343, 4);
		bresenhamLine(g2, 438, 296, 463, 262, 4);
		bresenhamLine(g2, 442, 216, 462, 191, 4);

		g2.setColor(lineColor);
		bresenhamLine(g2, 168, 128, 217, 153, lineSize);
		bresenhamLine(g2, 217, 153, 264, 134, lineSize);
		bresenhamLine(g2, 264, 134, 298, 156, lineSize);
		bresenhamLine(g2, 298, 156, 331, 133, lineSize);
		bresenhamLine(g2, 331, 133, 383, 152, lineSize);
		bresenhamLine(g2, 383, 152, 428, 127, lineSize);
		bresenhamLine(g2, 428, 127, 428, 158, lineSize);
		bresenhamLine(g2, 428, 158, 465, 190, lineSize);
		bresenhamLine(g2, 465, 190, 443, 219, lineSize);
		bresenhamLine(g2, 443, 219, 465, 262, lineSize);
		bresenhamLine(g2, 465, 262, 439, 300, lineSize);
		bresenhamLine(g2, 439, 300, 464, 343, lineSize);
		bresenhamLine(g2, 464, 343, 439, 373, lineSize);
		bresenhamLine(g2, 439, 373, 428, 427, lineSize);
		bresenhamLine(g2, 428, 427, 390, 412, lineSize);
		bresenhamLine(g2, 390, 412, 337, 426, lineSize);
		bresenhamLine(g2, 337, 426, 300, 411, lineSize);
		bresenhamLine(g2, 300, 411, 264, 434, lineSize);
		bresenhamLine(g2, 264, 434, 218, 409, lineSize);
		bresenhamLine(g2, 218, 409, 169, 426, lineSize);
		bresenhamLine(g2, 169, 426, 164, 388, lineSize);
		bresenhamLine(g2, 164, 388, 131, 357, lineSize);
		bresenhamLine(g2, 131, 357, 164, 316, lineSize);
		bresenhamLine(g2, 164, 316, 132, 278, lineSize);
		bresenhamLine(g2, 132, 278, 164, 247, lineSize);
		bresenhamLine(g2, 164, 247, 135, 196, lineSize);
		bresenhamLine(g2, 135, 196, 168, 158, lineSize);
		bresenhamLine(g2, 168, 158, 168, 128, lineSize);
		floodFill(buffer, 163, 202, tamagotchiColor, frameColor);

		// Buttons
		g2.setColor(lineColor);
		midpointCircle(g2, 182, 473, 30, lineSize);
		floodFill(buffer, 182, 473, tamagotchiColor, buttonShadow);
		g2.setColor(buttonColor);
		midpointEllipse(g2, 182, 471, 25, 24, lineSize);
		floodFill(buffer, 182, 471, buttonShadow, buttonColor);

		g2.setColor(lineColor);
		midpointCircle(g2, 300, 502, 30, lineSize);
		floodFill(buffer, 300, 502, tamagotchiColor, buttonShadow);
		g2.setColor(buttonColor);
		midpointEllipse(g2, 300, 500, 25, 24, lineSize);
		floodFill(buffer, 300, 500, buttonShadow, buttonColor);

		g2.setColor(lineColor);
		midpointCircle(g2, 421, 473, 30, lineSize);
		floodFill(buffer, 421, 473, tamagotchiColor, buttonShadow);
		g2.setColor(buttonColor);
		midpointEllipse(g2, 421, 471, 25, 24, lineSize);
		floodFill(buffer, 421, 471, buttonShadow, buttonColor);
	}

	// --- Helper Algorithms ---
	public static void bresenhamLine(Graphics g, int x1, int y1, int x2, int y2, int pixelSize) {
		int dx = Math.abs(x2 - x1);
		int dy = Math.abs(y2 - y1);
		int sx = (x1 < x2) ? 1 : -1;
		int sy = (y1 < y2) ? 1 : -1;
		boolean isSwap = false;

		if (dy > dx) {
			int temp = dx;
			dx = dy;
			dy = temp;
			isSwap = true;
		}

		int D = (2 * dy) - dx;
		int x = x1, y = y1;

		for (int i = 0; i <= dx; i++) {
			plot(g, x, y, pixelSize);
			if (D >= 0) {
				if (isSwap) x += sx;
				else y += sy;
				D -= 2 * dx;
			}
			if (isSwap) y += sy;
			else x += sx;
			D += 2 * dy;
		}
	}

	public static BufferedImage floodFill(BufferedImage m, int x, int y, Color targetColor, Color replacementColor) {
		Queue<Point> q = new LinkedList<>();
		int targetRGB = targetColor.getRGB();
		int replacementRGB = replacementColor.getRGB();

		if (x < 0 || x >= m.getWidth() || y < 0 || y >= m.getHeight() || m.getRGB(x, y) != targetRGB) {
			return m;
		}

		m.setRGB(x, y, replacementRGB);
		q.add(new Point(x, y));

		while (!q.isEmpty()) {
			Point p = q.poll();
			if (p.x + 1 < m.getWidth() && m.getRGB(p.x + 1, p.y) == targetRGB) {
				m.setRGB(p.x + 1, p.y, replacementRGB);
				q.add(new Point(p.x + 1, p.y));
			}
			if (p.x - 1 >= 0 && m.getRGB(p.x - 1, p.y) == targetRGB) {
				m.setRGB(p.x - 1, p.y, replacementRGB);
				q.add(new Point(p.x - 1, p.y));
			}
			if (p.y + 1 < m.getHeight() && m.getRGB(p.x, p.y + 1) == targetRGB) {
				m.setRGB(p.x, p.y + 1, replacementRGB);
				q.add(new Point(p.x, p.y + 1));
			}
			if (p.y - 1 >= 0 && m.getRGB(p.x, p.y - 1) == targetRGB) {
				m.setRGB(p.x, p.y - 1, replacementRGB);
				q.add(new Point(p.x, p.y - 1));
			}
		}
		return m;
	}

	public static void midpointCircle(Graphics g, int xc, int yc, int r, int pixelSize) {
		int x = 0, y = r;
		int d = 1 - r;
		int dx = 2 * x, dy = 2 * y;

		while (x <= y) {
			plot(g, x + xc, y + yc, pixelSize);
			plot(g, -x + xc, y + yc, pixelSize);
			plot(g, -x + xc, -y + yc, pixelSize);
			plot(g, x + xc, -y + yc, pixelSize);
			plot(g, y + xc, x + yc, pixelSize);
			plot(g, -y + xc, x + yc, pixelSize);
			plot(g, -y + xc, -x + yc, pixelSize);
			plot(g, y + xc, -x + yc, pixelSize);

			x++;
			dx += 2;
			d = d + dx + 1;
			if (d >= 0) {
				y--;
				dy -= 2;
				d = d - dy;
			}
		}
	}

	public static void midpointEllipse(Graphics g, int xc, int yc, int a, int b, int pixelSize) {
		int x = 0, y = b;
		int aSquared = a * a, bSquared = b * b;
		int twoASquare = 2 * aSquared, twoBSquare = 2 * bSquared;

		int d = (int) Math.round(bSquared - aSquared * b + aSquared / 4.0);

		while (bSquared * x <= aSquared * y) {
			plot(g, x + xc, y + yc, pixelSize);
			plot(g, -x + xc, y + yc, pixelSize);
			plot(g, -x + xc, -y + yc, pixelSize);
			plot(g, x + xc, -y + yc, pixelSize);

			x++;
			d = d + twoBSquare * x + bSquared;
			if (d >= 0) {
				y--;
				d = d - twoASquare * y;
			}
		}

		x = a; y = 0;
		d = (int) Math.round(aSquared - bSquared * a + bSquared / 4.0);

		while (bSquared * x >= aSquared * y) {
			plot(g, x + xc, y + yc, pixelSize);
			plot(g, -x + xc, y + yc, pixelSize);
			plot(g, -x + xc, -y + yc, pixelSize);
			plot(g, x + xc, -y + yc, pixelSize);

			y++;
			d = d + twoASquare * y + aSquared;
			if (d >= 0) {
				x--;
				d = d - twoBSquare * x;
			}
		}
	}

	public static void bezierCurve(Graphics2D g2d, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
		int numPoints = 500;
		double step = 1.0 / numPoints;

		for (double t = 0; t <= 1; t+= step) {
			int x = (int) (Math.pow(1 - t, 3) * x1 +
					3 * t * Math.pow(1 - t, 2) * x2 +
					3 * Math.pow(t, 2) * (1 - t) * x3 +
					Math.pow(t, 3) * x4);

			int y = (int) (Math.pow(1 - t, 3) * y1 +
					3 * t * Math.pow(1 - t, 2) * y2 +
					3 * Math.pow(t, 2) * (1 - t) * y3 +
					Math.pow(t, 3) * y4);

			plot(g2d, x, y, 3);
		}
	}

	// private static void plot2d(Graphics g, int x, int y, int size) {
	// 	g.fillOval(x - size/2, y - size/2, size, size);
	// }

	private static void plot(Graphics g, int x, int y, int pixelSize) {
		int size = Math.max(1, pixelSize);
		g.fillRect(x, y, size, size);
	}
}