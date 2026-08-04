import javax.swing.*;
import java.awt.*;

public class Main {
	public static enum Scene {
		IDLE,
		BATH,
		SLEEP
	}

	private static Scene currentScene = Scene.BATH;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame("Tamagotchi");
			Tamagotchi tamagotchiPanel = new Tamagotchi();

			tamagotchiPanel.setPreferredSize(new Dimension(600, 600));
			frame.add(tamagotchiPanel);
			frame.pack();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);

			// Controls animation tick rate and scene progression
			Timer timer = new Timer(500, e -> {
				tamagotchiPanel.tick();
				
				// Example scene control logic
				// You can change scene based on tick count or user interactions
				/*
				if (tamagotchiPanel.getTicks() == 10) setScene(Scene.BATH);
				if (tamagotchiPanel.getTicks() == 20) setScene(Scene.SLEEP);
				*/
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