import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Girl {
	// Color definitions
	private static final Color SKIN_COLOR = Color.decode("#fde9e9");
	private static final Color HAIR_COLOR = Color.decode("#775c55");
	private static final Color SHIRT_COLOR = Color.decode("#d7e3f9");

	public static void drawGirlOutline(Graphics2D g2d, int frameIndex) {
		boolean isBlinking = frameIndex % 4 == 1;
		int lineSize = 3;
		// Hair Left
		Tamagotchi.bezierCurve(g2d, 300, 115, 230, 75, 150, 195, 150, 255, lineSize);
		Tamagotchi.bezierCurve(g2d, 150, 255, 160, 310, 165, 340, 150, 380, lineSize);
		Tamagotchi.bezierCurve(g2d, 150, 380, 130, 430, 125, 480, 165, 520, lineSize);
		// Hair Right
		Tamagotchi.bezierCurve(g2d, 300, 115, 370, 75, 450, 195, 450, 255, lineSize);
		Tamagotchi.bezierCurve(g2d, 450, 255, 440, 310, 435, 340, 450, 380, lineSize);
		Tamagotchi.bezierCurve(g2d, 450, 380, 470, 430, 475, 480, 435, 520, lineSize);

		// Middle Bang
		Tamagotchi.bezierCurve(g2d, 278, 256, 300, 262, 322, 262, 344, 256, lineSize);
		// Middle Bang Left
		Tamagotchi.bezierCurve(g2d, 266, 172, 261, 208, 256, 220, 278, 256, lineSize);
		// Middle Bang Right
		Tamagotchi.bezierCurve(g2d, 312, 184, 312, 208, 322, 232, 344, 256, lineSize);

		// Bang Left
		Tamagotchi.bezierCurve(g2d, 266, 172, 266, 196, 222, 256, 178, 274, lineSize);
		// Bang Right
		Tamagotchi.bezierCurve(g2d, 312, 184, 322, 208, 366, 256, 422, 274, lineSize);

		// Ear Left
		Tamagotchi.bezierCurve(g2d, 200, 268, 166, 268, 166, 316, 200, 316, lineSize);
		Tamagotchi.bezierCurve(g2d, 197, 288, 192, 288, 187, 293, 187, 298, lineSize);
		// Ear Right
		Tamagotchi.bezierCurve(g2d, 400, 268, 434, 268, 434, 316, 400, 316, lineSize);
		Tamagotchi.bezierCurve(g2d, 403, 288, 408, 288, 413, 293, 413, 298, lineSize);

		// Jawline
		Tamagotchi.bezierCurve(g2d, 200, 316, 233, 412, 366, 412, 400, 316, lineSize);

		// Neck Left
		Tamagotchi.bezierCurve(g2d, 275, 387, 275, 410, 275, 425, 225, 430, lineSize);
		// Neck Right
		Tamagotchi.bezierCurve(g2d, 325, 387, 325, 410, 325, 425, 375, 430, lineSize);

		// Shirt Neckline
		Tamagotchi.bezierCurve(g2d, 265, 420, 280, 450, 320, 450, 335, 420, lineSize);

		// Shirt Sleeve Left
		Tamagotchi.bezierCurve(g2d, 225, 430, 185, 450, 185, 480, 165, 520, lineSize);
		Tamagotchi.bresenhamLine(g2d, 165, 520, 215, 535, 3);
		// Shirt Left
		Tamagotchi.bezierCurve(g2d, 220, 490, 215, 520, 215, 530, 225, 570, lineSize);
		Tamagotchi.bresenhamLine(g2d, 225, 570, 220, 600, 3);
		// Arm Left
		Tamagotchi.bresenhamLine(g2d, 185, 528, 175, 600, 3);

		// Shirt Sleeve Right
		Tamagotchi.bezierCurve(g2d, 375, 430, 415, 450, 415, 480, 435, 520, lineSize);
		Tamagotchi.bresenhamLine(g2d, 435, 520, 385, 535, 3);
		// Shirt Right
		Tamagotchi.bezierCurve(g2d, 380, 490, 385, 520, 385, 530, 375, 570, lineSize);
		Tamagotchi.bresenhamLine(g2d, 375, 570, 380, 600, 3);
		// Arm Right
		Tamagotchi.bresenhamLine(g2d, 415, 528, 425, 600, 3);

		// Eyebrow Left
		Tamagotchi.bezierCurve(g2d, 230, 255, 235, 245, 253, 236, 268, 250, lineSize);

		// Eyebrow Right
		Tamagotchi.bezierCurve(g2d, 332, 250, 347, 236, 365, 245, 370, 255, lineSize);

		if (isBlinking) {
			Tamagotchi.bezierCurve(g2d, 234, 280, 245, 288, 257, 288, 268, 280, lineSize);
			Tamagotchi.bezierCurve(g2d, 332, 280, 343, 288, 355, 288, 366, 280, lineSize);
		} else {
			// Eyelash Left
			Tamagotchi.bezierCurve(g2d, 234, 280, 240, 270, 253, 256, 268, 270, lineSize);
			Tamagotchi.bresenhamLine(g2d, 238, 270, 233, 265, 3);
			Tamagotchi.bresenhamLine(g2d, 245, 268, 240, 260, 3);
			// Eyelash Right
			Tamagotchi.bezierCurve(g2d, 332, 270, 347, 256, 360, 270, 366, 280, lineSize);
			Tamagotchi.bresenhamLine(g2d, 362, 270, 367, 265, 3);
			Tamagotchi.bresenhamLine(g2d, 355, 268, 360, 260, 3);

			// Eye Left
			Tamagotchi.midpointCircle(g2d, 256, 285, 15, 3);
			// Eye Right
			Tamagotchi.midpointCircle(g2d, 344, 285, 15, 3);
		}

		// Nose
		Tamagotchi.midpointCircle(g2d, 300, 310, 3, 3);

		// Mouth
		Tamagotchi.bezierCurve(g2d, 290, 330, 297, 335, 305, 335, 310, 330, lineSize);
	}

	public static void drawGirl(Graphics2D destination, int frameIndex) {
		BufferedImage girlImage = Tamagotchi.createBufferedImage(
				Tamagotchi.TRANSPARENT,
				(girlGraphics, buffer) -> {
					// 1. วาดเส้นลง buffer
					girlGraphics.setColor(Color.decode("#3d322d"));
					drawGirlOutline(girlGraphics, frameIndex);

					// 2. เติมสี
					Tamagotchi.floodFill(buffer, 300, 350, Tamagotchi.TRANSPARENT, SKIN_COLOR);
					Tamagotchi.floodFill(buffer, 337, 233, Tamagotchi.TRANSPARENT, SKIN_COLOR);
					Tamagotchi.floodFill(buffer, 299, 418, Tamagotchi.TRANSPARENT, SKIN_COLOR);
					Tamagotchi.floodFill(buffer, 204, 566, Tamagotchi.TRANSPARENT, SKIN_COLOR);
					Tamagotchi.floodFill(buffer, 401, 566, Tamagotchi.TRANSPARENT, SKIN_COLOR);
					Tamagotchi.floodFill(buffer, 165, 450, Tamagotchi.TRANSPARENT, HAIR_COLOR);
					Tamagotchi.floodFill(buffer, 250, 500, Tamagotchi.TRANSPARENT, SHIRT_COLOR);

					boolean isBlinking = frameIndex % 4 == 1;

					if (!isBlinking) {
						Color eyeColor = Color.decode("#463734");

						Tamagotchi.floodFill(buffer, 256, 285,Tamagotchi.TRANSPARENT, eyeColor);
						Tamagotchi.floodFill(buffer, 344, 285,Tamagotchi.TRANSPARENT, eyeColor);

						// Eye highlights: draw the boundary with Midpoint Circle, then fill it.
						girlGraphics.setColor(Color.WHITE);
						Tamagotchi.midpointCircle(girlGraphics, 253, 280, 3, 1);
						Tamagotchi.midpointCircle(girlGraphics, 341, 280, 3, 1);
						Tamagotchi.floodFill(buffer, 253, 280, eyeColor, Color.WHITE);
						Tamagotchi.floodFill(buffer, 341, 280, eyeColor, Color.WHITE);
					}
				});

		// 3. นำภาพที่ระบายสีเสร็จแล้วไปวาดบน Panel
		destination.drawImage(girlImage, 0, 0, null);
	}
}
