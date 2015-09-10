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
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import nl.joshuaslik.tudelft.SEM.control.viewController.IviewController;

/**
 * @author faris
 */
public class Launcher extends Application {

	public static final int SCREEN_WIDTH = 600;
	public static final int SCREEN_HEIGHT = 600;
	public static final double GRAVITY = 700;
	public static final double ENERGY = GRAVITY * SCREEN_HEIGHT; // E = .5v2 + gh
	private static final BorderPane bp = new BorderPane();

	@Override
	public void start(Stage primaryStage) {
		
		loadView(getClass().getResource("/data/gui/pages/MainMenu.fxml"));
		
		Scene scene = new Scene(bp);
		primaryStage.setScene(scene);
		primaryStage.setFullScreen(true);
		primaryStage.setFullScreenExitHint("");
		primaryStage.show();

	}
	
	public static void loadView(URL fxmlURL) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(fxmlURL);
		try {
			Pane pane = loader.load();
			((IviewController)loader.getController()).start(bp.getScene());
			bp.setCenter(pane);
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
