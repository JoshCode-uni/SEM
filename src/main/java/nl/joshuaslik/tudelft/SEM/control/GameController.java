package nl.joshuaslik.tudelft.SEM.control;

import java.io.IOException;

import nl.joshuaslik.tudelft.SEM.model.container.Point;
import nl.joshuaslik.tudelft.SEM.model.container.Vector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.control.Button;

/**
 * Controller for the game UI.
 * @author Bastijn
 */
public class GameController {
	
	@FXML
	private static Pane pane;
	
	@FXML
	private static Rectangle timeRectangle;
	
	@FXML
	private static Text livesText, levelText, scoreText;
	
	@FXML
	private Button quitButton, mainMenuButton;
	
	/**
	 * Starts the Game GUI scene.
	 * @param stage the current stage
	 * @throws IOException thrown when FXML is not parsed
	 */
	public static void start(Stage stage) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Class.class.getResource("/data/gui/pages/GameGUI.fxml"));
		pane = loader.load();
		Scene scene = new Scene(pane);
		
		stage.setScene(scene);
		stage.setFullScreen(true);
		stage.setFullScreenExitHint("");
		stage.show();
		
		Point topLeft = new Point(0, 0);
		Point topRight = new Point(1920, 0);
		Point bottomLeft = new Point(0, 880);
		Point bottomRight = new Point(1920, 880);
		
		Line top = new Line(topLeft, topRight);
		pane.getChildren().add(top.getNode());
		CurrentSceneObjects.addObject(top);
		
		Line left = new Line(topLeft, bottomLeft);
		pane.getChildren().add(left.getNode());
		CurrentSceneObjects.addObject(left);
		
		Line right = new Line(topRight, bottomRight);
		pane.getChildren().add(right.getNode());
		CurrentSceneObjects.addObject(right);
		
		Line bottom = new Line(bottomLeft, bottomRight);
		pane.getChildren().add(bottom.getNode());
		CurrentSceneObjects.addObject(bottom);
		
		// create 1 bubble
		Point bubbleCenter = new Point(200, 300);
		double bubbleRadius = 50;
		Vector bubbleDirection = new Vector(-2, -5);
		Bubble bubble = new Bubble(bubbleCenter, bubbleRadius, bubbleDirection);
		pane.getChildren().add(bubble.getNode());
		
		bubble.startAnimation();
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
	 * Method to return the timeRectangle
	 * @return Rectangle timeRectangle
	 */
	//I created this method because i thought it would come in handy with the changing the length
	//Of this rectangle. I don't really know if this method is in the right place though.
	//To alter the length of this rectangle, we can use timeRectangle.setWidth(X);
	public static Rectangle getTimeRectangle() {
		return timeRectangle;
	}
}
