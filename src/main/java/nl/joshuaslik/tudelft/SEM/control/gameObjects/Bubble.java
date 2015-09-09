/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
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
	private Vector newDir;
	private static final double MAX_X_SPEED = 150;
	private static final double Y_MAX_SPEED = 600;

	private double vX = 0;
	private double vY = 0;
	private double nextX;
	private double nextY;

	private int previousCollisionFrame = Integer.MIN_VALUE;
	private int frame = 0;

	public Bubble(Point p, double radius, Vector dir) {
		this.circle = new Circle(p.getxPos(), p.getyPos(), radius);
		this.dir = dir;
		this.newDir = dir;
	}

	@Override
	public Node getNode() {
		return circle;
	}

	@Override
	public IntersectionPoint getClosestIntersection(final Point p) {
		Point thisCircle = new Point(nextX, nextY);
		double radDdist = circle.getRadius() / p.distanceTo(thisCircle);
		double deltaX = nextX - p.getxPos();
		double deltaY = nextY - p.getyPos();
		double x = nextX - radDdist * deltaX;
		double y = nextY - radDdist * deltaY;

		Point xy = new Point(x, y);
		Vector normal = new Vector(x - nextX, y - nextY);
//		System.out.println("\ndeltaX = " + deltaX);
//		System.out.println("deltaY = " + deltaY);
//		System.out.println("radDdist = " + radDdist);
//		System.out.println("point = " + p);
//		System.out.println("IP: x, y = " + x + ", " + y);
//		System.out.println("normal = " + normal);
//		System.out.println("xy.distanceTo(p) = " + xy.distanceTo(p));
//		System.out.println("nextX = " + nextX);
//		System.out.println("nextY = " + nextY);
		return new IntersectionPoint(x, y, normal, xy.distanceTo(p));
	}

	private PhysicsObject getThisPhysicsObject() {
		return this;
	}

	@Override
	public void update(final long nanoFrameTime) {

		// TEMPORARY CODE TO SHOW PATH OF THE BALL :
//			javafx.scene.shape.Line fxLine = new javafx.scene.shape.Line(circle.getCenterX(), circle.getCenterY(), circleX, circleY);
//			fxLine.setStroke(circle.getFill());
//			Launcher.pane.getChildren().add(fxLine);
		// END OF TEMPORARY CODE
		// move circle
		circle.setCenterX(nextX);
		circle.setCenterY(nextY);
	}

	@Override
	public void checkCollision(final PhysicsObject obj2, final long nanoFrameTime) {
		// get the closest object to the circle (which we might hit)
		Point currentPos = new Point(circle.getCenterX(), circle.getCenterY());
		Point nextPos = new Point(nextX, nextY);
		IntersectionPoint closest = obj2.getClosestIntersection(nextPos);

		double curDist = currentPos.distanceTo(closest);
		double newDist = nextPos.distanceTo(closest);

		// if are close enough to hit, and going toward it
		if (newDist < circle.getRadius() * 0.9 && newDist < curDist) {
				// bounce off of the object by changing the direction

			// calculate new direction vector
			Vector normal = closest.getNormal();
			if (normal.getX() != 0) {
				newDir = dir;
				double dotProduct = -2 * (newDir.getX() * normal.getX() + newDir.getY() + normal.getY());
				newDir = new Vector(dotProduct * normal.getX() + newDir.getX(), dotProduct * normal.getY() + newDir.getY());

				// multiply y with -1 if we change y directions
				if (dir.getY() * newDir.getY() < 0) {
					vY *= -1;
					newDir = new Vector(-newDir.getX(), newDir.getY());
				}
			} else {
				// hit ground or ceiling !!!
				newDir = new Vector(newDir.getX(), -newDir.getY());
				vY *= -.99;
//					yVelocity = yVelocity > 0 ? -MAX_Y_VELOCITY : MAX_Y_VELOCITY / 2.0;
			}

			// recalculate new circle position (with new directions)
			vX = MAX_X_SPEED * newDir.getXdirection();// Math.sqrt(2 * Launcher.ENERGY - 2 * Launcher.GRAVITY * height) * dir.percentageXdirection();

			if (closest.hasSpeedVec()) {
				Vector speedVecOtherObj = closest.getSpeedVec();
				double xSpeedOtherObj = speedVecOtherObj.getX();
				double ySpeedOtherObj = speedVecOtherObj.getY();
				xSpeedOtherObj *= xSpeedOtherObj * vX < 0 ? -1 : 1;
				ySpeedOtherObj *= ySpeedOtherObj * vY < 0 ? -1 : 1;
				vX = (vX + xSpeedOtherObj) / 2.0;
				vY = (vY + ySpeedOtherObj) / 2.0;
			}
			calculateNextPosition(nanoFrameTime);
		}

//		// don't collide if you collided in one of the last 2 frames
//		if(!(previousCollisionFrame + 2 < frame || frame == previousCollisionFrame))
//			return;
//		previousCollisionFrame = frame;
//		
//		// calculate next position if direction doesn't change and we move for 1ms
////		double nextX = circle.getCenterX() + vX * 0.001;
////		double nextY = circle.getCenterY() + vY * 0.001;
//		Point currentPos = new Point(circle.getCenterX(), circle.getCenterY());
//		Point nextPos = new Point(nextX, nextY);
//		IntersectionPoint ip = obj2.getClosestIntersection(nextPos);
//
//		double curDist = currentPos.distanceTo(ip);
//		double newDist = nextPos.distanceTo(ip);
//
//		// if are close enough to hit
//		if (newDist < circle.getRadius()) {
//			collide(ip, nanoFrameTime);
//			if (obj2 instanceof DynamicObject) {
//				((DynamicObject) obj2).collide(this, nanoFrameTime);
//			}
//		}
	}
