package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects.ILineViewObject;
import nl.joshuaslik.tudelft.SEM.model.container.IntersectionPoint;
import nl.joshuaslik.tudelft.SEM.model.container.Point;
import nl.joshuaslik.tudelft.SEM.model.container.Vector;

/**
 * Contains position of a line. Contains a collision functionality.
 *
 * @author faris
 */
public class Line extends AbstractPhysicsObject implements IIntersectable {

    private final ILineViewObject line;
    private final Point           p1, p2;
    private final Vector dir;

    /**
     * @param gameObjects
     * @param startX
     * @param startY
     * @param endX
     * @param endY
     */
    public Line(final IGameObjects gameObjects, final double startX, final double startY, final double endX, final double endY) {
        super(gameObjects);

        line = getGameObjects().makeLine(startX, startY, endX, endY);
        line.setStrokeWidth(10);

        this.p1 = new Point(startX, startY);
        this.p2 = new Point(endX, endY);

        dir = new Vector(p2.getxPos() - p1.getxPos(), p2.getyPos() - p1.getyPos());
    }

    /**
     * Get the closest point of point p with this line.
     *
     * @param p point to intersect with.
     * @return intersection point on the line, which is closest to point p.
     */
    @Override
    public final IntersectionPoint getClosestIntersection(final Point p) {
        Vector normal       = dir.normal();
        Point  intersection =
                normal.getIntersectionPoint(p1.translate(-p.getxPos(), -p.getyPos()), p2.translate(-p.getxPos(), -p.getyPos()));

        if (intersection == null) {
            return new IntersectionPoint(Double.MAX_VALUE, Double.MAX_VALUE, new Vector(1, 1), Double.MAX_VALUE);
        }

        double distance = intersection.distanceTo(new Point(0, 0));
        intersection = new Point(intersection.getxPos() + p.getxPos(), intersection.getyPos() + p.getyPos());

        Point smallestXpoint = getSmallestXpoint();
        Point smallestYpoint = getSmallestYpoint();
        Point largestXpoint  = getLargestXpoint();
        Point largestYpoint  = getLargestYpoint();

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
     * Destroys the line object
     */
    public final void destroy() {
        getGameObjects().removeObject(this);
        line.destroy();
    }

    public final Point getPoint1() {
        return p1;
    }

    public final Point getPoint2() {
        return p2;
    }

    public final Vector getVector() {
        return dir;
    }
}
