package nl.joshuaslik.tudelft.SEM.control;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Controller for the game UI.
 * @author Bastijn
 */
public class GameController {
	
	private static Pane pane;
	
	public static void start(Stage stage) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Class.class.getResource("/data/gui/pages/GameGUI.fxml"));
		pane = loader.load();
		Scene scene = new Scene(pane);
		
		stage.setScene(scene);
		stage.setFullScreen(true);
		stage.setFullScreenExitHint("");
		stage.show();
	}
}
