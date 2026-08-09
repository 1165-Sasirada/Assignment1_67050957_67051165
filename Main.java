import javax.swing.*;
import java.awt.*;

public class Main {
	public static enum Scene {
		IDLE,
		BATH,
		SLEEP,
		EAT
	}

	private static Scene currentScene = Scene.IDLE;

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
			Timer timer = new Timer(500, e -> {
				int currentTick = tamagotchiPanel.getTicks() % 39;

				if (currentTick < 10) {
					setScene(Scene.IDLE);
				}
				else if (currentTick < 20) {
					setScene(Scene.SLEEP);
				}
				else if (currentTick < 31) {
					setScene(Scene.EAT);
				}
				else {
					setScene(Scene.BATH);
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