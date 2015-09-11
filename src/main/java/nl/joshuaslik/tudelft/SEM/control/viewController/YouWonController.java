package nl.joshuaslik.tudelft.SEM.control.viewController;

import nl.joshuaslik.tudelft.SEM.Launcher;
import nl.joshuaslik.tudelft.SEM.control.GameLoop;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * Controller for the You Won screen.
 * @author Bastijn
 */

public class YouWonController {
	
	@FXML
	private static Pane pane;
	
	@FXML
	private Button mainMenuButton, nextLevelButton;
	
	
	/**
	 * Handles clicking of the main menu button
	 *
	 * @param event the click of the button
	 */
	@FXML
	private void handleMainMenuButton(ActionEvent event) {
		System.out.println("Main Menu button pressed!");
		MainMenuController.loadView();
	}
	
	/**
	 * Handles clicking of the next level button
	 *
	 * @param event the click of the button
	 */
	//Needs to change
	@FXML
	private void handleNextLevelButton(ActionEvent event) {
		System.out.println("Next level button pressed!");
	}
	
	public static void loadView() {
		Launcher.loadView(Class.class.getResource("/data/gui/pages/YouWon.fxml"));
	}
	
}
