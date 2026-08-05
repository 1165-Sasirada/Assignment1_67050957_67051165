import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Test extends JPanel {
	private static final int CANVAS_GRID_SIZE = 64;

	private static final Color pinkFrame = new Color(229, 160, 192);

	private static final Color outline = new Color(7, 4, 54);
	private static final Color skin = new Color(200, 187, 235);
	private static final Color hair = new Color(72, 80, 134);
	private static final Color hair2 = new Color(69, 67, 106);
	private static final Color highlightPink = new Color(229, 135, 179);
	private static final Color highlightPurple = new Color(137, 88, 185);
	private static final Color eye = new Color(114, 83, 144);
	private static final Color shadow = new Color(146, 128, 194);

	private static final Color gold = new Color(210, 152, 80);
	private static final Color wood = new Color(142, 101, 87);
	private static final Color wood2 = new Color(174, 140, 122);
	private static final Color curtain = new Color(124, 44, 96);
	private static final Color curtain2 = new Color(144, 65, 116);
	private static final Color day = new Color(147, 179, 237);
	private static final Color night = new Color(42, 38, 106);

	private static final Color tub = new Color(193, 218, 240);
	private static final Color tubShadow = new Color(174, 199, 222);
	private static final Color tubLight = new Color(214, 231, 246);

	private static final Color bubble = new Color(233, 249, 255);
	private static final Color bubbleShadow = new Color(137, 174, 206);

	private static final Color cookie0 = new Color(93, 61, 52);
	private static final Color cookie1 = new Color(184, 133, 91);
	private static final Color cookie2 = new Color(220, 178, 142);

	private int currentFrame = 0;
	private final Timer animationTimer;

	public Test() {
		setBackground(new Color(255, 239, 246));

		animationTimer = new Timer(500, e -> {
			currentFrame = (currentFrame + 1) % 4;
			repaint();
		});
		animationTimer.start();
	}

	public static void main(String[] args) {
		Test t = new Test();
		JFrame frame = new JFrame("Test Animation");

		frame.add(t);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		int windowSize = 640;
		frame.setSize(windowSize, windowSize);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		int panelWidth = getWidth();
		int panelHeight = getHeight();
		int scale = Math.max(1, Math.min(panelWidth, panelHeight) / CANVAS_GRID_SIZE);

		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		drawFrame(g2d, pinkFrame, 9, 13, 44, scale);
		drawMenu(g2d, scale, 56, 17);
		drawWindow(g2d, 15, 18, scale, day);

		// switch(currentFrame) {
		// 	case 0:
		// 		drawPonyIdle(g2d, scale, 0);
		// 		break;
		// 	case 1:
		// 		drawPonyIdle(g2d, scale, 1);
		// 		break;
		// 	case 2:
		// 		drawPonyIdle(g2d, scale, 2);
		// 		break;
		// 	case 3:
		// 		drawPonyIdle(g2d, scale, 1);
		// 		break;
		// }

		// drawBathTub(g2d, 25, 40, scale);
		// randomBubbles(g2d, scale);

		switch(currentFrame) {
			case 0:
				drawPonySleep(g2d, scale, 0);
				break;
			case 1:
				drawPonySleep(g2d, scale, 1);
				break;
			case 2:
				drawPonySleep(g2d, scale, 2);
				break;
			case 3:
				drawPonySleep(g2d, scale, 1);
				break;
		}

		drawEat(g2d, scale);
	}

	private Color getColorFromCode(char code) {
		switch (code) {
			case 'O': return outline;
			case 'H': return hair;
			case 'P': return highlightPurple;
			case 'I': return highlightPink;
			case 'A': return hair2;
			case 'S': return skin;
			case 'D': return shadow;
			case 'E': return eye;

			// case 'G': return gold;
			// case 'M': return wood;
			// case 'Q': return wood2;
			// case 'C': return curtain;
			// case 'U': return curtain2;
			// case 'N': return night;
			case 'K': return day;

			case 'B': return bubble;
			case 'Y': return bubbleShadow;

			case 'F': return pinkFrame;

			case 'c': return cookie0;
			case 'o': return cookie1;
			case 'k': return cookie2;

			case 'W': return Color.WHITE;
			case 'X': // Blank
			default:  return null;
		}
	}

	private void drawFrame(Graphics2D g2d, Color c, int row, int col, int size, int scale) {
		g2d.setColor(c);
		g2d.setStroke(new BasicStroke(scale));
		
		int screenX = col * scale;
		int screenY = row * scale;
		int screenSize = size * scale;

		int offset = scale / 2;
		g2d.drawRect(screenX + offset, screenY + offset, screenSize - scale, screenSize - scale);
	}

	private void drawElement(Graphics2D g2d, String[] og, int row, int col, int scale) {
		g2d.setStroke(new BasicStroke(scale));

		for (int r = 0; r < og.length; r++) {
			int currentRow = row + r;
			int currentCol = col;

			// Split line into run-length instruction tokens (e.g., ["4X", "10O"])
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

	private void drawPonyIdle(Graphics2D g2d, int scale, int action) {
		String[] eyeOpen = {
			"4X 10O", 
			"3X 1O 2H 1P 2I 2H 1A 2S 1O", 
			"2X 1O 2H 1P 2I 2H 2A 3S 1O",
			"1X 1O 2S 1H 1P 2I 1H 1A 3S 1D 1S 1O",
			"1X 1O 2H 1P 2I 2H 1A 3S 1D 1S 1O",
			"1O 3H 1P 2I 1H 1A 6S 1O",
			"1O 3H 1P 2I 1H 1A 5S 1A 1O",
			"1O 3H 1P 2I 1H 1A 3E 1D 1S 1A 1O",
			"3O 6A 1E 1W 2E 1D 1A 1H 1O",
			"3X 1O 4S 1E 1W 1E 1W 1E 1S 1A 1H 1O",
			"2X 2O 1D 3S 1P 1H 1P 1W 1E 1S 1A 1H 1O",
			"2X 1O 5S 1H 1P 1H 1W 2S 1A 1H 1O",
			"3X 1O 9S 1A 2H 1O",
			"4X 3O 5D 1S 4A 1O",
			"6X 1O 7S 1O 2H 1O",
			"5X 1O 2D 7S 2O",
			"5X 1O 2D 2S 3O 2S 1O",
			"6X 4O 3X 2O"
		};

		String[] eyeHalf = {
			"4X 7O 1X 3O",
			"2X 2O 1H 1P 2I 2H 1A 1O 3S 1O",
			"1X 1O 2S 1H 1P 2I 1H 1A 3S 1D 1S 1O",
			"1X 1O 2H 1P 2I 2H 1A 3S 1D 1S 1O",
			"1O 3H 1P 2I 1H 1A 6S 1O",
			"1O 3H 1P 2I 1H 1A 5S 1A 1O",
			"1O 3H 1P 2I 1H 1A 5S 1A 1O",
			"3O 1H 1P 2I 1H 1A 3E 2S 1A 1H 1O",
			"3X 1O 4A 1E 1W 1E 1W 1E 1D 1A 1H 1O",
			"2X 2O 1D 3S 1P 1H 1P 1W 1E 1S 1A 1H 1O",
			"2X 1O 5S 1H 1P 1H 1W 2S 1A 1H 1O",
			"3X 1O 9S 1A 2H 1O",
			"4X 3O 5D 1S 4A 1O",
			"6X 1O 7S 1O 2H 1O",
			"5X 1O 2D 7S 3O",
			"5X 1O 2D 2S 3O 2S 1O",
			"6X 4O 3X 2O"
		};

		String[] eyeClose = {
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
			"5X 1O 2D 7S 2O",
			"5X 1O 2D 2S 3O 2S 1O",
			"6X 4O 3X 2O"
		};

		switch (action) {
			case 0:
				drawElement(g2d, eyeOpen, 26, 25, scale);
				break;
			case 1:
				drawElement(g2d, eyeHalf, 27, 25, scale);
				break;
			case 2:
				drawElement(g2d, eyeClose, 26, 25, scale);
				break;
		};	
	}

	private void drawPonySleep(Graphics2D g2d, int scale, int action) {
		String[] sleep0 = {
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

		String[] sleep1 = {
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

		String[] z0 = {
			"3O",
			"2X 1O",
			"1X 1O",
			"1O",
			"3O"
		};

		String[] z1 =  {
			"4O",
			"3X 1O",
			"2x 1O",
			"1X 1O",
			"1O",
			"4O"
		};

		String[] b0 = {
			"1X 2K",
			"1K 1B 1W 1K",
			"1K 2B 1K",
			"1X 2K"
		};

		String[] b1 = {
			"1X 3K",
			"1K 2B 1W 1K",
			"1K 1B 2W 1K",
			"1K 4B 1K",
			"1X 1K 4B 1K",
			"2X 4K"
		};

		switch (action) {
			case 0:
				drawElement(g2d, sleep0, 26, 25, scale);
				break;
			case 1:
				drawElement(g2d, sleep1, 27, 25, scale);
				drawElement(g2d, z0, 22, 41, scale);
				drawElement(g2d, b0, 35, 25, scale);
				break;
			case 2:
				drawElement(g2d, sleep0, 26, 25, scale);
				drawElement(g2d, z0, 22, 41, scale);
				drawElement(g2d, z1, 15, 45, scale);
				drawElement(g2d, b1, 32, 21, scale);
				break;
		};
	}

	private void drawMenu(Graphics2D g2d, int scale, int row, int col) {
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

	private void drawEat(Graphics2D g2d, int scale) {
		String[] cookie0 = {
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

		String[] cookie1 = {
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

		String[] cookie2 = {
			"4X 2c",
			"3X 1c 2o 1c",
			"2X 1c 1k 2o 1c",
			"1X 1c 2o 2c 1o 1c",
			"1X 1c 2o 1c 1k 2o 1c",
			"1c 8o 1c",
			"1X 1c 1o 1c 2o 1k 3o 2c",
			"1X 1c 2o 1c 2o 2c 3o 1c",
			"2X 2c 3o 2c 3o 1c",
			"4X 2c 4o 1k 1c",
			"6X 1c 3o 1c",
			"6x 1c 2o 1c",
			"7X 2c"
		};

		String[] cookie3 = {
			"3X 3c",
			"2X 1c 1k 2o 1c",
			"1X 1c 5o 1c",
			"1X 1c 2o 1k 3o 1c",
			"1c 1o 1c 2o 2c 1o 1c",
			"1X 1c 3o 3c",
			"2X 2c 2o 1c",
			"4X 2c"
		};

		// drawElement(g2d, cookie0, 37, 18, scale);
		// drawElement(g2d, cookie1, 37, 18, scale);
		// drawElement(g2d, cookie2, 37, 18, scale);
		drawElement(g2d, cookie3, 39, 21, scale);
	}

	private void drawWindow(Graphics2D g2d, int row, int col, int scale, Color timeOfDay) {
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

		// window glare
		g2d.setColor(Color.WHITE);
		int startX = x1 + scale * 13;
		int startY = y1 + scale;

		for (int currentY = startY; currentY < y2 - scale; currentY += scale) {
			g2d.fillRect(startX, currentY, scale, scale);
			g2d.fillRect(startX + (2 * scale), currentY, scale, scale);
			startX -= scale;
		}

		// wooden frame
		g2d.setColor(wood);
		drawFrame(g2d, wood, row, col, 16, scale);
		g2d.drawLine(x1 + scale, midY, x2 - scale, midY);
		g2d.drawLine(midX, y1 + scale, midX, y2 - scale);

		// curtain rod
		g2d.setColor(gold);
		g2d.drawLine(x1 - scale, y1 - scale, x2 + scale, y1 - scale); // rod
		g2d.drawLine(x1 - scale, y1 - 2 * scale, x1 - scale, y1); // left
		g2d.drawLine(x2 + scale, y1 - 2 * scale, x2 + scale, y1); // right

		// curtain
		int step = 0; 
		for (int currentX = x1; currentX < midX - scale; currentX += scale) {
			if (step % 2 == 0) {
				g2d.setColor(curtain);
				g2d.drawLine(currentX, y1 - scale, currentX, y1 + size);
			} else {
				g2d.setColor(curtain2);
				g2d.drawLine(currentX, y1, currentX, y1 + size);
			}
			step++;
		}
	}

	private void drawBathTub(Graphics2D g2d, int row, int col, int scale) {
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

	private void randomBubbles(Graphics2D g2d, int scale) {
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



/*

String[] windowDay = {
			"1G 20X 1G",
			"3G 1C 1G 1C 1G 1C 6G 1U 1G 1C 1G 1C 3G",
			"1G 1X 4C 1U 1C 6X 1U 5C 1X 1G",
			"2X 2C 1U 1C 1U 1C 3M 3Q 1U 1C 1U 3C",
			"1X 2C 2U 1C 1U 8M 1C 1U 1C 1U 2C",
			"1X 2C 2U 2C 3K 1Q 1M 3K 1C 1U 1C 1U 2C",
			"1X 2C 2U 1C 1W 3K 1Q 1M 4K 1U 1C 1U 2C",
			"1X 1U 1C 1U 2C 1K 1W 2K 1Q 1M 4K 1U 1C 1U 1C 1U",
			"1X 1U 3C 1K 1W 1K 1W 1K 1Q 1M 5K 1C 1U 1C 1U",
			"1X 1U 3C 2K 1W 1K 1W 1Q 1M 5K 2C 2U",
			"1X 1C 1G 1C 1M 6Q 7M 1C 1G 1U",
			"1X 3G 7M 6Q 1M 3G",
			"1X 1C 1G 1C 1M 5K 1Q 1M 1W 4K 1M 1C 1G 1C",
			"1X 4C 5K 1Q 1M 1K 1W 3K 4C",
			"1X 1C 1U 2C 5K 1Q 1M 1W 1K 1W 2K 2C 1U 1C",
			"1X 1C 1U 3C 4K 1Q 1M 1K 1W 1K 1W 3C 1U 1C",
			"1X 1C 2U 1C 1U 4K 1Q 1M 2K 1W 1K 1U 2C 2U",
			"1X 1C 2U 1C 1U 1C 4Q 4M 1C 2U 1C 2U",
			"1X 1C 2U 1C 1U 1C 4M 4Q 3U 2C 1U",
			"1X 2C 1U 1C 2U 1C 6X 1C 3U 2C 1U",
			"2X 3C 1U 2C 6X 3C 1U 2C"
		};

drawElement(g2d, windowDay, 13, 17, scale);

*/