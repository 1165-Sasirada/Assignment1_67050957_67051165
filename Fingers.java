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
	}

	public static void drawFingerLeftStill(Graphics2D g2d) {
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
	}

	public static void drawFingerLeftPress(Graphics2D g2d) {
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
	}	
}
