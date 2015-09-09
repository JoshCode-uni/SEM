package nl.joshuaslik.tudelft.SEM.control.viewController;

import nl.joshuaslik.tudelft.SEM.Launcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;

public class ChooseLevelController implements IviewController {

	@FXML
	private static Pane pane;

	@FXML
	private Button level1Button, level2Button, level3Button, level4Button, 
			level5Button, mainMenuButton;

	/**
	 * Handles clicking of the Level 1 button
	 *
	 * @param event the click of the button
	 */
	@FXML
	protected void handleLevel1Button(ActionEvent event) {
		System.out.println("Level 1 button pressed!");
		//Stage stage = (Stage) level1Button.getScene().getWindow();

	}

	/**
	 * Handles clicking of the Level 2 button
	 *
	 * @param event the click of the button
	 */
	@FXML
	protected void handleLevel2Button(ActionEvent event) {
		System.out.println("Level 2 button pressed!");
		//Stage stage = (Stage) level2Button.getScene().getWindow();

	}

	/**
	 * Handles clicking of the Level 3 button
	 *
	 * @param event the click of the button
	 */
	@FXML
	protected void handleLevel3Button(ActionEvent event) {
		System.out.println("Level 3 button pressed!");
		//Stage stage = (Stage) level3Button.getScene().getWindow();

	}

	/**
	 * Handles clicking of the Level 4 button
	 *
	 * @param event the click of the button
	 */
	@FXML
	protected void handleLevel4Button(ActionEvent event) {
		System.out.println("Level 4 button pressed!");
		//Stage stage = (Stage) level4Button.getScene().getWindow();

	}

	/**
	 * Handles clicking of the Level 5 button
	 *
	 * @param event the click of the button
	 */
	@FXML
	protected void handleLevel5Button(ActionEvent event) {
		System.out.println("Level 5 button pressed!");
		//Stage stage = (Stage) level5Button.getScene().getWindow();
	}

	/**
	 * Handles clicking of the main menu button
	 *
	 * @param event the click of the button
	 */
	@FXML
	protected void handleMainMenuButton(ActionEvent event) {
		System.out.println("Main Menu button pressed!");
		MainMenuController.loadView();
	}

	/**
	 * Handles clicking of the start button
	 *
	 * @param event the click of the button
	 */
	@FXML
	protected void handlePlayButton(ActionEvent event) {
		System.out.println("Play button pressed!");
		GameController.loadView();
	}

	/**
	 * Handles clicking of the choose level button
	 *
	 * @param event the click of the button
	 */
	@FXML
	protected void handleChooseLevelButton(ActionEvent event) {
		System.out.println("Choose Level button pressed!");
		ChooseLevelController.loadView();
	}

	/**
	 * Handles clicking of the options button
	 *
	 * @param event the click of the button
	 */
	@FXML
	protected void handleOptionsButton(ActionEvent event) {
		System.out.println("Options button pressed!");
		OptionsController.loadView();
	}

	/**
	 * Handles clicking of the quit button
	 *
	 * @param event the click of the button
	 */
	@FXML
	protected void handleQuitButton(ActionEvent event) {
		System.out.println("Quit button pressed!");
		System.exit(0);
	}

	public static void loadView() {
		Launcher.loadView(Class.class.getResource("/data/gui/pages/ChooseLevel.fxml"));
	}
	
	@Override
	public void start(Scene scene) {
		
	}
}
