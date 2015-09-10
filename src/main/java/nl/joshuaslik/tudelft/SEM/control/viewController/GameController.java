package nl.joshuaslik.tudelft.SEM.control.viewController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import nl.joshuaslik.tudelft.SEM.Launcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import nl.joshuaslik.tudelft.SEM.control.GameLoop;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.Bubble;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.Keyboard;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.Player;
import nl.joshuaslik.tudelft.SEM.model.container.Levels;
import nl.joshuaslik.tudelft.SEM.model.container.Point;

/**
 * Controller for the game UI.
 *
 * @author Bastijn
 */
public class GameController implements IviewController {

	@FXML
	private Pane pane;

	@FXML
	private Rectangle timeRectangle;

	@FXML
	private Text livesText, levelText, scoreText;

	@FXML
	private Button quitButton, mainMenuButton;

	@FXML
	private Line top, right, bottom, left;
	@FXML
	private Group gameObjects;

	private GameLoop gl;

	private static int currentLevel = 0;

	/**
	 * Handles clicking of the quit button
	 *
	 * @param event the click of the button
	 */
	@FXML
	private void handleQuitButton(ActionEvent event) {
		System.out.println("Quit button pressed!");
		System.exit(0);
	}

	/**
	 * Handles clicking of the main menu button
	 *
	 * @param event the click of the button
	 */
	@FXML
	private void handleMainMenuButton(ActionEvent event) {
		System.out.println("Main Menu button pressed!");
		gl.stop();
		gl = null;
		MainMenuController.loadView();
	}

	/**
	 * Method to return the timeRectangle
	 *
	 * @return Rectangle timeRectangle
	 */
	//I created this method because i thought it would come in handy with the changing the length
	//Of this rectangle. I don't really know if this method is in the right place though.
	//To alter the length of this rectangle, we can use timeRectangle.setWidth(X);
	public Rectangle getTimeRectangle() {
		return timeRectangle;
	}

	public static void loadView() {
		Launcher.loadView(Class.class.getResource("/data/gui/pages/GameGUI.fxml"));
	}

	@Override
	public void start(Scene scene) {
		gl = new GameLoop();
		gl.setGameBounds(top.getStartY(), top.getEndX(), bottom.getStartY(), top.getStartX());
		gl.setViewController(this);

		Point topLeft = new Point(top.getStartX(), top.getStartY());
		Point topRight = new Point(top.getEndX(), top.getEndY());
		Point bottomLeft = new Point(bottom.getStartX(), bottom.getStartY());
		Point bottomRight = new Point(bottom.getEndX(), bottom.getEndY());

		// add top, left, right and bottom lines
		gl.addObject(new nl.joshuaslik.tudelft.SEM.control.gameObjects.Line(topLeft, topRight));
		gl.addObject(new nl.joshuaslik.tudelft.SEM.control.gameObjects.Line(topLeft, bottomLeft));
		gl.addObject(new nl.joshuaslik.tudelft.SEM.control.gameObjects.Line(topRight, bottomRight));
		gl.addObject(new nl.joshuaslik.tudelft.SEM.control.gameObjects.Line(bottomLeft, bottomRight));

		//draw player
		Image playerImage = null;
		try {
			playerImage = new Image(getClass().getResource("/data/gui/img/penguin.png").openStream(),
					100, 100, true, true);
		} catch (IOException ex) {
			Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, "Couldn't load player image", ex);
		}
		ImageView playrImg = new ImageView(playerImage);
		playrImg.setX((bottom.getEndX() - bottom.getStartX()) / 2.0);
		playrImg.setY(bottom.getStartY() - playerImage.getHeight());
		gameObjects.getChildren().add(playrImg);

		//listen to player controls
		Keyboard kb = new Keyboard(scene);
		kb.addListeners();
		Player player = new Player(playrImg, kb);

		gl.addPlayer(player);

		//draw the bubbles of the current level
		ArrayList<Bubble> bubbles = Levels.getLevel(currentLevel);
		for (Bubble e : bubbles) {
			gl.addObject(e);
		}

		gl.start();
	}

	public void drawNode(Node n) {
		gameObjects.getChildren().add(n);
	}

	public void removeNode(Node n) {
		gameObjects.getChildren().remove(n);
	}

	/**
	 * Called when a level is completed
	 */
	public void levelCompleted() {
		// !!!!!!!! Needs to be changed !!!!!!!!!
		System.out.println("Level completed!");
		gl.stop();
		gl = null;
		if(currentLevel + 1 < Levels.amountOfLevels())
			currentLevel++;
		MainMenuController.loadView();
	}

	public static void setLevel(int level) {
		GameController.currentLevel = level;
	}
}
