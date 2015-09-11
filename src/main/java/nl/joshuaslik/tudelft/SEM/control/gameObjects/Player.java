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
 * A class containing the position of the player. This class also controller the
 * player.
 * @author faris
 */
public class Player implements PhysicsObject, DynamicObject {

	private ImageView image;
	private Keyboard keyboard;
	private static final double MAX_SPEED = 200;
	
	// TODO Temporary dirty static
	private static int lives;

	/**
	 * Create a player.
	 * @param img image of the player.
	 * @param kb keyboard which controller the actions of the player.
	 */
	public Player(ImageView img, Keyboard kb) {
		image = img;
		keyboard = kb;
		
		lives = 3;
	}

	/**
	 * Prepare for updating.
	 * @param nanoFrameTime the framerate (nanoseconds/frame)
	 */
	@Override
	public void prepareUpdate(long nanoFrameTime) {
		// no preparation needed
	}

	/**
	 * Check if the player has colldided with anything. Not implemented.
	 * @param obj2 object to check collision with
	 * @param nanoFrameTime the framerate (nanoseconds/frame)
	 */
	@Override
	public void checkCollision(PhysicsObject obj2, long nanoFrameTime) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	/**
	 * Update the position of the player.
	 * @param nanoFrameTime the framerate (nanoseconds/frame)
	 */
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
			GameLoop.setProjectile(new Projectile((image.getX() + image.getLayoutBounds().getMaxX()) / 2.0, image.getY() + image.getLayoutBounds().getHeight()));
		}
	}
	
	/**
	 * Check if we collided with a bubble.
	 * @return if we collided with a bubble.
	 */
	public boolean checkBubbleCollision() {
		ArrayList<PhysicsObject> objects = GameLoop.getAllObjects();
		
		Point leftcorner = new Point(image.getX(), image.getY());
		Point rightcorner = new Point(image.getX() + image.getFitWidth(), image.getY());
		
		for (PhysicsObject o : objects) {
			if (o instanceof Bubble) {
				Bubble b = (Bubble) o;
				if(image.intersects(o.getNode().getLayoutBounds()))
					return true;
			}
		}
		return false;
	}

	/**
	 * Get the speed vector of the player. Not implemented.
	 * @return x/y vector containing the speed of the player.
	 */
	@Override
	public Vector getSpeedVector() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	/**
	 * Get the node of the image of the player.
	 * @return the node of the image of the player.
	 */
	@Override
	public Node getNode() {
		return image;
	}

	/**
	 * Not implemented.
	 * @param p point
	 * @return intersection point
	 */
	@Override
	public IntersectionPoint getClosestIntersection(Point p) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
