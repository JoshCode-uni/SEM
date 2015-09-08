/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import nl.joshuaslik.tudelft.SEM.control.Bubble;
import nl.joshuaslik.tudelft.SEM.control.CurrentSceneObjects;
import nl.joshuaslik.tudelft.SEM.control.Line;
import nl.joshuaslik.tudelft.SEM.model.container.Point;
import nl.joshuaslik.tudelft.SEM.model.container.Vector;

/**
 * @author faris
 */
public class Launcher extends Application {
	
	public static final int SCREEN_WIDTH = 600;
	public static final int SCREEN_HEIGHT = 600;
	public static final double GRAVITY = 900;
	public static final double ENERGY = GRAVITY * SCREEN_HEIGHT; // E = .5v2 + gh
	public static final int ANIMATE_DELAY = 10; // milliseconds
	private static BorderPane bp = new BorderPane();
	
	public static Pane pane; // <<< temporary to draw lines along the circle path, should be replaced
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		/*pane = new Pane();
		Scene scene = new Scene(pane, SCREEN_WIDTH, SCREEN_HEIGHT);
		
		// create 4 lines (bounds of the scene)
		Point topLeft = new Point(30, 30);
		Point topRight = new Point(scene.getWidth() - 30, 30);
		Point bottomLeft = new Point(30, scene.getHeight() - 30);
		Point bottomRight = new Point(scene.getWidth() - 30, scene.getHeight() - 30);
		
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
		
		primaryStage.setTitle("Physics test");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		bubble.startAnimation();
		//		timeline.play();
		 */	
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/data/gui/pages/MainMenu.fxml"));
		Pane pane = loader.load();
		bp.setCenter(pane);
		Scene scene = new Scene(bp);
		primaryStage.setScene(scene);
		primaryStage.setFullScreen(true);
		primaryStage.setFullScreenExitHint("");
		primaryStage.show();
		
		}
	
	public static BorderPane getBorderPane(){
		return bp;
	}
	
	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
}
