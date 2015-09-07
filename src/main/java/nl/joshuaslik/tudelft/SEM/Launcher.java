/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM;


import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
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
	private List<Bubble> bubbles = new ArrayList<>();
	public static Pane pane; // <<< temporary to draw lines along the circle path, should be replaced
	
	@Override
	public void start(Stage primaryStage) {
		pane = new Pane();
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
		bubbles.add(bubble);
		pane.getChildren().add(bubble.getNode());
		
		primaryStage.setTitle("Physics test");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		bubble.startAnimation();
		
		
		//Used for splitting bubbles using a button
		Button btn = new Button();
	   	btn.setText("Split the bubbles");
	   	btn.setOnAction(new EventHandler<ActionEvent>() {
	   		@Override	
	   		public void handle(ActionEvent event) {
	           	List<Bubble> newBubbles = new ArrayList<>();
	           	List<Bubble> oldBubbles = new ArrayList<>();
	           	for(int i=0;i<bubbles.size();i++){
	           		if(bubbles.get(i).getCircle().getRadius()>20){
	           			newBubbles.addAll(bubbles.get(i).splitBubble(pane));
	           			oldBubbles.add(bubbles.get(i));
	           		}
	           	}
	           	bubbles.addAll(newBubbles);
	           	bubbles.removeAll(oldBubbles);
	   		}
	   	});
	   	pane.getChildren().add(btn);
	}
	
	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
}
