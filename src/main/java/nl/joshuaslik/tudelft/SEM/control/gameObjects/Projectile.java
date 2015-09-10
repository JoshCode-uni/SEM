/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import javafx.scene.paint.Color;
import nl.joshuaslik.tudelft.SEM.control.GameLoop;
import nl.joshuaslik.tudelft.SEM.model.container.Point;

/**
 *
 * @author faris
 */
public class Projectile extends Line implements IUpdateable {

	private final static double GROW_RATE = 750;

	public Projectile(double startX, double startY) {
		super(new Point(startX, startY), new Point(startX, startY - 1));
		fxLine.setStrokeWidth(7);
		fxLine.setStroke(Color.FUCHSIA);
		fxLine.setOpacity(0.3);
	}

	public void collisionCheck(DynamicObject dobj) {
		if (dobj instanceof Bubble) {
			Bubble bubble = (Bubble) dobj;

			// don't split circles which are too small
			if (bubble.getRadius() < 10) {
				return;
			}

			Point bubblePoint = bubble.getPoint();
			Point ip = getClosestIntersection(bubblePoint);
			if (ip.distanceTo(bubblePoint) < bubble.getRadius()) {
				bubble.splitBubble();
				GameLoop.removeProjectile(fxLine);
			}
		}
	}

	@Override
	public void update(long nanoFrameTime) {
		// make line longer
		double endY = fxLine.getEndY() - GROW_RATE * (nanoFrameTime / 1_000_000_000.0);
		fxLine.setEndY(endY);
		updatePoints();

		// destroy line if line hits the ceiling
		if (endY < GameLoop.getTopBorder()) {
			GameLoop.removeProjectile(getNode());
		}
	}
}
