/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import nl.joshuaslik.tudelft.SEM.control.GameLoop;
import nl.joshuaslik.tudelft.SEM.model.container.IntersectionPoint;
import nl.joshuaslik.tudelft.SEM.model.container.Point;
import nl.joshuaslik.tudelft.SEM.model.container.Vector;

/**
 *
 * @author faris
 */
public class Player implements PhysicsObject, DynamicObject {

	private ImageView image;
	private Keyboard keyboard;
	private static final double MAX_SPEED = 200;
	
	private int lives;

	public Player(ImageView img, Keyboard kb) {
		image = img;
		keyboard = kb;
		
		lives = 3;
	}

	@Override
	public void prepareUpdate(long nanoFrameTime) {
		// no preparation needed
	}

	@Override
	public void checkCollision(PhysicsObject obj2, long nanoFrameTime) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void update(long nanoFrameTime) {
		if (keyboard.isMoveLeft() && GameLoop.getLeftBorder() < image.getLayoutBounds().getMinX()) {
			// move left
			image.setX(image.getX() + -MAX_SPEED * nanoFrameTime / 1_000_000_000);
			image.setScaleX(1);
		} else if (keyboard.isMoveRight() && GameLoop.getRightBorder() > image.getLayoutBounds().getMaxX()) {
			// move right
			image.setX(image.getX() + MAX_SPEED * nanoFrameTime / 1_000_000_000);
			image.setScaleX(-1);
		}
		
		if (checkBubbleCollision()) {
			if (lives > 0) {
				System.out.println("Player loses life");
				lives--;
				// Reset level
			}
			else {
				System.out.println("Player dies");
				GameLoop.getGameController().died();
			}
		}
		
		if (keyboard.isShoot() && !GameLoop.hasProjectile()) {
			//shoot
			GameLoop.setProjectile(new Projectile((image.getX() + image.getLayoutBounds().getMaxX()) / 2.0, image.getY()));
		}
	}
	
	public boolean checkBubbleCollision() {
		ArrayList<PhysicsObject> objects = GameLoop.getAllObjects();
		
		Point leftcorner = new Point(image.getX(), image.getY());
		Point rightcorner = new Point(image.getX() + image.getFitWidth(), image.getY());
		
		for (PhysicsObject o : objects) {
			if (o instanceof Bubble) {
				Bubble b = (Bubble) o;
				
				IntersectionPoint closest = b.getClosestIntersection(leftcorner);
				if (leftcorner.distanceTo(closest) <= b.getRadius()) return true;
				
				closest = b.getClosestIntersection(rightcorner);
				if (rightcorner.distanceTo(closest) <= b.getRadius()) return true;
			}
		}
		return false;
	}

	@Override
	public Vector getSpeedVector() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Node getNode() {
		return image;
	}

	@Override
	public IntersectionPoint getClosestIntersection(Point p) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
