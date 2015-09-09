package nl.joshuaslik.tudelft.SEM.control.viewController;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import nl.joshuaslik.tudelft.SEM.Launcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
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
import nl.joshuaslik.tudelft.SEM.model.container.Point;
import nl.joshuaslik.tudelft.SEM.model.container.Vector;

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

	/**
	 * Initialize the Game GUI scene and GameLoop. Automatically called when
	 * loading the fxml file.
	 */
	@FXML
	private void initialize() {
//		GameLoop gl = new GameLoop();

//		Point topLeft = new Point(0, 0);
//		Point topRight = new Point(1920, 0);
//		Point bottomLeft = new Point(0, 880);
//		Point bottomRight = new Point(1920, 880);
//
//		Line top = new Line(topLeft, topRight);
//		pane.getChildren().add(top.getNode());
//		gl.addObject(top);
//
//		Line left = new Line(topLeft, bottomLeft);
//		pane.getChildren().add(left.getNode());
//		gl.addObject(left);
//
//		Line right = new Line(topRight, bottomRight);
//		pane.getChildren().add(right.getNode());
//		gl.addObject(right);
//
//		Line bottom = new Line(bottomLeft, bottomRight);
//		pane.getChildren().add(bottom.getNode());
//		gl.addObject(bottom);
		// create 1 bubble
//		Point bubbleCenter = new Point(200, 300);
//		double bubbleRadius = 50;
//		Vector bubbleDirection = new Vector(-2, -5);
//		Bubble bubble = new Bubble(bubbleCenter, bubbleRadius, bubbleDirection);
//		pane.getChildren().add(bubble.getNode());
//		bubbles.add(bubble);
//		//Used for splitting bubbles using a button
//		Button btn = new Button();
//		btn.setText("Split the bubbles");
//		btn.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent event) {
//				List<Bubble> newBubbles = new ArrayList<>();
//				for (int i = 0; i < bubbles.size(); i++) {
//					if (((Circle) bubbles.get(i).getNode()).getRadius() > 12) {
//						newBubbles.add(bubbles.get(i).splitBubble(pane));
//					}
//				}
//				bubbles.addAll(newBubbles);
//			}
//		});
//		pane.getChildren().add(btn);
//		gl.start();
	}

	/**
	 * Handles clicking of the quit button
	 *
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
	 *
	 * @param event the click of the button
	 * @throws IOException thrown when FXML file could not be parsed
	 */
	@FXML
	protected void handleMainMenuButton(ActionEvent event) throws IOException {
		System.out.println("Main Menu button pressed!");
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
		GameLoop gl = new GameLoop();
		gl.setGameBounds(top.getStartY(), top.getEndX(), bottom.getStartY(), top.getStartX());

		Point topLeft = new Point(top.getStartX(), top.getStartY());
		Point topRight = new Point(top.getEndX(), top.getEndY());
		Point bottomLeft = new Point(bottom.getStartX(), bottom.getStartY());
		Point bottomRight = new Point(bottom.getEndX(), bottom.getEndY());

		// add top, left, right and bottom lines
		gl.addObject(new nl.joshuaslik.tudelft.SEM.control.gameObjects.Line(topLeft, topRight));
		gl.addObject(new nl.joshuaslik.tudelft.SEM.control.gameObjects.Line(topLeft, bottomLeft));
		gl.addObject(new nl.joshuaslik.tudelft.SEM.control.gameObjects.Line(topRight, bottomRight));
		gl.addObject(new nl.joshuaslik.tudelft.SEM.control.gameObjects.Line(bottomLeft, bottomRight));

		// create 1 bubble
		Point bubbleCenter = new Point(200, 300);
		double bubbleRadius = 50;
		Vector bubbleDirection = new Vector(-2, -5);
		Bubble bubble = new Bubble(bubbleCenter, bubbleRadius, bubbleDirection);
		gameObjects.getChildren().add(bubble.getNode());

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

		gl.addObject(bubble);
		gl.addPlayer(player);
		gl.start();
	}
}
