package nl.joshuaslik.tudelft.SEM.control;

import java.io.IOException;

import nl.joshuaslik.tudelft.SEM.model.container.Point;
import nl.joshuaslik.tudelft.SEM.model.container.Vector;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;


/**
 * Controller for the main menu UI.
 * @author Bastijn
 */
public class MainMenuController {
	
	@FXML
	private static Pane pane;
	
	@FXML
	private Button playButton, chooseLevelButton, optionsButton, quitButton;

	/**
	 * Starts the Main Menu scene.
	 * @param stage the current stage
	 * @throws IOException thrown when FXML is not parsed
	 */
	public static void start(Stage stage) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Class.class.getResource("/data/gui/pages/MainMenu.fxml"));
		pane = loader.load();
		Scene scene = new Scene(pane);
		
		stage.setScene(scene);
		stage.setFullScreen(true);
		stage.setFullScreenExitHint("");
		stage.show();
	}
	
	
	/**
	 * Handles clicking of the start button
	 * @param event the click of the button
	 * @throws IOException thrown when FXML file could not be parsed
	 */
	@FXML
	protected void handlePlayButton(ActionEvent event) throws IOException {
		System.out.println("Play button pressed!");
		Stage stage = (Stage) playButton.getScene().getWindow();
		GameController.start(stage);
	}
	
	/**
	 * Handles clicking of the choose level button
	 * @param event the click of the button
	 * @throws IOException thrown when FXML file could not be parsed
	 */
	@FXML
	protected void handleChooseLevelButton(ActionEvent event) throws IOException {
		System.out.println("Choose Level button pressed!");
		Stage stage = (Stage) chooseLevelButton.getScene().getWindow();
		ChooseLevelController.start(stage);
	}
	
	/**
	 * Handles clicking of the options button
	 * @param event the click of the button
	 * @throws IOException thrown when FXML file could not be parsed
	 */
	@FXML
	protected void handleOptionsButton(ActionEvent event) throws IOException {
		System.out.println("Options button pressed!");
		//Stage stage = (Stage) optionsButton.getScene().getWindow();
		//OptionsController.start(stage);
	}
	
	/**
	 * Handles clicking of the quit button
	 * @param event the click of the button
	 * @throws IOException thrown when FXML file could not be parsed
	 */
	@FXML
	protected void handleQuitButton(ActionEvent event) throws IOException {
		System.out.println("Quit button pressed!");
		System.exit(0);
	}
}
