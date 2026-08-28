import javax.swing.*;
import java.awt.*;

public class Main {
	public static enum Scene {
		GIRL,
		GIRLPEEK,
		IDLE,
		BATH,
		SLEEP,
		EAT
	}

	private static Scene currentScene = Scene.GIRL;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame("Tamagotchi");
			Tamagotchi tamagotchiPanel = new Tamagotchi();

			tamagotchiPanel.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent e) {
					System.out.println("x = " + e.getX() + ", y = " + e.getY());
				}
			});

			tamagotchiPanel.setPreferredSize(new Dimension(600, 600));
			frame.add(tamagotchiPanel);
			frame.pack();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);

			// Controls animation tick rate and scene progression
			// Timer timer = new Timer(500, e -> {
			// int currentTick = tamagotchiPanel.getTicks() % 48;

			// setScene(Scene.GIRL);
			// if (currentTick < 8) {
			// setScene(Scene.GIRL);
			// } else if (currentTick < 18) {
			// setScene(Scene.IDLE);
			// } else if (currentTick < 28) {
			// setScene(Scene.SLEEP);
			// } else if (currentTick < 39) {
			// setScene(Scene.EAT);
			// } else {
			// setScene(Scene.BATH);
			// }

			// tamagotchiPanel.tick();
			// });

			// Timer timer = new Timer(500, e -> {
			// int currentTick = tamagotchiPanel.getTicks() % 66;

			// if (currentTick < 8) {
			// setScene(Scene.SLEEP); // 4 วินาที

			// } else if (currentTick < 16) {
			// setScene(Scene.IDLE); // 4 วินาที

			// } else if (currentTick < 24) {
			// setScene(Scene.BATH); // 4 วินาที

			// } else if (currentTick < 32) {
			// setScene(Scene.EAT); // 4 วินาที

			// } else if (currentTick < 40) {
			// setScene(Scene.IDLE); // 4 วินาที: กลับสู่ปกติ

			// } else if (currentTick < 46) {
			// setScene(Scene.GIRL); // 3 วินาที: คนยืน

			// } else {
			// setScene(Scene.GIRLPEEK); // 10 วินาที: เดิน → เห็น → ตกใจ
			// }

			// tamagotchiPanel.tick();
			// });
			// timer.start();
			// });

			Timer timer = new Timer(500, e -> {
				int currentTick = tamagotchiPanel.getTicks() % 60;

				if (currentTick < 8) {
					setScene(Scene.SLEEP); 			// Ticks 0-7 (4 seconds)
				} else if (currentTick < 12) {
					setScene(Scene.IDLE); 			// Ticks 8-11 (2 seconds)
				} else if (currentTick < 20) {
					setScene(Scene.BATH);			// Ticks 12-19 (4 seconds)
				} else if (currentTick < 28) {
					setScene(Scene.EAT); 			// Ticks 20-27 (4 seconds)
				} else if (currentTick < 32) {
					setScene(Scene.IDLE); 			// Ticks 28-31 (2 seconds)
				} else if (currentTick < 40) {
					setScene(Scene.GIRL); 			// Ticks 32-39 (4 seconds)
				} else {
					setScene(Scene.GIRLPEEK); 		// Ticks 40-59 (10 seconds)
				}

				tamagotchiPanel.tick();
			});
			timer.start();
		});
	}

	public static Scene getCurrentScene() {
		return currentScene;
	}

	public static void setScene(Scene scene) {
		currentScene = scene;
	}
}