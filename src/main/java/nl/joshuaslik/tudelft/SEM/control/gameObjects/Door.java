package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects.ILineViewObject;
import nl.joshuaslik.tudelft.SEM.model.container.Point;

/**Makes a door which can be removed.
 *
 */
public class Door {

	Point ul;
	Point ur;
	Point bl;
	Point br;
	
	IGameObjects igo;
	
	ILineViewObject up;
	ILineViewObject left;
	ILineViewObject right;
	ILineViewObject down;
	
	
	/**
	 * Constructs a door of 4 points
	 * @param upperLeft
	 * @param upperRight
	 * @param bottomLeft
	 * @param bottomRight
	 */
	public Door (Point upperLeft, Point upperRight, Point bottomLeft, Point bottomRight) {
		ul = upperLeft;
		ur = upperRight;
		bl = bottomLeft;
		br = bottomRight;
		
		up.setStartX(ul.getxPos());
		up.setStartY(ul.getyPos());
		up.setEndX(ur.getxPos());
		up.setEndY(ur.getyPos());
		
		left.setStartX(ul.getxPos());
		left.setStartY(ul.getyPos());
		left.setEndX(bl.getxPos());
		left.setEndY(bl.getyPos());
		
		right.setStartX(ur.getxPos());
		right.setStartY(ur.getyPos());
		right.setEndX(br.getxPos());
		right.setEndY(br.getyPos());
		
		down.setStartX(bl.getxPos());
		down.setStartY(bl.getyPos());
		down.setEndX(br.getxPos());
		down.setEndY(br.getyPos());
	}
	
	
	/**
	 * This method sets a certain amount of time, after which the door will disappear
	 */
	public void setTime() {
		
	}
	
	/**
	 * This method sets a certain amount of balls which need to be destroyed to open the door
	 */
	public void setBalls() {
		
	}
}
