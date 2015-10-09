/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects.ILineViewObject;
import nl.joshuaslik.tudelft.SEM.model.container.IntersectionPoint;
import nl.joshuaslik.tudelft.SEM.model.container.Point;
import nl.joshuaslik.tudelft.SEM.model.container.Vector;
import nl.joshuaslik.tudelft.SEM.utility.GameLog;

/**
 * A class which holds the information of a projectile which is shot by the palyer.
 *
 * @author faris
 */
public class Projectile extends AbstractPhysicsObject implements IUpdateable, ICollideReceiver {

    //    private final javafx.scene.shape.Line fxLine;
    private final ILineViewObject line;
    private Point p1, p2;
    private final Vector dir;

    private final double growSpeed;
    private double delay;
    private boolean isActive = true;

    /**
     * Create a projectile.
     *
     * @param gameObjects
     * @param startX      start x coordinate of the projectile.
     * @param startY      start y coordinate of the projectile.
     * @param speed
     * @param delay
     */
    public Projectile(final IGameObjects gameObjects, final double startX, final double startY, final double speed, final int delay) {
        super(gameObjects);

        growSpeed = 1000 * speed;
        this.delay = delay * 1_000_000_000.0;

        line = getGameObjects().makeLine(startX, startY - 2, startX, startY - 3);

        this.p1 = new Point(startX, startY);
        this.p2 = new Point(startX, startY + 10);

        dir = new Vector(p2.getxPos() - p1.getxPos(), p2.getyPos() - p1.getyPos());

        line.setStrokeWidth(7);
        line.setColor(0.2, 0.1, 0.1);
        line.setOpacity(0.8);

        GameLog.addInfoLog("Projectile created at: (" + Double.toString(startX) + ", " + Double.toString(startY) + ")");
    }

    /**
     * Update the length of the projectile.
     *
     * @param nanoFrameTime the framerate (nanoseconds/frame).
     */
    @Override
    public void update(final long nanoFrameTime) {

        // destroy line if it hit the ceiling
        if (line.getEndY() <= getGameObjects().getTopBorder() + 2) {
            // Wait till delay is done.
            if (delay > 0) {
                delay -= nanoFrameTime;
                return;
            }
            GameLog.addInfoLog("Projectile hit ceiling at: (" + Double.toString(line.getEndX()) + ", " + Double.toString(line.getEndY())
                               + ")");
            getGameObjects().removeProjectile();
            line.destroy();
            isActive = false;
        }

        // make line longer
        double endY = line.getEndY() - growSpeed * (nanoFrameTime / 1_000_000_000.0);
        if (endY < getGameObjects().getTopBorder() + 2) {
            line.setEndY(getGameObjects().getTopBorder() + 2);
        } else {
            line.setEndY(endY);
        }
        updateLinePoints();
    }

    /**
     * Get the closest point of point p with this line.
     *
     * @param p point to intersect with.
     * @return intersection point on the line, which is closest to point p.
     */
    @Override
    public IntersectionPoint getClosestIntersection(final Point p) {
        Vector normal = dir.normal();
        Point intersection =
                normal.getIntersectionPoint(p1.translate(-p.getxPos(), -p.getyPos()), p2.translate(-p.getxPos(), -p.getyPos()));

        if (intersection == null) {
            return new IntersectionPoint(Double.MAX_VALUE, Double.MAX_VALUE, new Vector(1, 1), Double.MAX_VALUE);
        }

        double distance = intersection.distanceTo(new Point(0, 0));
        intersection = new Point(intersection.getxPos() + p.getxPos(), intersection.getyPos() + p.getyPos());

        Point smallestXpoint = getSmallestXpoint();
        Point smallestYpoint = getSmallestYpoint();
        Point largestXpoint = getLargestXpoint();
        Point largestYpoint = getLargestYpoint();

        // assure the intersection point is on the line
        if (intersection.getxPos() < smallestXpoint.getxPos()) {
            intersection = smallestXpoint;
        } else if (intersection.getxPos() > largestXpoint.getxPos()) {
            intersection = largestXpoint;
        } else if (intersection.getyPos() < smallestYpoint.getyPos()) {
            intersection = smallestYpoint;
        } else if (intersection.getyPos() > largestYpoint.getyPos()) {
            intersection = largestYpoint;
        }

        return new IntersectionPoint(intersection.getxPos(), intersection.getyPos(), normal, distance);
    }

    /**
     * Get smallest y.
     *
     * @return smallest y.
     */
    private Point getSmallestYpoint() {
        return p1.getyPos() < p2.getyPos() ? p1 : p2;
    }

    /**
     * Get largest y.
     *
     * @return largest y.
     */
    private Point getLargestYpoint() {
        return p1.getyPos() < p2.getyPos() ? p2 : p1;
    }

    /**
     * Get smallest x.
     *
     * @return smallest x.
     */
    private Point getSmallestXpoint() {
        return p1.getxPos() < p2.getxPos() ? p1 : p2;
    }

    /**
     * Get largest x.
     *
     * @return largest x.
     */
    private Point getLargestXpoint() {
        return p1.getxPos() < p2.getxPos() ? p2 : p1;
    }

    /**
     * Recalculated p1 and p2.
     */
    public void updateLinePoints() {
        p1 = new Point(line.getStartX(), line.getStartY());
        p2 = new Point(line.getEndX(), line.getEndY());
    }

    /**
     * Called when a dynamic object collides with this projectile. If it is a bubble we will split
     * the bubble.
     *
     * @param obj2          the dynamic object which collided with this projectile.
     * @param nanoFrameTime the time which a frame takes.
     */
    @Override
    public void collide(final ICollider obj2, final long nanoFrameTime) {
        if (isActive && obj2 instanceof Bubble) {
            Bubble bubble = (Bubble) obj2;
            bubble.splitBubble();
            GameLog.addInfoLog("Projectile hit bubble at: (" + Double.toString(line.getEndX()) + ", " + Double.toString(line.getEndY())
                               + ")");
            getGameObjects().removeProjectile();
            line.destroy();
            isActive = false;
        }
    }

    public Vector getVector() {
        return dir;
    }

    public Point getPoint1() {
        return p1;
    }

    public Point getPoint2() {
        return p2;
    }

    public double getDelay() {
        return delay;
    }

    public double getGrowSpeed() {
        return growSpeed;
    }
}
