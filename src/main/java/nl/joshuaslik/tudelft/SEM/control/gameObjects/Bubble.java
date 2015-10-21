package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import java.util.ArrayList;
import nl.joshuaslik.tudelft.SEM.Launcher;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.bubble.AbstractBubbleDecorator;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.bubble.BubbleBaseModifier;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.bubble.IBubbleModifier;
import nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects.ICircleViewObject;
import nl.joshuaslik.tudelft.SEM.model.container.IntersectionPoint;
import nl.joshuaslik.tudelft.SEM.model.container.Point;
import nl.joshuaslik.tudelft.SEM.model.container.Vector;
import nl.joshuaslik.tudelft.SEM.utility.GameLog;
import nl.joshuaslik.tudelft.SEM.utility.IObservable;
import nl.joshuaslik.tudelft.SEM.utility.IObserver;
import nl.joshuaslik.tudelft.SEM.utility.Time;
import org.apache.commons.lang3.ClassUtils;

/**
 * This class contains the position, speed and direction of a bubble. It implements Dynamic object
 * (because it can move) and PhysicsObject (so it can collide with other objects).
 *
 * @author faris
 */
public class Bubble extends AbstractPhysicsObject implements IUpdateable, IPrepareable, ICollider, ICollideReceiver, IObservable<Bubble, Bubble.EventType> {

    private final ICircleViewObject circle;
    private Vector dir;
    private Vector newDir;
    private static final double MAX_X_SPEED = 150;
    private static final double Y_MAX_SPEED = 900;
    private double vX = 0;
    private double vY = 0;
    private double nextX;
    private double nextY;
    private final double leftBorder;
    private final double rightBorder;
    private final double topBorder;
    private final double bottomBorder;
    private IBubbleModifier modifier = new BubbleBaseModifier();
    private final ArrayList<IObserver<Bubble, EventType>> observers = new ArrayList<>();

