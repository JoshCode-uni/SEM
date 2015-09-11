package nl.joshuaslik.tudelft.SEM.control.viewController;

import java.io.IOException;

import nl.joshuaslik.tudelft.SEM.Launcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Popup;

public class YouLostController {

	private static Popup popup;
	private static AnchorPane page;
	
	@FXML
	private Button mainMenuButton;
	
	/**
	 * Start the pop-up when player lost
	 * @throws IOException is thrown if the FXML file cannot be parsed.
	 */
	public static void start() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Class.class
				.getResource("/data/gui/pages/YouLost.fxml"));
		page = (AnchorPane) loader.load();
		page.setOpacity(0.85);
		popup = new Popup();
		popup.setAutoHide(true);
		popup.getContent().add(page);
		popup.show(Launcher.stage);
	}
	
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
