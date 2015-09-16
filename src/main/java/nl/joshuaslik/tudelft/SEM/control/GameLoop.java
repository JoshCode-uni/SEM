/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.Bubble;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.DynamicObject;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.IUpdateable;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.PhysicsObject;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.Projectile;
import nl.joshuaslik.tudelft.SEM.control.viewController.GameController;
import nl.joshuaslik.tudelft.SEM.model.container.IntersectionPoint;
import nl.joshuaslik.tudelft.SEM.model.container.Point;

/**
 * The gameloop which updated all objects after each frame.
 *
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
	private static int score = 0;

	private static long time = 0;

	/**
	 * Stop the GameLoop and reset the static variables.
	 */
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
		score = 0;
	}

	/**
	 * Called every time JavaFX refreshes a frame. Calls all update methods.
	 *
	 * @param time the current time.
	 */
	@Override
	public void handle(long time) {

		if (bubbleCount <= 0) {
			gameController.levelCompleted();
			return;
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
			//			Logger.getLogger(GameLoop.class.getName()).log(Level.SEVERE, "exception in gameLoop", ex);
		}
	}

	/**
	 * Get the closest intersection point to the given point which is not equal
	 * to the given Physics object.
	 *
	 * @param point the point.
	 * @param pObj  the Physics Object.
	 * @return the closest Intersection Point.
	 */
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

	/**
	 * Get time of the last frame refresh.
	 *
	 * @return the time.
	 */
	public static long getTime() {
		return time;
	}

	/**
	 * Add a Physics Object to the game.
	 *
	 * @param object the Physics Object to add to the scene.
	 */
	public void addObject(PhysicsObject object) {
		staticObjects.add(object);
		allObjects.add(object);
	}

	/**
	 * Add a Dynamic Object to the game.
	 *
	 * @param object the Dynamic Object to add to the scene.
	 */
	public static void addObject(DynamicObject object) {
		dynamicObjects.add(object);
		allObjects.add(object);

		if (object instanceof Bubble) {
			++bubbleCount;
		}

		// let the dynamic object be drawn in the game view
		gameController.drawNode(object.getNode());
	}

	/**
	 * Remove a Physics Object from the game.
	 *
	 * @param object the Physics Object to remove from the scene.
	 */
	public void removeObject(PhysicsObject object) {
		staticObjects.remove(object);
		allObjects.remove(object);

	}

	/**
	 * Remove a Dynamic Object from the game.
	 *
	 * @param object the Dynamic Object to remove from the game.
	 */
	public static void removeObject(DynamicObject object) {

		if (object instanceof Bubble && dynamicObjects.contains(object)) {
			score += 10;
			--bubbleCount;
		}

		dynamicObjects.remove(object);
		allObjects.remove(object);

		// remove the dynamic object from the view
		gameController.removeNode(object.getNode());
	}

	/**
	 * Get all objects which are currently in the game (except for player/projectile).
	 *
	 * @return all objects which are currently in the game.
	 */
	public static ArrayList<PhysicsObject> getAllObjects() {
		return allObjects;
	}

	/**
	 * Add a player object to the game.
	 *
	 * @param pl the player object.
	 */
	public void addPlayer(DynamicObject pl) {
		player = pl;
	}

	/**
	 * Set the bounds of the game.
	 *
	 * @param top    min y value.
	 * @param right  max x value.
	 * @param bottom max y value.
	 * @param left   min x value.
	 */
	public void setGameBounds(double top, double right, double bottom, double left) {
		GameLoop.topBorder = top;
		GameLoop.rightBorder = right;
		GameLoop.bottomBorder = bottom;
		GameLoop.leftBorder = left;
	}

	/**
	 * Add a projectile to the game.
	 *
	 * @param projectile projectile to add.
	 */
	public static void setProjectile(Projectile projectile) {
		GameLoop.projectile = projectile;
		gameController.drawNode(projectile.getNode());
	}

	/**
	 * Remove a projectile from the game.
	 *
	 * @param projectile the projectile.
	 */
	public static void removeProjectile(Node projectile) {
		GameLoop.projectile = null;
		gameController.removeNode(projectile);
	}

	/**
	 * Check if there currently is a projectile.
	 *
	 * @return if there is a projectile.
	 */
	public static boolean hasProjectile() {
		return projectile != null;
	}

	/**
	 * Get the min y value.
	 *
	 * @return min y value.
	 */
	public static double getTopBorder() {
		return topBorder;
	}

	/**
	 * Get the maximum x value.
	 *
	 * @return max x value.
	 */
	public static double getRightBorder() {
		return rightBorder;
	}

	/**
	 * Get the maximum y value.
	 *
	 * @return max y value.
	 */
	public static double getBottomBorder() {
		return bottomBorder;
	}

	/**
	 * Get the minimum x value.
	 *
	 * @return min x value.
	 */
	public static double getLeftBorder() {
		return leftBorder;
	}

	/**
	 * Set the view controller class.
	 *
	 * @param gameController the view controller class.
	 */
	public void setViewController(GameController gameController) {
		GameLoop.gameController = gameController;
	}

	/**
	 * Get the game controller class.
	 *
	 * @return the game controller class.
	 */
	public static GameController getGameController() {
		return gameController;
	}

	/**
	 * Get the score achieved in this level.
	 *
	 * @return the score.
	 */
	public static int getScore() {
		return score;
	}
}
