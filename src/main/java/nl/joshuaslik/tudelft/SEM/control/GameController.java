package nl.joshuaslik.tudelft.SEM.control;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * Controller for the game UI.
 * @author Bastijn
 */
public class GameController {
	
	@FXML
	private static Pane pane;
	
	@FXML
	private static Rectangle timeRectangle;
	
	@FXML
	private static Text livesText, levelText, scoreText;
	
	/**
	 * Starts the Game GUI scene.
	 * @param stage the current stage
	 * @throws IOException thrown when FXML is not parsed
	 */
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
	
	/**
	 * Method to return the timeRectangle
	 * @return Rectangle timeRectangle
	 */
	//I created this method because i thought it would come in handy with the changing the length
	//Of this rectangle. I don't really know if this method is in the right place though.
	//To alter the length of this rectangle, we can use timeRectangle.setWidth(X);
	public static Rectangle getTimeRectangle() {
		return timeRectangle;
	}
}
