package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import nl.joshuaslik.tudelft.SEM.model.container.Point;

/**
 * Makes a door which can be removed.
 */
abstract public class Door extends AbstractPhysicsObject implements IUpdateable {

//    private int MAX_OPEN_SPEED;
//    private ImageViewObject texture;

    private final Line up;
    private final Line left;
    private final Line right;
    private final Line down;

    /**
     * Constructs a door of 4 points
     *
     * @param gameObjects
     * @param ul
     * @param ur
     * @param bl
     * @param br
     */
    public Door(IGameObjects gameObjects, Point ul, Point ur, Point bl, 
            Point br) {
        super(gameObjects);

        up = new Line(gameObjects, ul.getxPos(), ul.getyPos(), ur.getxPos(), ur.getyPos());
        left = new Line(gameObjects, ul.getxPos(), ul.getyPos(), bl.getxPos(), bl.getyPos());
        right = new Line(gameObjects, ur.getxPos(), ur.getyPos(), br.getxPos(), br.getyPos());
        down = new Line(gameObjects, bl.getxPos(), bl.getyPos(), br.getxPos(), br.getyPos());

        gameObjects.addObject(up);
        gameObjects.addObject(left);
        gameObjects.addObject(right);
        gameObjects.addObject(down);
    }
    
    /**
     * Checks if the door is currently open
     *
     * @return open
     */
    public abstract boolean isOpen();

//    @Override
//    public IntersectionPoint getClosestIntersection(Point p) {
//        Point pup = up.getClosestIntersection(p);
//        Point pleft = left.getClosestIntersection(p);
//        Point pright = right.getClosestIntersection(p);
//        Point pdown = down.getClosestIntersection(p);
//
//        double dup = Math.sqrt((Math.pow(p.getxPos() + pup.getxPos(), 2) - Math.pow(p.getyPos() + pup.getyPos(), 2)));
//        double dleft = Math.sqrt((Math.pow(p.getxPos() + pleft.getxPos(), 2) - Math.pow(p.getyPos() + pleft.getyPos(), 2)));
//        double dright = Math.sqrt((Math.pow(p.getxPos() + pright.getxPos(), 2) - Math.pow(p.getyPos() + pright.getyPos(), 2)));
//        double ddown = Math.sqrt((Math.pow(p.getxPos() + pdown.getxPos(), 2) - Math.pow(p.getyPos() + pdown.getyPos(), 2)));
//
//        Point closestPoint = pup;
//        double shortestDist = dup;
//
//        if (dleft > shortestDist) {
//            closestPoint = pleft;
//            shortestDist = dleft;
//        }
//        if (dright > shortestDist) {
//            closestPoint = pright;
//            shortestDist = dright;
//        }
//        if (ddown > shortestDist) {
//            closestPoint = pdown;
//            shortestDist = ddown;
//        }
//
//        IntersectionPoint inPoint = new IntersectionPoint(closestPoint.getxPos(), closestPoint.getyPos(), null, shortestDist);
//
//        return inPoint;
//    }

    @Override
    public void update(long nanoFrameTime) {
        if (isOpen()) {
            destroy();
        }
    }

    /**
     * Should destory the door
     */
    public void destroy() {
        up.destroy();
        left.destroy();
        right.destroy();
        down.destroy();
        getGameObjects().removeObject(this);
    }
}
