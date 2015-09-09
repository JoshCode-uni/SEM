package nl.joshuaslik.tudelft.SEM.control;

import java.io.IOException;

import nl.joshuaslik.tudelft.SEM.Launcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class ChooseLevelController {

	@FXML
	private static Pane pane;
	
	@FXML
	private Button playButton, chooseLevelButton, optionsButton, quitButton;
	
	@FXML
	private Button level1Button, level2Button, level3Button, level4Button, level5Button, mainMenuButton;
	
	/**
	 * Starts the Game GUI scene.
	 * @param stage the current stage
	 * @throws IOException thrown when FXML is not parsed
	 */
	public static void start(Stage stage) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Class.class.getResource("/data/gui/pages/ChooseLevel.fxml"));
		pane = loader.load();
		Launcher.getBorderPane().setCenter(pane);
	}
	
	/**
	 * Handles clicking of the Level 1 button
	 * @param event the click of the button
	 * @throws IOException thrown when FXML file could not be parsed
	 */
	@FXML
	protected void handleLevel1Button(ActionEvent event) throws IOException {
		System.out.println("Level 1 button pressed!");
		//Stage stage = (Stage) level1Button.getScene().getWindow();
		
	}
	/**
	 * Handles clicking of the Level 2 button
	 * @param event the click of the button
	 * @throws IOException thrown when FXML file could not be parsed
	 */
	@FXML
	protected void handleLevel2Button(ActionEvent event) throws IOException {
		System.out.println("Level 2 button pressed!");
		//Stage stage = (Stage) level2Button.getScene().getWindow();
		
	}
	
	/**
	 * Handles clicking of the Level 3 button
	 * @param event the click of the button
	 * @throws IOException thrown when FXML file could not be parsed
	 */
	@FXML
	protected void handleLevel3Button(ActionEvent event) throws IOException {
		System.out.println("Level 3 button pressed!");
		//Stage stage = (Stage) level3Button.getScene().getWindow();
		
	}
	
	/**
	 * Handles clicking of the Level 4 button
	 * @param event the click of the button
	 * @throws IOException thrown when FXML file could not be parsed
	 */
	@FXML
	protected void handleLevel4Button(ActionEvent event) throws IOException {
		System.out.println("Level 4 button pressed!");
		//Stage stage = (Stage) level4Button.getScene().getWindow();
		
	}
	
	/**
	 * Handles clicking of the Level 5 button
	 * @param event the click of the button
	 * @throws IOException thrown when FXML file could not be parsed
	 */
	@FXML
	protected void handleLevel5Button(ActionEvent event) throws IOException {
		System.out.println("Level 5 button pressed!");
		//Stage stage = (Stage) level5Button.getScene().getWindow();
	}
	
	/**
	 * Handles clicking of the main menu button
	 * @param event the click of the button
	 * @throws IOException thrown when FXML file could not be parsed
	 */
	@FXML
	protected void handleMainMenuButton(ActionEvent event) throws IOException {
		System.out.println("Main Menu button pressed!");
		Stage stage = (Stage) mainMenuButton.getScene().getWindow();
		MainMenuController.start(stage);
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
		Stage stage = (Stage) optionsButton.getScene().getWindow();
		OptionsController.start(stage);
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