//
//	private void collide(final IntersectionPoint ip, final long nanoFrameTime) {
//		// bounce off of the object by changing the direction
//
//		// calculate new direction vector
//		Vector normal = ip.getNormal();
//		if (normal.getX() != 0) {
//			double oldDirY = dir.getY();
//			double dotProduct = -2 * (dir.getX() * normal.getX() + dir.getY() + normal.getY());
//			newDir = new Vector(dotProduct * normal.getX() + dir.getX(), dotProduct * normal.getY() + dir.getY());
//
//			// multiply y with -1 if we change y directions
//			if (oldDirY * newDir.getY() < 0) {
//				vY *= -1;
//				newDir = new Vector(-newDir.getX(), newDir.getY());
//			}
//		} else {
//			// hit ground or ceiling !!!
//			newDir = new Vector(newDir.getX(), -newDir.getY());
//			vY *= -1;
//		}
//		recalculateNextPosition(nanoFrameTime);
//	}
//
//	@Override
//	public void collide(final DynamicObject obj2, final long nanoFrameTime) {
//		Point thisCirclePoint = new Point(circle.getCenterX(), circle.getCenterY());
//		IntersectionPoint ip = obj2.getClosestIntersection(thisCirclePoint);
//		collide(ip, nanoFrameTime);
//	}

	@Override
	public void prepareUpdate(final long nanoFrameTime) {

		dir = newDir;

		// apply gravity
		vY += Launcher.GRAVITY * (nanoFrameTime / 1_000_000_000.0);
		vX = MAX_X_SPEED * dir.getXdirection();// Math.sqrt(2 * Launcher.ENERGY - 2 * Launcher.GRAVITY * height) * dir.percentageXdirection();

		// calculate new positions of the cirecles
		calculateNextPosition(nanoFrameTime);

		// change direction vector according to current direction
		dir = new Vector(vX, vY);
	}

	private void calculateNextPosition(final long nanoFrameTime) {
		nextX = circle.getCenterX() + vX * (nanoFrameTime / 1_000_000_000.0);
		nextY = circle.getCenterY() + vY * (nanoFrameTime / 1_000_000_000.0);
	}

	@Override
	public Vector getSpeedVector() {
		return new Vector(vX, vY);
	}

	/**
	 * Splits a bubble if you pushed the button.
	 *
	 * @param pane the pane in which the bubbles should spawn
	 * @return the bubbles which are spawned by the splitbubble function
	 */
	public Bubble splitBubble(Pane pane) {
		double newRadius = circle.getRadius();
		double xPos = circle.getCenterX();
		double yPos = circle.getCenterY();
		Bubble bubble2 = new Bubble(new Point(xPos + newRadius / 2, yPos), newRadius / 2, new Vector(2, 5));
		pane.getChildren().add(bubble2.getNode());
		circle.setCenterX(xPos - newRadius / 2);
		circle.setRadius(newRadius / 2);
		circle.setCenterY(yPos);
		this.dir = new Vector(-2, -5);
		bubble2.vY = -200 + this.vY / 4;
		this.vY = -200 + this.vY / 4;

		return bubble2;
	}
}
