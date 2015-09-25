package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects.ILineViewObject;
import nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects.ImageViewObject;
import nl.joshuaslik.tudelft.SEM.model.container.IntersectionPoint;
import nl.joshuaslik.tudelft.SEM.model.container.Point;

/**Makes a door which can be removed.
 *
 */
abstract public class Door implements IDynamicObject{

	int MAX_OPEN_SPEED;
	ImageViewObject texture;
	boolean open;
	
	Point ul;
	Point ur;
	Point bl;
	Point br;
	
	IGameObjects igo;
	
	Line up;
	Line left;
	Line right;
	Line down;
	
	
	/**
	 * Constructs a door of 4 points
	 * @param upperLeft
	 * @param upperRight
	 * @param bottomLeft
	 * @param bottomRight
	 */
	public Door (IGameObjects game, Point upperLeft, Point upperRight, Point bottomLeft, Point bottomRight) {
		ul = upperLeft;
		ur = upperRight;
		bl = bottomLeft;
		br = bottomRight;
		igo = game;
		
		up = new Line(igo, ul.getxPos(), ul.getyPos(), ur.getxPos(), ur.getyPos());
		left = new Line(igo, ul.getxPos(), ul.getyPos(), bl.getxPos(), bl.getyPos());
		right = new Line(igo, ur.getxPos(), ur.getyPos(), br.getxPos(), br.getyPos());
		down = new Line(igo, bl.getxPos(), bl.getyPos(), br.getxPos(), br.getyPos());
		
		open = false;
	}

	@Override
	public IntersectionPoint getClosestIntersection(Point p) {
		Point pup = up.getClosestIntersection(p);
		Point pleft = left.getClosestIntersection(p);
		Point pright = right.getClosestIntersection(p);
		Point pdown = down.getClosestIntersection(p);
		
		double dup = Math.sqrt(( Math.pow(p.getxPos()+pup.getxPos(), 2) - Math.pow(p.getyPos()+pup.getyPos(), 2) ));
		double dleft = Math.sqrt(( Math.pow(p.getxPos()+pleft.getxPos(), 2) - Math.pow(p.getyPos()+pleft.getyPos(), 2) ));
		double dright = Math.sqrt(( Math.pow(p.getxPos()+pright.getxPos(), 2) - Math.pow(p.getyPos()+pright.getyPos(), 2) ));
		double ddown = Math.sqrt(( Math.pow(p.getxPos()+pdown.getxPos(), 2) - Math.pow(p.getyPos()+pdown.getyPos(), 2) ));

		Point closestPoint = pup;
		double shortestDist = dup; 
		
		if (dleft > shortestDist ) {
			closestPoint = pleft;
			shortestDist = dleft;
		}
		if (dright > shortestDist ) {
			closestPoint = pright;
			shortestDist = dright;
		}		
		if (ddown > shortestDist ) {
			closestPoint = pdown;
			shortestDist = ddown;
		}
		
		IntersectionPoint inPoint = new IntersectionPoint(closestPoint.getxPos(), closestPoint.getyPos(), null, shortestDist);
		
		return inPoint;
	}

	@Override
	public void setIGameObjects(IGameObjects gameObjects) {
		igo = gameObjects;	
	}

	@Override
	public void update(long nanoFrameTime) {
		if(open == true){
			destroy();
		}
	}

	@Override
	public void prepareUpdate(long nanoFrameTime) {
		// TODO Auto-generated method stub
		
	}

	//Not needed, doors don't collide into other stuff
	@Override
	public void checkCollision(PhysicsObject obj2, long nanoFrameTime) {
		// TODO Auto-generated method stub
		
	}

	//Not needed, doors don't collide into other stuff
	@Override
	public void collide(IDynamicObject obj2, long nanoFrameTime) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Should destory the door
	 */
	public void destroy() {
		
	}
	
	/**
	 * Checks if the door is currently open
	 * @return open
	 */
	public boolean isOpen() {
		return open;
	}
}
