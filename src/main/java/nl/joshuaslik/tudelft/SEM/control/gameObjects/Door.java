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
	
	private final double xLeft;
	private final double xRight;
	
	/**
	 * Constructs a door of 4 points
	 *
	 * @param gameObjects
	 * @param ul
	 * @param ur
	 * @param bl
	 * @param br
	 */
	public Door(IGameObjects gameObjects, Point ul, Point ur, Point bl, Point br) {
		super(gameObjects);
		
		up = new Line(gameObjects, ul.getxPos(), ul.getyPos(), ur.getxPos(), ur.getyPos());
		left = new Line(gameObjects, ul.getxPos(), ul.getyPos(), bl.getxPos(), bl.getyPos());
		right = new Line(gameObjects, ur.getxPos(), ur.getyPos(), br.getxPos(), br.getyPos());
		down = new Line(gameObjects, bl.getxPos(), bl.getyPos(), br.getxPos(), br.getyPos());
		
		gameObjects.addObject(up);
		gameObjects.addObject(left);
		gameObjects.addObject(right);
		gameObjects.addObject(down);
		
		xLeft = ul.getxPos();
		xRight = ur.getxPos();
		gameObjects.getPlayer().setDoor(xLeft);
		gameObjects.getPlayer().setDoor(xRight);
	}
	
	/**
	 * Checks if the door is currently open
	 *
	 * @return open
	 */
	public abstract boolean isOpen();
	
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
		getGameObjects().getPlayer().removeDoor(xLeft);
		getGameObjects().getPlayer().removeDoor(xRight);
		
		up.destroy();
		left.destroy();
		right.destroy();
		down.destroy();
		
		getGameObjects().removeObject(this);
	}
}
