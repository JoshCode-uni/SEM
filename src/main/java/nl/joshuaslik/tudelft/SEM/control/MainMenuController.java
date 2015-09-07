package nl.joshuaslik.tudelft.SEM.control;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;


/**
 * Controller for the main menu UI.
 * @author Bastijn
 */
public class MainMenuController {
	
	@FXML
	private Button startButton, optionsButton, quitButton;

	/**
	 * Handles clicking of the start button
	 * @param event the click of the button
	 * @throws IOException thrown when FXML file could not be parsed
	 */
	@FXML
	protected void handleStartButton(KeyEvent event) throws IOException {
		//GameController.start();
	}
	
	/**
	 * Handles clicking of the options button
	 * @param event the click of the button
	 * @throws IOException thrown when FXML file could not be parsed
	 */
	@FXML
	protected void handleOptionsButton(KeyEvent event) throws IOException {
	//	OptionsController.start();
	}
	
	/**
	 * Handles clicking of the quit button
	 * @param event the click of the button
	 * @throws IOException thrown when FXML file could not be parsed
	 */
	@FXML
	protected void handleQuitButton(KeyEvent event) throws IOException {
		System.exit(0);
	}
}
