package nl.joshuaslik.tudelft.SEM.control;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;


/**
 * Controller for the main menu UI.
 * @author Bastijn
 */
public class MainMenuController {
	
	@FXML
	private Button playButton, optionsButton, quitButton;

	/**
	 * Handles clicking of the start button
	 * @param event the click of the button
	 * @throws IOException thrown when FXML file could not be parsed
	 */
	@FXML
	protected void handlePlayButton(ActionEvent event) throws IOException {
		GameController.start();
	}
	
	/**
	 * Handles clicking of the options button
	 * @param event the click of the button
	 * @throws IOException thrown when FXML file could not be parsed
	 */
	@FXML
	protected void handleOptionsButton(ActionEvent event) throws IOException {
		//OptionsController.start();
	}
	
	/**
	 * Handles clicking of the quit button
	 * @param event the click of the button
	 * @throws IOException thrown when FXML file could not be parsed
	 */
	@FXML
	protected void handleQuitButton(ActionEvent event) throws IOException {
		System.exit(0);
	}
}
