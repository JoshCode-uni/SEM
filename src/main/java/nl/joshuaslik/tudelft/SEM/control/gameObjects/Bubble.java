package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.shape.Circle;
import nl.joshuaslik.tudelft.SEM.Launcher;
import nl.joshuaslik.tudelft.SEM.control.GameLoop;
import nl.joshuaslik.tudelft.SEM.model.container.IntersectionPoint;
import nl.joshuaslik.tudelft.SEM.model.container.Point;
import nl.joshuaslik.tudelft.SEM.model.container.Vector;

/**
 * This class contains the position, speed and direction of a bubble.
 * It implements Dynamic object (because it can move) and PhysicsObject (so it
 * can collide with other objects).
 * @author faris
 */
public class Bubble implements PhysicsObject, DynamicObject {

	// variables to keep track of the direction/speed/position
	private final Circle circle;
	private Vector dir;
	private Vector newDir;
	private static final double MAX_X_SPEED = 150;
	private static final double Y_MAX_SPEED = 900;
	private double vX = 0;
	private double vY = 0;
	private double nextX;
	private double nextY;
	private int previousCollisionFrame = Integer.MIN_VALUE;
	private int frame = 0;

	/**
	 * Create a bubble.
	 * @param p Center x/y coordinates
	 * @param radius Radius of the bubble
	 * @param dir Moving direction of the bubble
	 */
	public Bubble(Point p, double radius, Vector dir) {
		this.circle = new Circle(p.getxPos(), p.getyPos(), radius);
		this.dir = dir;
		this.newDir = dir;

		SceneSizeChangeListener changeListener = new SceneSizeChangeListener();
		circle.centerXProperty().addListener(changeListener);
		circle.centerYProperty().addListener(changeListener);
	}

	/**
	 * Get the circle of this bubble (as a node).
	 * @return node of the circle.
	 */
	@Override
	public Node getNode() {
		return circle;
	}

	/**
	 * Calculate the closest point on the circle to point p.
	 * @param p the point
	 * @return the interaction point on the circle which is closest to p.
	 */
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
		return new IntersectionPoint(x, y, normal, xy.distanceTo(p));
	}

	/**
	 * Get the physics object.
	 * @return 
	 */
	private PhysicsObject getThisPhysicsObject() {
		return this;
	}

	/**
	 * Update the position (called by GameLoop).
	 * @param nanoFrameTime the framerate (nanoseconds/frame)
	 */
	@Override
	public void update(final long nanoFrameTime) {
		// move circle
		circle.setCenterX(nextX);
		circle.setCenterY(nextY);
	}

	/**
	 * Check if the bubble collides with obj2.
	 * @param obj2 a Phyisics object.
	 * @param nanoFrameTime the framerate (nanoseconds/frame)
	 */
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
	}

// WIP: should be implemented later
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
	
	/**
	 * Prepare for an update (calculate next positions). Called by GameLoop
	 * @param nanoFrameTime the framerate (nanoseconds/frame)
	 */
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

	/**
	 * Calculate the next position of the bubble.
	 * @param nanoFrameTime the framerate (nanoseconds/frame)
	 */
	private void calculateNextPosition(final long nanoFrameTime) {
		if (vY > Y_MAX_SPEED) {
			vY = Y_MAX_SPEED;
		} else if (vY < -Y_MAX_SPEED) {
			vY = -Y_MAX_SPEED;
		}
		nextX = circle.getCenterX() + vX * (nanoFrameTime / 1_000_000_000.0);
		nextY = circle.getCenterY() + vY * (nanoFrameTime / 1_000_000_000.0);
	}

	/**
	 * Get the speed of the bubble as a x/y vector.
	 * @return speed vector.
	 */
	@Override
	public Vector getSpeedVector() {
		return new Vector(vX, vY);
	}

	/**
	 * Split a bubble if you pushed the button.
	 *
	 */
	public void splitBubble() {

		double newRadius = circle.getRadius();
		if(newRadius < 15) {
			GameLoop.removeObject(this);
			return;
		}
		double xPos = circle.getCenterX();
		double yPos = circle.getCenterY();
		Bubble bubble2 = new Bubble(new Point(xPos + 1.1 * newRadius / 2, yPos), newRadius / 2, new Vector(2, -5));
		circle.setCenterX(xPos - newRadius / 2);
		circle.setRadius(newRadius / 2);
		circle.setCenterY(yPos);

		this.newDir = new Vector(-2, -5);
		this.vX = -MAX_X_SPEED;
		this.vY = -200 + this.vY / 4;

		bubble2.vY = -200 + this.vY / 4;
		bubble2.vX = MAX_X_SPEED;
		bubble2.vY = this.vY;

		GameLoop.addObject(bubble2);
	}

	/**
	 * Get the center point of this bubble.
	 * @return center point of this bubble.
	 */
	public Point getPoint() {
		return new Point(circle.getCenterX(), circle.getCenterY());
	}

	/**
	 * Get the radius of this bubble.
	 * @return radius of the bubble.
	 */
	public double getRadius() {
		return circle.getRadius();
	}

	/**
	 * Listens to changed of the x and/or y position of the circle and assures 
	 * that the circle won't go outside of the view.
	 */
	private class SceneSizeChangeListener implements ChangeListener<Number> {

		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
			double circleX = circle.getCenterX();
			double circleY = circle.getCenterY();
			double radius = circle.getRadius();

			if (circleX - radius + 10 < GameLoop.getLeftBorder()) {
				circle.setCenterX(GameLoop.getLeftBorder() + radius - 10);
			}
			if (circleX + radius - 10 > GameLoop.getRightBorder()) {
				circle.setCenterX(GameLoop.getRightBorder() - radius + 10);
			}
			if (circleY - radius + 10 < GameLoop.getTopBorder()) // hit ceiling
			{
				GameLoop.removeObject(getThis());
			}
			if (circleY + radius - 10 > GameLoop.getBottomBorder()) {
				circle.setCenterY(GameLoop.getBottomBorder() - radius + 10);
			}
		}
	}

	/**
	 * Get an instance of this class (for annonymous inner class).
	 * @return instance of this class.
	 */
	private Bubble getThis() {
		return this;
	}
}
