import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.function.Consumer;

public class Fingers {
	private static final Color SKIN_COLOR = Color.decode("#fff5e9");
	private static final Color NAIL_COLOR = Color.decode("#b6465f");
	
	//หลักการคือสร้าง Buffered Image แล้ววาดภาพลงใน Buffer แล้วค่อยเติมสี
	private static void filledFinger(Graphics2D destination,
			Consumer<Graphics2D> drawOutline,
			int skinX, int skinY,
			int nailX, int nailY) {
		BufferedImage fingerImage = Tamagotchi.createBufferedImage(
				Tamagotchi.TRANSPARENT,
				(fingerGraphics, buffer) -> {
					fingerGraphics.setColor(Tamagotchi.lineColor);
					drawOutline.accept(fingerGraphics);

					Tamagotchi.floodFill(buffer, skinX, skinY,
							Tamagotchi.TRANSPARENT, SKIN_COLOR);
					Tamagotchi.floodFill(buffer, nailX, nailY,
							Tamagotchi.TRANSPARENT, NAIL_COLOR);
				});

		destination.drawImage(fingerImage, 0, 0, null);
	}

	public static void drawFingerRightStill(Graphics2D g2d) {
		filledFinger(g2d, Fingers::drawFingerRightStillOutline,
				465, 540, 470, 405);
	}

	private static void drawFingerRightStillOutline(Graphics2D g2d) {
		// finger left
		Tamagotchi.bezierCurve(g2d, 430, 680, 
									430, 590, 
									430, 540, 
									438, 523);
		Tamagotchi.bezierCurve(g2d, 438, 523, 
									430, 440, 
									433, 425, 
									457, 390);

		// nail
		Tamagotchi.bezierCurve(g2d, 468, 455, 
									438, 343, 
									475, 350, 
									490, 448);
		Tamagotchi.bezierCurve(g2d, 468, 455, 
									478, 458, 
									486, 458, 
									490, 448);

		// finger right
		Tamagotchi.bezierCurve(g2d, 490, 448, 
									493, 455, 
									493, 470, 
									501, 508);
		Tamagotchi.bezierCurve(g2d, 501, 508, 
									505, 530, 
									513, 575, 
									520, 670);
		
		Tamagotchi.bresenhamLine(g2d, 425, 599, 525, 599, 3);
	}

	public static void drawFingerRightPress(Graphics2D g2d) {
		filledFinger(g2d, Fingers::drawFingerRightPressOutline,
				465, 540, 445, 405);
	}

	private static void drawFingerRightPressOutline(Graphics2D g2d) {
		// finger left
		Tamagotchi.bezierCurve(g2d, 430, 680, 
									430, 590, 
									430, 540, 
									438, 523);
		Tamagotchi.bezierCurve(g2d, 438, 523, 
									415, 440, 
									418, 425, 
									431, 390);

		// nail
		Tamagotchi.bezierCurve(g2d, 457, 455, 
									408, 343, 
									440, 350, 
									475, 448);
		Tamagotchi.bezierCurve(g2d, 457, 455, 
									463, 458, 
									471, 458, 
									475, 448);

		// finger right
		Tamagotchi.bezierCurve(g2d, 475, 448, 
									478, 458, 
									488, 470, 
									501, 508);
		Tamagotchi.bezierCurve(g2d, 501, 508, 
									505, 530, 
									513, 575, 
									520, 670);

		Tamagotchi.bresenhamLine(g2d, 425, 599, 525, 599, 3);
	}

	public static void drawFingerLeftStill(Graphics2D g2d) {
		filledFinger(g2d, Fingers::drawFingerLeftStillOutline,
				140, 540, 140, 405);
	}

	private static void drawFingerLeftStillOutline(Graphics2D g2d) {
		// finger left
		Tamagotchi.bezierCurve(g2d, 175, 680, 
									175, 590, 
		  		  					175, 540, 
									167, 523);
		Tamagotchi.bezierCurve(g2d, 167, 523, 
									175, 440, 
									172, 425, 
									148, 390);

		// nail
		Tamagotchi.bezierCurve(g2d, 137, 455, 
									167, 343, 
									130, 350, 
									115, 448);
		Tamagotchi.bezierCurve(g2d, 137, 455, 
									127, 458, 
									119, 458, 
									115, 448);

		// finger right
		Tamagotchi.bezierCurve(g2d, 115, 448, 
									112, 455, 
									112, 470, 
									104, 508);
		Tamagotchi.bezierCurve(g2d, 104, 508, 
									100, 530, 
									102, 575, 
									85, 670);

		Tamagotchi.bresenhamLine(g2d, 80, 599, 180, 599, 3);
	}

