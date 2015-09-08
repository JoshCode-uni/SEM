/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import nl.joshuaslik.tudelft.SEM.Launcher;
import nl.joshuaslik.tudelft.SEM.model.container.IntersectionPoint;
import nl.joshuaslik.tudelft.SEM.model.container.Point;
import nl.joshuaslik.tudelft.SEM.model.container.Vector;

/**
 * @author faris
 */
public class Bubble implements PhysicsObject, DynamicObject {
	
	private final Circle circle;
	private Vector dir;
	private Timeline timeline;
	private static final double MAX_Y_VELOCITY = 700;
	private static final double X_SPEED = 250;
	private double yVelocity = 0;
	
	public Bubble(Point p, double radius, Vector dir) {
		this.circle = new Circle(p.getxPos(), p.getyPos(), radius);
		this.dir = dir;
	}
	
	@Override
	public Node getNode() {
		return circle;
	}
	public Circle getCircle() {
		return circle;
	}
	
	@Override
	public void startAnimation() {
		
		// initialize timeline
		timeline = new Timeline();
		timeline.setOnFinished(onFinished);
		
		// calculate new speed in x and y directions
		double height = Launcher.SCREEN_HEIGHT - circle.getCenterY() - 30;
		double vX = X_SPEED * dir.getXdirection();// Math.sqrt(2 * Launcher.ENERGY - 2 * Launcher.GRAVITY * height) * dir.percentageXdirection();
		double vY = yVelocity;
		
		// calculate new positions of the cirecles
		double circleX = circle.getCenterX() + vX * Launcher.ANIMATE_DELAY / 1000.0;
		double circleY = circle.getCenterY() + vY * Launcher.ANIMATE_DELAY / 1000.0;
		
		// move + animate circle
		KeyValue kvX = new KeyValue(circle.centerXProperty(), circleX, Interpolator.LINEAR);
		KeyValue kvY = new KeyValue(circle.centerYProperty(), circleY, Interpolator.LINEAR);
		KeyFrame kf = new KeyFrame(Duration.millis(Launcher.ANIMATE_DELAY), kvX, kvY);
		timeline.getKeyFrames().add(kf);
		timeline.play();
	}
	
	// Event handler, will be activated when a frame is done playing
	private final EventHandler onFinished = (EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent t) {
			
			// apply gravity
			yVelocity += Launcher.GRAVITY * Launcher.ANIMATE_DELAY / 1000.0;
			
			// reinitialize timeline
			timeline = new Timeline();
			timeline.setOnFinished(onFinished);
			
			// calculate new speed in x and y directions
			double height = Launcher.SCREEN_HEIGHT - circle.getCenterY() - 30;
			double vX = X_SPEED * dir.getXdirection();// Math.sqrt(2 * Launcher.ENERGY - 2 * Launcher.GRAVITY * height) * dir.percentageXdirection();
			double vY = yVelocity;
			
			// calculate new positions of the cirecles
			double circleX = circle.getCenterX() + vX * Launcher.ANIMATE_DELAY / 1000.0;
			double circleY = circle.getCenterY() + vY * Launcher.ANIMATE_DELAY / 1000.0;
			
			// change direction vector according to current direction
			dir = new Vector(vX, vY);
			
			// get the closest object to the circle (which we might hit)
			Point currentPos = new Point(circle.getCenterX(), circle.getCenterY());
			Point nextPos = new Point(circleX, circleY);
			IntersectionPoint closest = CurrentSceneObjects.getClosestPoint(nextPos);
			
			double curDist = currentPos.distanceTo(closest);
			double newDist = nextPos.distanceTo(closest);
			
			// if are close enough to hit, and going toward it
			if (newDist < circle.getRadius() * 0.9 && newDist < curDist) {
				// bounce off of the object by changing the direction
				
				// calculate new direction vector
				Vector normal = closest.getNormal();
				if (normal.getX() != 0) {
					normal = new Vector(dir.getX(), normal.getY() * dir.getX() / normal.getX()); // translate to the same x as dir
					double deltaY = normal.getY() - dir.getY();
					dir = new Vector(-dir.getX(), -(dir.getY() + 2 * deltaY));
				} else {
					// hit ground
					dir = new Vector(dir.getX(), -dir.getY());
					yVelocity = -MAX_Y_VELOCITY;
				}
				
				// recalculate new circle position (with new directions
				vX = X_SPEED * dir.getXdirection();// Math.sqrt(2 * Launcher.ENERGY - 2 * Launcher.GRAVITY * height) * dir.percentageXdirection();
				vY = yVelocity;
				
				circleX = circle.getCenterX() + vX * Launcher.ANIMATE_DELAY / 1000.0;
				circleY = circle.getCenterY() + vY * Launcher.ANIMATE_DELAY / 1000.0;
			}
			
			// TEMPORARY CODE TO SHOW PATH OF THE BALL :

			//javafx.scene.shape.Line fxLine = new javafx.scene.shape.Line(circle.getCenterX(), circle.getCenterY(), circleX, circleY);
			//Launcher.pane.getChildren().add(fxLine);
			// END OF TEMPORARY CODE
			
			// move + animate circle
			KeyValue kvX = new KeyValue(circle.centerXProperty(), circleX, Interpolator.LINEAR);
			KeyValue kvY = new KeyValue(circle.centerYProperty(), circleY, Interpolator.LINEAR);
			KeyFrame kf = new KeyFrame(Duration.millis(Launcher.ANIMATE_DELAY), kvX, kvY);
			timeline.getKeyFrames().add(kf);
			timeline.play();
		}
	};
	
	@Override
	public IntersectionPoint getClosestIntersection(Point p) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
	/**
	 * Splits a bubble if you pushed the button.
	 * @param pane the pane in which the bubbles should spawn
	 * @return the bubbles which are spawned by the splitbubble function
	 */
	public Bubble splitBubble(Pane pane){
		double newRadius = circle.getRadius();
		double xPos = circle.getCenterX();
		double yPos = circle.getCenterY();
		Bubble bubble2 = new Bubble(new Point(xPos+newRadius/2,yPos),newRadius/2,new Vector(2,5));
		pane.getChildren().add(bubble2.getNode());
		circle.setCenterX(xPos-newRadius/2);
		circle.setRadius(newRadius/2);
		circle.setCenterY(yPos);
		this.dir = new Vector(-2,-5);
		bubble2.startAnimation();
		bubble2.yVelocity=-200+this.yVelocity/4;
		this.yVelocity=-200+this.yVelocity/4;
		
		return bubble2;
	}
}