    /**
     * Create a bubble.
     *
     * @param gameObjects
     * @param p Center x/y coordinates
     * @param radius Radius of the bubble
     * @param dir Moving direction of the bubble
     */
    public Bubble(final IGameObjects gameObjects, final Point p, final double radius, final Vector dir) {
        super(gameObjects);
        circle = getGameObjects().makeCircle(p.getxPos(), p.getyPos(), radius);
        circle.setRadius(radius);
        this.dir = dir;
        this.newDir = dir;
        this.topBorder = getGameObjects().getTopBorder();
        this.bottomBorder = getGameObjects().getBottomBorder();
        this.leftBorder = getGameObjects().getLeftBorder();
        this.rightBorder = getGameObjects().getRightBorder();
        circle.setBounds(leftBorder, topBorder,rightBorder, bottomBorder);
        GameLog.addInfoLog("Bubble created at: (" + Double.toString(circle.getCenterX()) + ", "
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
        if (nextY - circle.getRadius() - 10 < topBorder) {
            GameLog.addInfoLog("Bubble hit ceiling: (" + Double.toString(circle.getCenterX()) + Double.toString(circle.getCenterY()) + ")");
            GameLog.addInfoLog("Bubble is destroyed");
            getGameObjects().removeObject(getThis());
            circle.destroy();
            notifyObservers(EventType.KILLED_CEILING);
        }
        circle.setCenterX(nextX);
        circle.setCenterY(nextY);
        if (circle.getCenterY() == bottomBorder) {
            vY = -Y_MAX_SPEED;
        }
    }

    /**
     * Check if the bubble collides with obj2.
     *
     * @param obj2 a Phyisics object.
     * @param nanoFrameTime the framerate (nanoseconds/frame)
     */
    @Override
    public final void checkCollision(final IIntersectable obj2, final long nanoFrameTime) {
        Point currentPos = new Point(circle.getCenterX(), circle.getCenterY());
        Point nextPos = new Point(nextX, nextY);
        IntersectionPoint ip = obj2.getClosestIntersection(nextPos);
        double curDist = currentPos.distanceTo(ip);
        double newDist = nextPos.distanceTo(ip);
        if (newDist < circle.getRadius() * 0.9) {
            if (newDist < curDist) {
                collide(ip, nanoFrameTime);
            }
            if (ClassUtils.getAllInterfaces(obj2.getClass()).contains(ICollideReceiver.class)) {
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
        Vector normal = ip.getNormal();
        if (normal.getX() != 0) {
            double oldDirY = dir.getY();
            double dotProduct = -2 * (dir.getX() * normal.getX() + dir.getY() + normal.getY());
            newDir = new Vector(dotProduct * normal.getX() + dir.getX(), dotProduct * normal.getY() + dir.getY());
            if (oldDirY * newDir.getY() < 0) {
                vY *= -1;
                newDir = new Vector(-newDir.getX(), newDir.getY());
            }
        } else {
            newDir = new Vector(newDir.getX(), -newDir.getY());
            vY *= -1;
        }
        vX = MAX_X_SPEED * newDir.getXdirection();
        collideUpdateDirection(ip);
    }

    /**
     * Update the direction after a collision.
     * @param ip the intersection point.
     */
    private void collideUpdateDirection(IntersectionPoint ip) {
        if (ip.hasSpeedVec()) {
            Vector speedVecOtherObj = ip.getSpeedVec();
            double xSpeedOtherObj = speedVecOtherObj.getX();
            double ySpeedOtherObj = speedVecOtherObj.getY();
            if (xSpeedOtherObj * vX < 0) {
                xSpeedOtherObj *= -1;
            }
            if (ySpeedOtherObj * vX < 0) {
                ySpeedOtherObj *= -1;
            }
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
        Point thisCirclePoint = new Point(circle.getCenterX(), circle.getCenterY());
        IntersectionPoint ip = ((IIntersectable) obj2).getClosestIntersection(thisCirclePoint);
        Point currentPos = new Point(circle.getCenterX(), circle.getCenterY());
        Point nextPos = new Point(nextX, nextY);
        double curDist = currentPos.distanceTo(ip);
        double newDist = nextPos.distanceTo(ip);
        if (newDist < curDist) {
            collide(ip, nanoFrameTime);
            calculateNextPosition(nanoFrameTime);
            notifyObservers(EventType.COLLIDE);
        }
    }

    /**
     * Prepare for an update (calculate next positions). Called by GameLoop
     *
     * @param nanoFrameTime the framerate (nanoseconds/frame)
     */
    @Override
    public void prepare(final long nanoFrameTime) {
        long newNanoFrameTime = (long) (nanoFrameTime * getSpeedModifier());
        dir = newDir;
        vY += Launcher.GRAVITY * (newNanoFrameTime / Time.SECOND_NANO);
        vX = MAX_X_SPEED * dir.getXdirection();
        calculateNextPosition(newNanoFrameTime);
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
        nextX = circle.getCenterX() + vX * (nanoFrameTime / Time.SECOND_NANO);
        nextY = circle.getCenterY() + vY * (nanoFrameTime / Time.SECOND_NANO);
    }

    /**
     * Split a bubble if you pushed the button.
     * @param nanoFrameTime
     */
    public void splitBubble(final long nanoFrameTime) {
        GameLog.addInfoLog("Bubble hit by projectile at: (" + Double.toString(circle.getCenterX()) + Double.toString(circle.getCenterY()) + ")");
        getGameObjects().handleBubbleSplit(getPoint());
        double newRadius = circle.getRadius() / 2.0;
        if (newRadius < 10) {
            getGameObjects().removeObject(this);
            circle.destroy();
            notifyObservers(EventType.KILLED_PROJECTILE);
            return;
        }
        if (newRadius < 20) {
            newRadius = 10;
        }
        double xPos = circle.getCenterX();
        double yPos = circle.getCenterY();
        splitted(newRadius, new Point(xPos, yPos));
        createSplitBubble(new Point(xPos + 1.1 * newRadius, yPos), newRadius, new Vector(2, -5),
                nanoFrameTime);
        calculateNextPosition(nanoFrameTime);
        notifyObservers(EventType.SPLIT);
    }

    /**
     * Adjust some variables so bubble is split correctly
     *
     * @param newRadius the new radius.
     * @param oldcenter the old center point.
     */
    private void splitted(double newRadius, Point oldcenter) {
        circle.setCenterX(oldcenter.getxPos() - newRadius);
        circle.setRadius(newRadius);
        circle.setCenterY(oldcenter.getyPos());
        this.newDir = new Vector(-2, -5);
        this.vX = -MAX_X_SPEED;
        this.vY = -Y_MAX_SPEED / 1.5;
        this.modifier = new BubbleBaseModifier();
    }

    /**
     * Create a new bubble after splitting.
     *
     * @param center the center of the bubble.
     * @param radius the radius of the bubble.
     * @param vector the vector of the direction of the bubble.
     */
    private void createSplitBubble(final Point center, final double radius, final Vector vector,
            final long nanoFrameTime) {
        Bubble bubble2 = new Bubble(getGameObjects(), center, radius, vector);
        bubble2.vY = -200 + this.vY / 4;
        bubble2.vX = MAX_X_SPEED;
        bubble2.vY = this.vY;
        getGameObjects().addObject(bubble2);
        bubble2.prepare(nanoFrameTime);
        bubble2.update(nanoFrameTime);
    }

    /**
     * Get the center point of this bubble.
     *
     * @return center point of this bubble.
     */
    private Point getPoint() {
        return new Point(circle.getCenterX(), circle.getCenterY());
    }

    /**
     * Get the radius of this bubble.
     *
     * @return radius of the bubble.
     */
    public final double getRadius() {
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
     *
     * @return the circle view object interface of this bubble.
     */
    protected ICircleViewObject getCircleViewObject() {
        return circle;
    }

    /**
     * Add a modifier.
     * @param newmod the modifier to add.
     */
    public void addModifier(final AbstractBubbleDecorator newmod) {
        modifier = newmod.decorate(modifier);
    }

    /**
     * Get the speed modifier.
     * @return the speed modifier.
     */
    private double getSpeedModifier() {
        return modifier.getBubbleSpeedMultiplier();
    }

    /**
     * FOR TESTING PURPOSES ONLY.
     * @return the direction vector.
     */
    public final Vector getDir() {
        return dir.clone();
    }

    /**
     * FOR TESTING PURPOSES ONLY.
     * @return the new direction vector.
     */
    public final Vector getNewDir() {
        return newDir.clone();
    }

    /**
     * FOR TESTING PURPOSES ONLY.
     * @return the x velocity.
     */
    public final double getXvelocity() {
        return vX;
    }

    /**
     * FOR TESTING PURPOSES ONLY.
     * @return the y velocity.
     */
    public final double getYvelocity() {
        return vY;
    }

    /**
     * FOR TESTING PURPOSES ONLY.
     * @param x the next x.
     */
    public final void setNextX(final double x) {
        nextX = x;
    }

    /**
     * FOR TESTING PURPOSES ONLY.
     * @param y the next y.
     */
    public final void setNextY(final double y) {
        nextY = y;
    }

    /**
     * FOR TESTING PURPOSES ONLY.
     * @return the next x.
     */
    public final double getNextX() {
        return nextX;
    }

    /**
     * FOR TESTING PURPOSES ONLY.
     * @return the next y.
     */
    public final double getNextY() {
        return nextY;
    }
    
    /**
     * Get the relative x position of the bubble compared to the view.
     * @return the relative x position of the bubble compared to the view.
     */
    public double getRelativeXPos() {
        double xPos = getPoint().getxPos();
        return xPos / (rightBorder - leftBorder);
    }

    /**
     * Add an observer to this observable object.
     * @param o an observer.
     */
    @Override
    public void addObserver(IObserver o) {
        if (o.sameClass(Bubble.class)) {
            observers.add(o);
        }
    }

    /**
     * Delete an observer from this observable object.
     * @param o an observer.
     */
    @Override
    public void deleteObserver(IObserver o) {
        if (o.sameClass(Bubble.class)) {
            observers.remove(o);
        }
    }

    /**
     * Notify the observers of an event of this observable object.
     * @param event an event.
     */
    @Override
    public void notifyObservers(EventType event) {
        for (IObserver o : observers) {
            o.update(this, event);
        }
    }
    
    /**
     * An enum containing all of the events which can be triggered by a bubble.
     */
    public static enum EventType {
        SPLIT, COLLIDE, KILLED_PROJECTILE, KILLED_CEILING;
    }
}
