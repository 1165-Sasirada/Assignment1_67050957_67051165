import java.awt.*;
// import javax.swing.*;

public class Fingers {
	public static void drawFingerRightStill(Graphics2D g2d) {
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
	}

	public static void drawFingerRightPress(Graphics2D g2d) {
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
		Tamagotchi.bezierCurve(g2d, 453, 455, 
									408, 343, 
									440, 350, 
									475, 448);
		Tamagotchi.bezierCurve(g2d, 453, 455, 
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
	}
}
