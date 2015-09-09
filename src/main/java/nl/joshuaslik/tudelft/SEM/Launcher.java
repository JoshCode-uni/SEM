/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

/**
 * @author faris
 */
public class Launcher extends Application {

	public static final int SCREEN_WIDTH = 600;
	public static final int SCREEN_HEIGHT = 600;
	public static final double GRAVITY = 700;
	public static final double ENERGY = GRAVITY * SCREEN_HEIGHT; // E = .5v2 + gh
	private static BorderPane bp = new BorderPane();

	public static Pane pane; // <<< temporary to draw lines along the circle path, should be replaced

	@Override
	public void start(Stage primaryStage) {

//		GameLoop gl = new GameLoop();
//		pane = new Pane();
//		Scene scene = new Scene(pane, SCREEN_WIDTH, SCREEN_HEIGHT);
//
//		// create 4 lines (bounds of the scene)
//		Point topLeft = new Point(30, 30);
//		Point topRight = new Point(scene.getWidth() - 30, 30);
//		Point bottomLeft = new Point(30, scene.getHeight() - 30);
//		Point bottomRight = new Point(scene.getWidth() - 30, scene.getHeight() - 30);
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
//		
//		GameLoop.setGameBounds(30, scene.getWidth() - 30, scene.getHeight() - 30, 30);
//		Line extraLine = new Line(topLeft, bottomRight);
//		pane.getChildren().add(extraLine.getNode());
//		gl.addObject(extraLine);
//		// create 1 bubble
//		Point bubbleCenter = new Point(200, 150);
//		double bubbleRadius = 35;
//		Vector bubbleDirection = new Vector(0, -5);
//		Bubble bubble = new Bubble(bubbleCenter, bubbleRadius, bubbleDirection);
//		bubbles.add(bubble);
//		pane.getChildren().add(bubble.getNode());
//		gl.addObject(bubble);
//
//		// create 2 bubble
//		Point bubble2Center = new Point(160, 400);
//		double bubble2Radius = 20;
//		Vector bubble2Direction = new Vector(0, -5);
//		Bubble bubble2 = new Bubble(bubble2Center, bubble2Radius, bubble2Direction);
//		pane.getChildren().add(bubble2.getNode());
//		gl.addObject(bubble2);
//
//		// create 3 bubble
//		Point bubble3Center = new Point(100, 300);
//		double bubble3Radius = 30;
//		Vector bubble3Direction = new Vector(0, -5);
//		Bubble bubble3 = new Bubble(bubble3Center, bubble3Radius, bubble3Direction);
//		pane.getChildren().add(bubble3.getNode());
//		gl.addObject(bubble3);
//		// create 3 bubble
//		Point bubble4Center = new Point(100, 260);
//		double bubble4Radius = 25;
//		Vector bubble4Direction = new Vector(0, -5);
//		Bubble bubble4 = new Bubble(bubble4Center, bubble4Radius, bubble4Direction);
//		pane.getChildren().add(bubble4.getNode());
//		gl.addObject(bubble4);
//		// create 3 bubble
//		Point bubble5Center = new Point(140, 300);
//		double bubble5Radius = 25;
//		Vector bubble5Direction = new Vector(0, -5);
//		Bubble bubble5 = new Bubble(bubble5Center, bubble5Radius, bubble5Direction);
//		pane.getChildren().add(bubble5.getNode());
//		gl.addObject(bubble5);
//		for (int i = 0; i < 1; i++) {
//			Bubble b = new Bubble(bubble5Center,  10, bubble5Direction);
//			pane.getChildren().add(b.getNode());
//			gl.addObject(b);
//		}
//		primaryStage.setTitle("Physics test");
//		primaryStage.setScene(scene);
//		primaryStage.show();
//
////		bubble.startAnimation();
////		bubble2.startAnimation();
////		bubble3.startAnimation();
////		bubble4.startAnimation();
////		bubble5.startAnimation();
//		//		timeline.play();
//
//		
//		//draw player
//		Image playerImage = new Image("main/resources/images/penguin.png", 50, 50, true, true);
//		ImageView playrImg = new ImageView(playerImage);
//		playrImg.setX(SCREEN_WIDTH / 2.0);
//		playrImg.setY(SCREEN_HEIGHT - 30 - playerImage.getHeight());
//		pane.getChildren().add(playrImg);
//		
//		//listen to player controls
//		Keyboard kb = new Keyboard(scene);
//		kb.addListeners();
//		Player player = new Player(playrImg, kb);
//		gl.addPlayer(player);
//		
//		// start timeline
//		gl.start();
//		pane.getChildren().add(playerImageView);
//		
//		Timeline timeline = new Timeline();
//		timeline.setOnFinished(null);
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/data/gui/pages/MainMenu.fxml"));
		Pane pane;
		try {
			pane = loader.load();
			bp.setCenter(pane);
		} catch (IOException ex) {
			Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
		}
		Scene scene = new Scene(bp);
		primaryStage.setScene(scene);
		primaryStage.setFullScreen(true);
		primaryStage.setFullScreenExitHint("");
		primaryStage.show();

	}

	public static BorderPane getBorderPane() {
		return bp;
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
