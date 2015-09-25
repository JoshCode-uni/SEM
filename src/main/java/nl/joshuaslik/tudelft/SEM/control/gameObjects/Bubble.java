package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import nl.joshuaslik.tudelft.SEM.Launcher;
import nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects.ICircleViewObject;
import nl.joshuaslik.tudelft.SEM.model.container.IntersectionPoint;
import nl.joshuaslik.tudelft.SEM.model.container.Point;
import nl.joshuaslik.tudelft.SEM.model.container.Vector;
import nl.joshuaslik.tudelft.SEM.utility.GameLog;

/**
 * This class contains the position, speed and direction of a bubble. It
 * implements Dynamic object (because it can move) and PhysicsObject (so it can
 * collide with other objects).
 *
 * @author faris
 */
public class Bubble extends AbstractPhysicsObject implements IUpdateable, IPrepareUpdateable, ICollider, ICollideReceiver {

    // variables to keep track of the direction/speed/position
    private final ICircleViewObject circle;
    private Vector dir;
    private Vector newDir;
    private static final double MAX_X_SPEED = 150;
    private static final double Y_MAX_SPEED = 900;
    private double vX = 0;
    private double vY = 0;
    private double nextX;
    private double nextY;

    /**
     * Create a bubble.
     *
     * @param gameObjects
     * @param p Center x/y coordinates
     * @param radius Radius of the bubble
     * @param dir Moving direction of the bubble
     */
    public Bubble(IGameObjects gameObjects, Point p, double radius, Vector dir) {
        super(gameObjects);

        circle = getGameObjects().makeCircle(p.getxPos(), p.getyPos(), radius);
        circle.setRadius(radius);

        this.dir = dir;
        this.newDir = dir;

        circle.setBounds(getGameObjects().getLeftBorder(),
                getGameObjects().getTopBorder(),
                getGameObjects().getRightBorder(),
                getGameObjects().getBottomBorder());

        GameLog.addInfoLog("Bubble created at: ("
                + Double.toString(circle.getCenterX()) + ", "
                + Double.toString(circle.getCenterY()) + ")");
    }

    /**
     * Calculate the closest point on the circle to point p.
     *
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
     * Update the position (called by GameLoop).
     *
     * @param nanoFrameTime the framerate (nanoseconds/frame)
     */
    @Override
    public void update(final long nanoFrameTime) {
        // check if hit ceiling, if so, destroy bubble
        if (nextY - circle.getRadius() - 10 < getGameObjects().getTopBorder())
        {
            GameLog.addInfoLog("Bubble hit ceiling: ("
                    + Double.toString(circle.getCenterX())
                    + Double.toString(circle.getCenterY()) + ")");
            GameLog.addInfoLog("Bubble is destroyed");
            getGameObjects().removeObject(getThis());
            circle.destroy();
        }
        
        // move circle
        circle.setCenterX(nextX);
        circle.setCenterY(nextY);
        
        // Ensure we won't get stuck on the ground if we tried to go through the
        // bottom border
        if(circle.getCenterY() == getGameObjects().getBottomBorder())
            vY = -Y_MAX_SPEED;
    }

    /**
     * Check if the bubble collides with obj2.
     *
     * @param obj2 a Phyisics object.
     * @param nanoFrameTime the framerate (nanoseconds/frame)
     */
    @Override
    public void checkCollision(final IIntersectable obj2, final long nanoFrameTime) {

        // get the closest object to the circle (which we might hit)
        Point currentPos = new Point(circle.getCenterX(), circle.getCenterY());
        Point nextPos = new Point(nextX, nextY);
        IntersectionPoint ip = obj2.getClosestIntersection(nextPos);

        double curDist = currentPos.distanceTo(ip);
        double newDist = nextPos.distanceTo(ip);

        // if are close enough to hit, and going toward it
        if (newDist < circle.getRadius() * 0.9) {
            if (newDist < curDist) {
                // bounce off of the object by changing the direction
                collide(ip, nanoFrameTime);
            }
            //notify the other object to collide with this object
            if (obj2 instanceof ICollideReceiver) {
                ((ICollideReceiver) obj2).collide(this, nanoFrameTime);
            }
            if (newDist < curDist) {
                calculateNextPosition(nanoFrameTime);
            }
        }
    }

