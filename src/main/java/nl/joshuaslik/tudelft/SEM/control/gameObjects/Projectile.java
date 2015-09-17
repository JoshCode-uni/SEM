/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import javafx.scene.paint.Color;
import nl.joshuaslik.tudelft.SEM.model.container.IntersectionPoint;
import nl.joshuaslik.tudelft.SEM.model.container.Point;
import nl.joshuaslik.tudelft.SEM.model.container.Vector;

/**
 * A class which holds the information of a projectile which is shot by the
 * palyer.
 *
 * @author faris
 */
public class Projectile extends AbstractDynamicObject {

    private final javafx.scene.shape.Line fxLine;
    private Point p1, p2;
    private final Vector dir;

    private final static double GROW_RATE = 750;
    private boolean isActive = true;

    /**
     * Create a projectile.
     *
     * @param startX start x coordinate of the projectile.
     * @param startY start y coordinate of the projectile.
     */
    public Projectile(double startX, double startY) {
        super(new javafx.scene.shape.Line());

        fxLine = (javafx.scene.shape.Line) getNode();
        fxLine.setStartX(startX);
        fxLine.setStartY(startY - 1);
        fxLine.setEndX(startX);
        fxLine.setEndY(startY);

        this.p1 = new Point(startX, startY);
        this.p2 = new Point(startX, startY - 1);

        dir = new Vector(p2.getxPos() - p1.getxPos(), p2.getyPos() - p1.getyPos());

        fxLine.setStrokeWidth(7);
        fxLine.setStroke(Color.FUCHSIA);
        fxLine.setOpacity(0.3);

    }

    /**
     * Check if we collide with object dobj.
     *
     * @param dobj a dynamic object.
     * @param nanoFrameTime
     */
    @Override
    public void checkCollision(PhysicsObject dobj, long nanoFrameTime) {
        // we won't collide with anything, other things only collide with us.
    }

    /**
     * Update the length of the projectile.
     *
     * @param nanoFrameTime the framerate (nanoseconds/frame).
     */
    @Override
    public void update(long nanoFrameTime) {

        // make line longer
        double endY = fxLine.getEndY() - GROW_RATE * (nanoFrameTime / 1_000_000_000.0);
        fxLine.setEndY(endY);
        updateLinePoints();
        
        // destroy line if it hit the ceiling
        if (fxLine.getEndY() < getGameObjects().getTopBorder()) {
            getGameObjects().removeProjectile();
            isActive = false;
        }
    }

    /**
     * Prepare for an update.
     * @param nanoFrameTime the time which a frame takes.
     */
    @Override
    public void prepareUpdate(long nanoFrameTime) {
        // no preparation needed
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
        Point intersection = normal.getIntersectionPoint(p1.translate(-p.getxPos(), -p.getyPos()), p2.translate(-p.getxPos(), -p.getyPos()));

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
        p1 = new Point(fxLine.getStartX(), fxLine.getStartY());
        p2 = new Point(fxLine.getEndX(), fxLine.getEndY());
    }

    /**
     * Called when a dynamic object collides with this projectile. If it is a
     * bubble we will split the bubble.
     * @param obj2 the dynamic object which collided with this projectile.
     * @param nanoFrameTime the time which a frame takes.
     */
    @Override
    public void collide(IDynamicObject obj2, long nanoFrameTime) {
        if (isActive && obj2 instanceof Bubble) {
            Bubble bubble = (Bubble) obj2;
            bubble.splitBubble();
            getGameObjects().removeProjectile();
            isActive = false;
        }
    }
}
