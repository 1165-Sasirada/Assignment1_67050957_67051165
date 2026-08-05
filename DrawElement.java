import java.awt.*;
import java.util.Random;

public class DrawElement {
	// Palette Definitions
	public static final Color outline = new Color(7, 4, 54);
	public static final Color skin = new Color(200, 187, 235);
	public static final Color hair = new Color(72, 80, 134);
	public static final Color hair2 = new Color(69, 67, 106);
	public static final Color highlightPink = new Color(229, 135, 179);
	public static final Color highlightPurple = new Color(137, 88, 185);
	public static final Color eye = new Color(114, 83, 144);
	public static final Color shadow = new Color(146, 128, 194);

	public static final Color gold = new Color(210, 152, 80);
	public static final Color wood = new Color(142, 101, 87);
	public static final Color wood2 = new Color(174, 140, 122);
	public static final Color curtain = new Color(124, 44, 96);
	public static final Color curtain2 = new Color(144, 65, 116);
	public static final Color day = new Color(147, 179, 237);
	public static final Color night = new Color(42, 38, 106);

	public static final Color tub = new Color(193, 218, 240);
	public static final Color tubShadow = new Color(174, 199, 222);
	public static final Color tubLight = new Color(214, 231, 246);

	public static final Color bubble = new Color(233, 249, 255);
	public static final Color bubbleShadow = new Color(137, 174, 206);

	public static Color getColorFromCode(char code) {
		switch (code) {
			case 'O': return outline;
			case 'H': return hair;
			case 'P': return highlightPurple;
			case 'I': return highlightPink;
			case 'A': return hair2;
			case 'S': return skin;
			case 'D': return shadow;
			case 'E': return eye;

			case 'G': return gold;
			case 'M': return wood;
			case 'Q': return wood2;
			case 'C': return curtain;
			case 'U': return curtain2;
			case 'K': return day;
			case 'N': return night;

			case 'B': return bubble;
			case 'Y': return bubbleShadow;
			
			case 'F': return Tamagotchi.pinkFrame;
			case 'W': return Color.WHITE;
			case 'X': 
			default:  return null;
		}
	}

	public static void drawElement(Graphics2D g2d, String[] og, int row, int col, int scale) {
		g2d.setStroke(new BasicStroke(scale));

		for (int r = 0; r < og.length; r++) {
			int currentRow = row + r;
			int currentCol = col;

			String[] tokens = og[r].split("\\s+");

			for (String token : tokens) {
				if (token.isEmpty()) continue;

				int count = Integer.parseInt(token.substring(0, token.length() - 1));
				char code = token.charAt(token.length() - 1);
				Color color = getColorFromCode(code);

				if (color != null) {
					g2d.setColor(color);
					g2d.fillRect(currentCol * scale, currentRow * scale, count * scale, scale);
				}

				currentCol += count;
			}
		}
	}

	public static void drawFrame(Graphics2D g2d, Color c, int row, int col, int size, int scale) {
		g2d.setColor(c);
		g2d.setStroke(new BasicStroke(scale));

		int screenX = col * scale;
		int screenY = row * scale;
		int screenSize = size * scale;

		int offset = scale / 2;
		g2d.drawRect(screenX + offset, screenY + offset, screenSize - scale, screenSize - scale);
	}

	public static void drawMenuDay(Graphics2D g2d, int scale, int row, int col) {
		String[] sleepMenu = {
			"2X 3F",
			"1X 2F 2X 1F",
			"2F 5X 1F",
			"2F 4X 1F 1X 1F",
			"2F 5X 1F",
			"1X 2F 2X 1F",
			"2X 3F"
		};

		String[] bathMenu = {
			"2X 2F",
			"1X 1F 2X 1F",
			"2F 2X 1F 2X 1F",
			"2X 2F 2X 2F",
			"1X 1F 2X 2F 1X 1F",
			"1X 1F 5X 1F",
			"2X 5F"
		};

		String[] eatMenu = {
			"3F 2X 1F",
			"3F 1X 2F",
			"3F 1X 2F",
			"1X 1F 1X 3F",
			"1X 1F 2X 2F",
			"1X 1F 3X 1F",
			"1X 1F 3X 1F"
		};

		drawElement(g2d, sleepMenu, row, col, scale);
		drawElement(g2d, bathMenu, row, col + 15, scale);
		drawElement(g2d, eatMenu, row, col + 30, scale);
	}

