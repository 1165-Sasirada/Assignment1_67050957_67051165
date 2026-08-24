import java.awt.Graphics2D;

public class Girl {
	public static void drawGirlOutline(Graphics2D g2d) {
		// Middle Bang
		Tamagotchi.bezierCurve(g2d, 278, 256, 300, 262, 322, 262, 344, 256);
		// Middle Bang Left
		Tamagotchi.bezierCurve(g2d, 266, 172, 261, 208, 256, 220, 278, 256);
		// Middle Bang Right
		Tamagotchi.bezierCurve(g2d, 312, 184, 312, 208, 322, 232, 344, 256);

		// Bang Left
		Tamagotchi.bezierCurve(g2d, 266, 172, 266, 196, 222, 256, 178, 274);
		// Bang Right
		Tamagotchi.bezierCurve(g2d, 312, 184, 322, 208, 366, 256, 422, 274);

		// Ear Left
		Tamagotchi.bezierCurve(g2d, 200, 268, 166, 268, 166, 316, 200, 316);
		// Ear Right
		Tamagotchi.bezierCurve(g2d, 400, 268, 434, 268, 434, 316, 400, 316);

		// Jawline
		Tamagotchi.bezierCurve(g2d, 200, 316, 233, 412, 366, 412, 400, 316);

		// Eyelash Left
		Tamagotchi.bezierCurve(g2d, 234, 280, 240, 270, 253, 266, 268, 270);
		// Eyelash Right
		Tamagotchi.bezierCurve(g2d, 332, 270, 347, 266, 360, 270, 366, 280);

		// Eye Left
		Tamagotchi.midpointCircle(g2d, 256, 287, 15, 3);
		// Eye Right
		Tamagotchi.midpointCircle(g2d, 344, 287, 15, 3);

		// Nose
		Tamagotchi.midpointCircle(g2d, 300, 310, 3, 3);

		// Mouth
		Tamagotchi.bezierCurve(g2d, 290, 330, 297, 335, 305, 335, 310, 330);
	}
}