	public static void drawFingerLeftPress(Graphics2D g2d) {
		filledFinger(g2d, Fingers::drawFingerLeftPressOutline,
				140, 540, 165, 405);
	}

	private static void drawFingerLeftPressOutline(Graphics2D g2d) {
	// finger left
	Tamagotchi.bezierCurve(g2d, 175, 680, 
								175, 590, 
								175, 540, 
								167, 523);
	Tamagotchi.bezierCurve(g2d, 167, 523, 
								190, 440, 
								187, 425, 
								174, 390);

	// nail
	Tamagotchi.bezierCurve(g2d, 148, 455, 
								197, 343, 
								165, 350, 
								130, 448);
	Tamagotchi.bezierCurve(g2d, 148, 455, 
								142, 458, 
								134, 458, 
								130, 448);

	// finger right
	Tamagotchi.bezierCurve(g2d, 130, 448, 
								127, 458, 
								117, 470, 
								104, 508);
	Tamagotchi.bezierCurve(g2d, 104, 508, 
								100, 530, 
								92, 575, 
								85, 670);

	Tamagotchi.bresenhamLine(g2d, 80, 599, 180, 599, 3);
	}

	public static void drawFingerRightReach0(Graphics2D g2d) {
		filledFinger(g2d, Fingers::drawFingerRightReach0Outline,
				420, 540, 380, 415);
	}

	private static void drawFingerRightReach0Outline(Graphics2D g2d) {
		// finger left
		Tamagotchi.bezierCurve(g2d, 410, 690, 
									400, 600, 
									400, 550, 
									398, 533);
		Tamagotchi.bezierCurve(g2d, 398, 533, 
									355, 450, 
									358, 435, 
									371, 400);

		// nail
		Tamagotchi.bezierCurve(g2d, 397, 465, 
									348, 353, 
									380, 360, 
									415, 458);
		Tamagotchi.bezierCurve(g2d, 397, 465, 
									403, 468, 
									421, 468, 
									415, 458);

		// finger right
		Tamagotchi.bezierCurve(g2d, 415, 458, 
									418, 468, 
									428, 480, 
									451, 518);
		Tamagotchi.bezierCurve(g2d, 451, 518, 
									455, 540, 
									473, 585, 
									480, 680);

		Tamagotchi.bresenhamLine(g2d, 390, 599, 490, 599, 3);
	}

	public static void drawFingerRightReach1(Graphics2D g2d) {
		filledFinger(g2d, Fingers::drawFingerRightReach1Outline,
				380, 550, 340, 425);
	}

	private static void drawFingerRightReach1Outline(Graphics2D g2d) {
		// finger left
		Tamagotchi.bezierCurve(g2d, 370, 700, 
									360, 610, 
									360, 560, 
									358, 543);
		Tamagotchi.bezierCurve(g2d, 358, 543, 
									315, 460, 
									318, 445, 
									331, 410);

		// nail
		Tamagotchi.bezierCurve(g2d, 357, 475, 
									308, 363, 
									340, 370, 
									375, 468);
		Tamagotchi.bezierCurve(g2d, 357, 475, 
									363, 478, 
									371, 478, 
									375, 468);

		// finger right
		Tamagotchi.bezierCurve(g2d, 375, 468, 
									378, 478, 
									388, 490, 
									411, 528);
		Tamagotchi.bezierCurve(g2d, 411, 528, 
									415, 550, 
									423, 595, 
									440, 690);

		Tamagotchi.bresenhamLine(g2d, 350, 599, 450, 599, 3);
	}

	public static void drawFingerRightPressMiddle(Graphics2D g2d) {
		filledFinger(g2d, Fingers::drawFingerRightPressMiddleOutline,
				335, 580, 315, 455);
	}

	private static void drawFingerRightPressMiddleOutline(Graphics2D g2d) {
		// finger left
		Tamagotchi.bezierCurve(g2d, 300, 730, 
									300, 640, 
									300, 590, 
									308, 573);
		Tamagotchi.bezierCurve(g2d, 308, 573, 
									285, 490, 
									288, 475, 
									301, 440);

		// nail
		Tamagotchi.bezierCurve(g2d, 327, 505, 
									278, 393, 
									310, 400, 
									345, 498);
		Tamagotchi.bezierCurve(g2d, 327, 505, 
									333, 508, 
									341, 508, 
									345, 498);

		// finger right
		Tamagotchi.bezierCurve(g2d, 345, 498,                                                                                     
									348, 508, 
									358, 520, 
									371, 558);
		Tamagotchi.bezierCurve(g2d, 371, 558, 
									375, 580, 
									383, 625, 
									390, 720);

		Tamagotchi.bresenhamLine(g2d, 290, 599, 400, 599, 3);
	}
}
