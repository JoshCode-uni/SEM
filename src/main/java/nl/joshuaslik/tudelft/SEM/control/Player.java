/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.nl.joshuaslik.tudelft.SEM.control;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import nl.joshuaslik.tudelft.SEM.control.DynamicObject;
import nl.joshuaslik.tudelft.SEM.control.GameLoop;
import nl.joshuaslik.tudelft.SEM.control.Keyboard;
import nl.joshuaslik.tudelft.SEM.control.PhysicsObject;
import nl.joshuaslik.tudelft.SEM.model.container.IntersectionPoint;
import nl.joshuaslik.tudelft.SEM.model.container.Point;
import nl.joshuaslik.tudelft.SEM.model.container.Vector;

/**
 *
 * @author faris
 */
public class Player extends DynamicObject {
	
	private ImageView image;
	private Keyboard keyboard;
	private static final double MAX_SPEED = 200;
	
	public Player(ImageView img, Keyboard kb) {
		image = img;
		keyboard = kb;
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
		if(keyboard.isMoveLeft() && GameLoop.getLeftBorder() < image.getLayoutBounds().getMinX()) {
			// move left
				image.setX(image.getX() + -MAX_SPEED * nanoFrameTime / 1_000_000_000);
				image.setScaleX(1);
		} else if(keyboard.isMoveRight() && GameLoop.getRightBorder() > image.getLayoutBounds().getMaxX()) {
			// move right
			image.setX(image.getX() + MAX_SPEED * nanoFrameTime / 1_000_000_000);
			image.setScaleX(-1);
		}
		if(keyboard.isShoot())
			//shoot
			;
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