	public static void drawMenuNight(Graphics2D g2d, int scale, int row, int col) {
		String[] sleepMenu = {
			"2X 3N",
			"1X 2N 2X 1N",
			"2N 5X 1N",
			"2N 4X 1N 1X 1N",
			"2N 5X 1N",
			"1X 2N 2X 1N",
			"2X 3N"
		};

		String[] bathMenu = {
			"2X 2N",
			"1X 1N 2X 1N",
			"2N 2X 1N 2X 1N",
			"2X 2N 2X 2N",
			"1X 1N 2X 2N 1X 1N",
			"1X 1N 5X 1N",
			"2X 5N"
		};

		String[] eatMenu = {
			"3N 2X 1N",
			"3N 1X 2N",
			"3N 1X 2N",
			"1X 1N 1X 3N",
			"1X 1N 2X 2N",
			"1X 1N 3X 1N",
			"1X 1N 3X 1N"
		};

		drawElement(g2d, sleepMenu, row, col, scale);
		drawElement(g2d, bathMenu, row, col + 15, scale);
		drawElement(g2d, eatMenu, row, col + 30, scale);
	}

	public static void drawWindow(Graphics2D g2d, int row, int col, int scale, Color timeOfDay) {
		int size = 16 * scale;
		int x1 = col * scale;
		int y1 = row * scale;
		int x2 = x1 + size;
		int y2 = y1 + size;

		int midX = x1 + (size / 2);
		int midY = y1 + (size / 2);

		g2d.setColor(timeOfDay);
		g2d.fillRect(x1, y1, size, size);
		g2d.setStroke(new BasicStroke(scale));

		// Window glare
		g2d.setColor(Color.WHITE);
		int startX = x1 + scale * 13;
		int startY = y1 + scale;

		for (int currentY = startY; currentY < y2 - scale; currentY += scale) {
			g2d.fillRect(startX, currentY, scale, scale);
			g2d.fillRect(startX + (2 * scale), currentY, scale, scale);
			startX -= scale;
		}

		// Wooden frame
		drawFrame(g2d, wood, row, col, 16, scale);
		g2d.setColor(wood);
		g2d.drawLine(x1 + scale, midY, x2 - scale, midY);
		g2d.drawLine(midX, y1 + scale, midX, y2 - scale);

		// Curtain rod
		g2d.setColor(gold);
		g2d.drawLine(x1 - scale, y1 - scale, x2 + scale, y1 - scale);
		g2d.drawLine(x1 - scale, y1 - 2 * scale, x1 - scale, y1);
		g2d.drawLine(x2 + scale, y1 - 2 * scale, x2 + scale, y1);

		// Curtains
		int step = 0;
		for (int currentX = x1; currentX < midX - scale; currentX += scale) {
			g2d.setColor((step % 2 == 0) ? curtain : curtain2);
			g2d.drawLine(currentX, y1 - scale, currentX, y1 + size);
			step++;
		}
	}

	public static void drawBathTub(Graphics2D g2d, int row, int col, int scale) {
		int x = row * scale;
		int y = col * scale;

		g2d.setColor(tub);
		g2d.fillRect(x, y, 21 * scale, 2 * scale);
		g2d.fillRect(x + 2 * scale, y + 2 * scale, 17 * scale, 4 * scale);
		g2d.fillRect(x + 3 * scale, y + 6 * scale, 15 * scale, scale / 2);
		g2d.fillRect(x + 4 * scale, y + 6 * scale, 13 * scale, scale);

		g2d.setColor(tubShadow);
		g2d.fillRect(x + scale, y + 2 * scale, 19 * scale, scale / 2);
		g2d.fillRect(x + 5 * scale, y + 7 * scale, 3 * scale, scale / 2);
		g2d.fillRect(x + 13 * scale, y + 7 * scale, 3 * scale, scale / 2);

		g2d.setColor(tubLight);
		g2d.fillRect(x, y, 16 * scale, scale / 2);
		g2d.fillRect(x, y, 13 * scale, scale);
		g2d.fillRect(x, y + scale, 10 * scale, scale / 2);
		g2d.fillRect(x + 18 * scale, y, 3 * scale, scale / 2);
	}

	public static void randomBubbles(Graphics2D g2d, int scale) {
		String[] type1 = {
			"1X 1B",
			"1B 1W 1B",
			"1X 1B 1Y"
		};

		String[] type2 = {
			"1W 1B",
			"1Y 1B"
		};

		int minRow = 10;
		int minCol = 14;
		int maxRow = 50;
		int maxCol = 52;

		Random rand = new Random();

		for (int i = 0; i < 16; i++) {
			int randomCol = minCol + rand.nextInt(maxCol - minCol + 1);
			int randomRow = minRow + rand.nextInt(maxRow - minRow + 1);
			int bubbleType = rand.nextInt(3);

			switch (bubbleType) {
				case 0:
					drawElement(g2d, type1, randomRow, randomCol, scale);
					break;
				case 1:
					drawElement(g2d, type2, randomRow, randomCol, scale);
					break;
				case 2:
					g2d.setColor(bubbleShadow);
					g2d.fillRect(randomCol * scale, randomRow * scale, scale, scale);
					break;
			}
		}
	}
}