    /**
     * Method to calculate the position of this bubble after a collision.
     *
     * @param ip the intersection point (collision point).
     * @param nanoFrameTime the time which a frame takes.
     */
    private void collide(final IntersectionPoint ip, final long nanoFrameTime) {
        // bounce off of the object by changing the direction

        // calculate new direction vector
        Vector normal = ip.getNormal();
        if (normal.getX() != 0) {
            double oldDirY = dir.getY();
            double dotProduct = -2 * (dir.getX() * normal.getX() + dir.getY() + normal.getY());
            newDir = new Vector(dotProduct * normal.getX() + dir.getX(), dotProduct * normal.getY() + dir.getY());

            // multiply y with -1 if we change y directions
            if (oldDirY * newDir.getY() < 0) {
                vY *= -1;
                newDir = new Vector(-newDir.getX(), newDir.getY());
            }
        } else {
            // hit ground or ceiling !!!
            newDir = new Vector(newDir.getX(), -newDir.getY());
            vY *= -1;
        }

        // recalculate new circle position (with new directions)
        vX = MAX_X_SPEED * newDir.getXdirection();// Math.sqrt(2 * Launcher.ENERGY - 2 * Launcher.GRAVITY * height) * dir.percentageXdirection();

        if (ip.hasSpeedVec()) {
            Vector speedVecOtherObj = ip.getSpeedVec();
            double xSpeedOtherObj = speedVecOtherObj.getX();
            double ySpeedOtherObj = speedVecOtherObj.getY();
            xSpeedOtherObj *= xSpeedOtherObj * vX < 0 ? -1 : 1;
            ySpeedOtherObj *= ySpeedOtherObj * vY < 0 ? -1 : 1;
            vX = (vX + xSpeedOtherObj) / 2.0;
            vY = (vY + ySpeedOtherObj) / 2.0;
        }
    }

    /**
     * Called when a dynamic object collides with this Bubble.
     *
     * @param obj2 the dynamic object which collided with this bubble.
     * @param nanoFrameTime the time which a frame takes.
     */
    @Override
    public void collide(final ICollider obj2, final long nanoFrameTime) {
        if(!(obj2 instanceof IIntersectable))
            return;
        Point thisCirclePoint = new Point(circle.getCenterX(), circle.getCenterY());
        IntersectionPoint ip = ((IIntersectable)obj2).getClosestIntersection(thisCirclePoint);

        Point currentPos = new Point(circle.getCenterX(), circle.getCenterY());
        Point nextPos = new Point(nextX, nextY);

        double curDist = currentPos.distanceTo(ip);
        double newDist = nextPos.distanceTo(ip);

        // if are going toward obj2, cause a collision
        if (newDist < curDist) {
            collide(ip, nanoFrameTime);
            calculateNextPosition(nanoFrameTime);
        }
    }

    /**
     * Prepare for an update (calculate next positions). Called by GameLoop
     *
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
     *
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
     * Split a bubble if you pushed the button.
     */
    public void splitBubble() {

        GameLog.addInfoLog("Bubble hit by projectile at: ("
                + Double.toString(circle.getCenterX())
                + Double.toString(circle.getCenterY()) + ")");

        double newRadius = circle.getRadius() / 2.0;
        if (newRadius < 10) {
            getGameObjects().removeObject(this);
            circle.destroy();
            return;
        }
        if (newRadius < 20) {
            newRadius = 10;
        }

        GameLog.addInfoLog("Bubble is split");

        double xPos = circle.getCenterX();
        double yPos = circle.getCenterY();
        Bubble bubble2 = new Bubble(getGameObjects(),
                new Point(xPos + 1.1 * newRadius, yPos),
                newRadius, new Vector(2, -5));
        circle.setCenterX(xPos - newRadius);
        circle.setRadius(newRadius);
        circle.setCenterY(yPos);

        this.newDir = new Vector(-2, -5);
        this.vX = -MAX_X_SPEED;
        this.vY = -Y_MAX_SPEED / 1.5;

        bubble2.vY = -200 + this.vY / 4;
        bubble2.vX = MAX_X_SPEED;
        bubble2.vY = this.vY;

        getGameObjects().addObject(bubble2);
    }

    /**
     * Get the center point of this bubble.
     *
     * @return center point of this bubble.
     */
    public Point getPoint() {
        return new Point(circle.getCenterX(), circle.getCenterY());
    }

    /**
     * Get the radius of this bubble.
     *
     * @return radius of the bubble.
     */
    public double getRadius() {
        return circle.getRadius();
    }

    /**
     * Get an instance of this class (for annonymous inner class).
     *
     * @return instance of this class.
     */
    private Bubble getThis() {
        return this;
    }
    
    /**
     * Get the circle view object interface of this bubble.
     * @return the circle view object interface of this bubble.
     */
    protected ICircleViewObject getCircleViewObject() {
        return circle;
    }
}
