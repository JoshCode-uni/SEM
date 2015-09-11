package nl.joshuaslik.tudelft.SEM.control.viewController;

import nl.joshuaslik.tudelft.SEM.Launcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class YouLostController {

	
	@FXML
	private Button mainMenuButton;
	
	
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
	
	public static void loadView() {
		Launcher.loadView(Class.class.getResource("/data/gui/pages/YouLost.fxml"));
	}
}
