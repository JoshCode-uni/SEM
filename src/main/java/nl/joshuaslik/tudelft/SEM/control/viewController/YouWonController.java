package nl.joshuaslik.tudelft.SEM.control.viewController;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import nl.joshuaslik.tudelft.SEM.Launcher;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.stage.Popup;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PopupControl;
import javafx.stage.Stage;

/**
 * Controller for the You Won screen.
 *
 * @author Bastijn
 */
public class YouWonController implements IpopupController{

	@FXML
	private Pane pane;

	@FXML
	private Button mainMenuButton, nextLevelButton;

	private Scene newScene;
	
	private IviewController mainController;
	private PopupControl popupControl;

	/**
	 * Start the pop-up when player has won
	 *
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
		popupControl.hide();
		mainController.setButtonsDisiabled(false);
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
		mainController.setButtonsDisiabled(false);
		popupControl.hide();
		GameController.loadView();
	}

	public static void loadPopup(IviewController controller) {
		Launcher.loadPopup(controller, Class.class.getResource("/data/gui/pages/YouWon.fxml"));
	}

//	@Override
//	public void start(Scene scene) {
//		try {
//			FXMLLoader loader = new FXMLLoader();
//			loader.setLocation(Class.class.getResource("/data/gui/pages/YouWon.fxml"));
//			Pane page = loader.load();
//			PopupControl popup = new PopupControl();
//			popup.getScene().setRoot(page);
//			popup.show(scene.getWindow());
////		try {
////			Stage stage = new Stage();
////			FXMLLoader loader = new FXMLLoader();
////			loader.setLocation(Class.class.getResource("/data/gui/pages/YouWon.fxml"));
////			Pane page = loader.load();
////			page.setOpacity(0.85);
////			
//////			Popup popup = new Popup();
//////			popup.setAutoHide(true);
//////			popup.getContent().add(page);
////			newScene = new Scene(page);
////			stage.setScene(newScene);
////			stage.showAndWait();//popup.show(Launcher.stage);
////		} catch (IOException ex) {
////			Logger.getLogger(YouWonController.class.getName()).log(Level.SEVERE, null, ex);
////		}
//		} catch (IOException ex) {
//			Logger.getLogger(YouWonController.class.getName()).log(Level.SEVERE, null, ex);
//		}
//	}

	@Override
	public void setMainViewController(IviewController controller) {
		mainController = controller;
	}

	@Override
	public void setPopupControl(PopupControl popupControl) {
		this.popupControl = popupControl;
	}

}
