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

/**
 * A line which contains functionality to calculate an intersection point.
 * @author Faris
 */
public abstract class AbstractLine extends AbstractPhysicsObject {

    private final Vector dir;
    private Point p1;
    private Point p2;

    /**
     * Create a line.
     * @param gameObjects   gameobjects reference.
     * @param startX        the x start coordinate.
     * @param startY        the y start coordinate.
     * @param endX          the x end coordinate.
     * @param endY          the y end coordinate.
     */
    public AbstractLine(IGameObjects gameObjects, final double startX, final double startY, 
            final double endX, final double endY) {
        super(gameObjects);
        this.p1 = new Point(startX, startY);
        this.p2 = new Point(endX, endY);
        dir = new Vector(p2.getxPos() - p1.getxPos(), p2.getyPos() - p1.getyPos());
    }

    /**
     * Destroys the line object
     */
    public abstract void destroy();

    /**
     * Get the closest point of point p with this line.
     *
     * @param p point to intersect with.
     * @return intersection point on the line, which is closest to point p.
     */
    public final IntersectionPoint getClosestIntersection(final Point p) {
        Vector normal = dir.normal();
        Point intersection = normal.getIntersectionPoint(p1.translate(-p.getxPos(), -p.getyPos()), p2.translate(-p.getxPos(), -p.getyPos()));
        if (intersection == null) {
            return new IntersectionPoint(Double.MAX_VALUE, Double.MAX_VALUE, new Vector(1, 1), Double.MAX_VALUE);
        }
        double distance = intersection.distanceTo(new Point(0, 0));
        intersection = new Point(intersection.getxPos() + p.getxPos(), intersection.getyPos() + p.getyPos());
        intersection = applyBounds(intersection);
        return new IntersectionPoint(intersection.getxPos(), intersection.getyPos(), normal, distance);
    }
    
    /**
     * Make sure the point isn't outside of the bounds.
     * @param intersection point.
     * @return point with bounds applied.
     */
    private Point applyBounds(final Point intersection) {
        Point res = intersection;
        Point smallestXpoint = getSmallestXpoint();
        Point smallestYpoint = getSmallestYpoint();
        Point largestXpoint = getLargestXpoint();
        Point largestYpoint = getLargestYpoint();
        if (intersection.getxPos() < smallestXpoint.getxPos()) {
            res = smallestXpoint;
        } else if (intersection.getxPos() > largestXpoint.getxPos()) {
            res = largestXpoint;
        } else if (intersection.getyPos() < smallestYpoint.getyPos()) {
            res = smallestYpoint;
        } else if (intersection.getyPos() > largestYpoint.getyPos()) {
            res = largestYpoint;
        }
        return res;
    }

    /**
     * Get largest x.
     *
     * @return largest x.
     */
    protected Point getLargestXpoint() {
        return p1.getxPos() < p2.getxPos() ? p2 : p1;
    }

    /**
     * Get largest y.
     *
     * @return largest y.
     */
    protected Point getLargestYpoint() {
        return p1.getyPos() < p2.getyPos() ? p2 : p1;
    }

     /**
     * Get point 1.
     * @return point 1.
     */
    public final Point getPoint1() {
        return p1;
    }

    /**
     * Get point 2.
     * @return point 2.
     */
    public final Point getPoint2() {
        return p2;
    }

    /**
     * Get smallest x.
     *
     * @return smallest x.
     */
    protected Point getSmallestXpoint() {
        return p1.getxPos() < p2.getxPos() ? p1 : p2;
    }

    /**
     * Get smallest y.
     *
     * @return smallest y.
     */
    protected Point getSmallestYpoint() {
        return p1.getyPos() < p2.getyPos() ? p1 : p2;
    }

    /**
     * Get the vector.
     * @return the vector.
     */
    public final Vector getVector() {
        return dir;
    }
    
    /**
     * Update the points.
     * @param line the line.
     */
    public void updateLinePoints(ILineViewObject line) {
        p1 = new Point(line.getStartX(), line.getStartY());
        p2 = new Point(line.getEndX(), line.getEndY());
    }
}
