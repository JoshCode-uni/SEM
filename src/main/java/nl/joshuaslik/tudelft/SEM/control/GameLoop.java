/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control;

import nl.joshuaslik.tudelft.SEM.control.gameObjects.DynamicObject;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.PhysicsObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.Bubble;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.IUpdateable;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.Projectile;
import nl.joshuaslik.tudelft.SEM.control.viewController.GameController;
import nl.joshuaslik.tudelft.SEM.model.container.IntersectionPoint;
import nl.joshuaslik.tudelft.SEM.model.container.Point;

/**
 * @author faris
 */
public class GameLoop extends AnimationTimer {

	private static ArrayList<PhysicsObject> allObjects = new ArrayList<>();
	private ArrayList<PhysicsObject> staticObjects = new ArrayList<>();
	private static ArrayList<DynamicObject> dynamicObjects = new ArrayList<>();

	// list of updateable instances which aren't dynamic objects
	private ArrayList<IUpdateable> updateable = new ArrayList<>();

	private DynamicObject player;
	private static GameController gameController;
	private static Projectile projectile = null;
	private static double topBorder, rightBorder, bottomBorder, leftBorder;

	private static int bubbleCount = 0;

	private static long time = 0;

	@Override
	public void stop() {
		super.stop();
		gameController = null;
		projectile = null;
		topBorder = 0;
		rightBorder = 0;
		bottomBorder = 0;
		leftBorder = 0;
		time = 0;
		allObjects.clear();
		dynamicObjects.clear();
		bubbleCount = 0;
	}

	@Override
	public void handle(long time) {

		if (bubbleCount <= 0) {
			gameController.levelCompleted();
		}
		try {
			// update time
			long frametime = this.time != 0 ? time - this.time : 165_000_000;
			this.time = time;

			gameController.updateTime(frametime);

//		System.out.println("framerate:  = " + 1.0 / frametime * 1_000_000_000 + "fps");
			// check if there are any collisions (for dynamic objects)
			// if so, update the direction
			// update the positions of al dynamic objects
//		for (DynamicObject e : dynamicObjects) {
//			e.prepareUpdate(frametime);
//		}
//		
//		for(int i=0; i<dynamicObjects.size(); i++) {
//			for(int j=0; j<dynamicObjects.size(); j++) {
//				dynamicObjects.get(i).checkCollision(dynamicObjects.get(j), frametime);
//			}
//			for(PhysicsObject e : staticObjects) {
//				dynamicObjects.get(i).checkCollision(e, frametime);
//			}
//		}
//		
//		for (DynamicObject e : dynamicObjects) {
//			e.update(frametime);
//		}
			for (IUpdateable e : updateable) {
				e.update(frametime);
			}

			for (DynamicObject e : dynamicObjects) {
				e.prepareUpdate(frametime);
			}

			for (DynamicObject e : dynamicObjects) {
				for (DynamicObject f : dynamicObjects) {
					e.checkCollision(f, frametime);
				}
			}

			for (DynamicObject e : dynamicObjects) {
				for (PhysicsObject f : staticObjects) {
					e.checkCollision(f, frametime);
				}
			}

			for (int i = 0; i < dynamicObjects.size(); i++) {
				dynamicObjects.get(i).update(frametime);
			}

			player.update(frametime);
			if (projectile != null) {
				projectile.update(frametime);
				for (int i = 0; i < dynamicObjects.size() && projectile != null; i++) {
					projectile.collisionCheck(dynamicObjects.get(i));
				}
			}

		} catch (Exception ex) {
			stop();
			Logger.getLogger(GameLoop.class.getName()).log(Level.SEVERE, "exception in gameLoop", ex);
		}
	}

	public IntersectionPoint getClosestPoint(Point point, PhysicsObject pObj) {
		double dist = Double.MAX_VALUE;
		IntersectionPoint ip = null;
		for (PhysicsObject e : allObjects) {
			if (!pObj.equals(e)) {
				IntersectionPoint tempIP = e.getClosestIntersection(point);
				if (tempIP.getDistance() < dist) {
					dist = tempIP.getDistance();
					ip = tempIP;

					// add speed vector if the object is dynamic
					if (e instanceof DynamicObject) {
						DynamicObject d = (DynamicObject) e;
						ip.setSpeedVec(d.getSpeedVector());
					}
				}
			}
		}
		return ip;
	}

	public static long getTime() {
		return time;
	}

	public void addObject(PhysicsObject object) {
		staticObjects.add(object);
		allObjects.add(object);
	}

	public static void addObject(DynamicObject object) {
		dynamicObjects.add(object);
		allObjects.add(object);

		if (object instanceof Bubble) {
			++bubbleCount;
		}

		// let the dynamic object be drawn in the game view
		gameController.drawNode(object.getNode());
	}

	public void removeObject(PhysicsObject object) {
		staticObjects.remove(object);
		allObjects.remove(object);

	}

	public static void removeObject(DynamicObject object) {

		if (object instanceof Bubble && dynamicObjects.contains(object)) {
			--bubbleCount;
		}

		dynamicObjects.remove(object);
		allObjects.remove(object);

		// remove the dynamic object from the view
		gameController.removeNode(object.getNode());
	}

	public void addPlayer(DynamicObject pl) {
		player = pl;
	}

	public void setGameBounds(double top, double right, double bottom, double left) {
		GameLoop.topBorder = top;
		GameLoop.rightBorder = right;
		GameLoop.bottomBorder = bottom;
		GameLoop.leftBorder = left;
	}

	public static void setProjectile(Projectile projectile) {
		GameLoop.projectile = projectile;
		gameController.drawNode(projectile.getNode());
	}

	public static void removeProjectile(Node projectile) {
		GameLoop.projectile = null;
		gameController.removeNode(projectile);
	}

	public static boolean hasProjectile() {
		return projectile != null;
	}

	public static double getTopBorder() {
		return topBorder;
	}

	public static double getRightBorder() {
		return rightBorder;
	}

	public static double getBottomBorder() {
		return bottomBorder;
	}

	public static double getLeftBorder() {
		return leftBorder;
	}

	public void setViewController(GameController gameController) {
		GameLoop.gameController = gameController;
	}

}
