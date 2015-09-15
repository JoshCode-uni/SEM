/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PopupControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import nl.joshuaslik.tudelft.SEM.control.viewController.IpopupController;
import nl.joshuaslik.tudelft.SEM.control.viewController.IviewController;

/**
 * The launcher of the application.
 *
 * @author faris
 */
public class Launcher extends Application {
	
	public static final int SCREEN_WIDTH = 600;
	public static final int SCREEN_HEIGHT = 600;
	public static final double GRAVITY = 700;
	public static final double ENERGY = GRAVITY * SCREEN_HEIGHT; // E = .5v2 + gh
	private static final BorderPane bp = new BorderPane();
	public static Stage stage;
	public static final GameLog gl = new GameLog();
	
	/**
	 * Start up the game.
	 */
	@Override
	public void start(Stage primaryStage) {
		
		loadView(getClass().getResource("/data/gui/pages/MainMenu.fxml"));
		
		Scene scene = new Scene(bp);
		primaryStage.setScene(scene);
		primaryStage.setFullScreen(true);
		primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
		primaryStage.show();
		stage = primaryStage;
	}
	
	/**
	 * Get the stage.
	 *
	 * @return stage the stage.
	 */
	public static Stage getStage() {
		return stage;
	}
	
	/**
	 * Load the fxml file for the screen.
	 *
	 * @param fxmlURL URL of the FXML file.
	 */
	public static IviewController loadView(URL fxmlURL) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(fxmlURL);
		try {
			Pane pane = loader.load();
			IviewController res = ((IviewController) loader.getController());
			res.start(bp.getScene());
			bp.setCenter(pane);
			return res;
		} catch (IOException ex) {
			Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, "Failed to load fxml file: " + fxmlURL.toString(), ex);
			return null;
		}
	}
	
	/**
	 * Load a popup screen.
	 *
	 * @param mainViewController the controller of the current view.
	 * @param fxmlURL            the URL of the FXML file of the popup.
	 */
	public static void loadPopup(IviewController mainViewController, URL fxmlURL) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(fxmlURL);
		mainViewController.setButtonsDisiabled(true);
		try {
			Pane pane = loader.load();
			PopupControl popup = new PopupControl();
			popup.getScene().setRoot(pane);
			popup.show(stage);
			IpopupController popupController = (IpopupController) loader.getController();
			popupController.setPopupControl(popup);
			popupController.setMainViewController(mainViewController);
		} catch (IOException ex) {
			Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, "Failed to load fxml file: " + fxmlURL.toString(), ex);
		}
	}
	
	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
}
