package nl.joshuaslik.tudelft.SEM.control.viewController;

import java.io.IOException;

import nl.joshuaslik.tudelft.SEM.Launcher;
import nl.joshuaslik.tudelft.SEM.control.GameLoop;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.stage.Popup;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

/**
 * Controller for the You Won screen.
 * @author Bastijn
 */

public class YouWonController {
	
	private static Popup popup;
	private static AnchorPane page;
	
	@FXML
	private Pane pane;
	
	@FXML
	private Button mainMenuButton, nextLevelButton;
	
	/**
	 * Start the pop-up when player has won
	 * @throws IOException is thrown if the FXML file cannot be parsed.
	 */
	public static void start() throws IOException {

	}
	
	/**
	 * Handles clicking of the main menu button
	 *
	 * @param event the click of the button
	 */
	@FXML
	private void handleMainMenuButton(ActionEvent event) {
		System.out.println("Main Menu button pressed!");
		popup.hide();
		//MainMenuController.loadView();
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
		popup.hide();
		GameController.loadView();
	}
	
	public static void loadView() throws IOException {
		//Launcher.loadView(Class.class.getResource("/data/gui/pages/YouWon.fxml"));
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Class.class.getResource("/data/gui/pages/YouWon.fxml"));
		page = (AnchorPane) loader.load();
		page.setOpacity(0.85);
		popup = new Popup();
		popup.setAutoHide(true);
		popup.getContent().add(page);
		popup.show(Launcher.stage);
	}
	
}
