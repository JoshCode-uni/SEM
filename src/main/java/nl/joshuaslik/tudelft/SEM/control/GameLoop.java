/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.Scene;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.GameObjects;
import nl.joshuaslik.tudelft.SEM.control.viewController.GameController;

/**
 * The gameloop which updated all objects after each frame.
 *
 * @author faris
 */
public class GameLoop extends AnimationTimer implements IDraw {

    private GameController gameController;
    private GameObjects gameObjects;
//
    private long time = 0;

    /**
     * Stop the GameLoop and reset the static variables.
     */
//	@Override
//	public void stop() {
//		super.stop();
//		gameController = null;
//		projectile = null;
//		topBorder = 0;
//		rightBorder = 0;
//		bottomBorder = 0;
//		leftBorder = 0;
//		time = 0;
//		allObjects.clear();
//		dynamicObjects.clear();
//		bubbleCount = 0;
//		score = 0;
//	}
    /**
     *
     * @param gameController
     * @param currentLevel
     * @param top
     * @param right
     * @param bottom
     * @param left
     */
    public GameLoop(GameController gameController, int currentLevel, double top,
            double right, double bottom, double left, Scene scene) {
        this.gameController = gameController;
        gameObjects = new GameObjects((IDraw) this, currentLevel, top, right,
                bottom, left, scene);
    }

    /**
     * Called every time JavaFX refreshes a frame. Calls all update methods.
     *
     * @param time the current time.
     */
    @Override
    public void handle(long time) {

        if (gameObjects.allBubblesDestroyed()) {
            gameController.levelCompleted();
            return;
        }

        try {
            // update time
            long frametime = this.time != 0 ? time - this.time : 165_000_000;
            this.time = time;

            gameController.updateTime(frametime);
            gameObjects.update(frametime);

        }
        catch (Exception ex) {
            stop();
            Logger.getLogger(GameLoop.class.getName()).log(Level.SEVERE, "Exception in game loop", ex);
        }
    }

//	/**
//	 * Get the closest intersection point to the given point which is not equal
//	 * to the given Physics object.
//	 *
//	 * @param point the point.
//	 * @param pObj  the Physics Object.
//	 * @return the closest Intersection Point.
//	 */
//	public IntersectionPoint getClosestPoint(Point point, PhysicsObject pObj) {
//		double dist = Double.MAX_VALUE;
//		IntersectionPoint ip = null;
//		for (PhysicsObject e : allObjects) {
//			if (!pObj.equals(e)) {
//				IntersectionPoint tempIP = e.getClosestIntersection(point);
//				if (tempIP.getDistance() < dist) {
//					dist = tempIP.getDistance();
//					ip = tempIP;
//
//					// add speed vector if the object is dynamic
//					if (e instanceof DynamicObject) {
//						DynamicObject d = (DynamicObject) e;
//						ip.setSpeedVec(d.getSpeedVector());
//					}
//				}
//			}
//		}
//		return ip;
//	}
    /**
     * Get time of the last frame refresh.
     *
     * @return the time.
     */
    public long getTime() {
        return time;
    }

//	/**
//	 * Add a Physics Object to the game.
//	 *
//	 * @param object the Physics Object to add to the scene.
//	 */
//	public void addObject(PhysicsObject object) {
//		staticObjects.add(object);
//		allObjects.add(object);
//	}
//
//	/**
//	 * Add a Dynamic Object to the game.
//	 *
//	 * @param object the Dynamic Object to add to the scene.
//	 */
//	public static void addObject(DynamicObject object) {
//		dynamicObjects.add(object);
//		allObjects.add(object);
//
//		if (object instanceof Bubble) {
//			++bubbleCount;
//		}
//
//		// let the dynamic object be drawn in the game view
//		gameController.drawNode(object.getNode());
//	}
//
//	/**
//	 * Remove a Physics Object from the game.
//	 *
//	 * @param object the Physics Object to remove from the scene.
//	 */
//	public void removeObject(PhysicsObject object) {
//		staticObjects.remove(object);
//		allObjects.remove(object);
//
//	}
//
//	/**
//	 * Remove a Dynamic Object from the game.
//	 *
//	 * @param object the Dynamic Object to remove from the game.
//	 */
//	public static void removeObject(DynamicObject object) {
//
//		if (object instanceof Bubble && dynamicObjects.contains(object)) {
//			score += 10;
//			--bubbleCount;
//		}
//
//		dynamicObjects.remove(object);
//		allObjects.remove(object);
//
//		// remove the dynamic object from the view
//		gameController.removeNode(object.getNode());
//	}
//
//	/**
//	 * Get all objects which are currently in the game (except for player/projectile).
//	 *
//	 * @return all objects which are currently in the game.
//	 */
//	public static ArrayList<PhysicsObject> getAllObjects() {
//		return allObjects;
//	}
//
//	/**
//	 * Add a player object to the game.
//	 *
//	 * @param pl the player object.
//	 */
//	public void addPlayer(DynamicObject pl) {
//		player = pl;
//	}
//
//	/**
//	 * Set the bounds of the game.
//	 *
//	 * @param top    min y value.
//	 * @param right  max x value.
//	 * @param bottom max y value.
//	 * @param left   min x value.
//	 */
//	public void setGameBounds(double top, double right, double bottom, double left) {
//		GameLoop.topBorder = top;
//		GameLoop.rightBorder = right;
//		GameLoop.bottomBorder = bottom;
//		GameLoop.leftBorder = left;
//	}
//
//	/**
//	 * Add a projectile to the game.
//	 *
//	 * @param projectile projectile to add.
//	 */
//	public static void setProjectile(Projectile projectile) {
//		GameLoop.projectile = projectile;
//		gameController.drawNode(projectile.getNode());
//	}
//
//	/**
//	 * Remove a projectile from the game.
//	 *
//	 * @param projectile the projectile.
//	 */
//	public static void removeProjectile(Node projectile) {
//		GameLoop.projectile = null;
//		gameController.removeNode(projectile);
//	}
//
//	/**
//	 * Check if there currently is a projectile.
//	 *
//	 * @return if there is a projectile.
//	 */
//	public static boolean hasProjectile() {
//		return projectile != null;
//	}
//
//	/**
//	 * Get the min y value.
//	 *
//	 * @return min y value.
//	 */
//	public static double getTopBorder() {
//		return topBorder;
//	}
//
//	/**
//	 * Get the maximum x value.
//	 *
//	 * @return max x value.
//	 */
//	public static double getRightBorder() {
//		return rightBorder;
//	}
//
//	/**
//	 * Get the maximum y value.
//	 *
//	 * @return max y value.
//	 */
//	public static double getBottomBorder() {
//		return bottomBorder;
//	}
//
//	/**
//	 * Get the minimum x value.
//	 *
//	 * @return min x value.
//	 */
//	public static double getLeftBorder() {
//		return leftBorder;
//	}
    /**
     * Set the view controller class.
     *
     * @param gameController the view controller class.
     */
    public void setViewController(GameController gameController) {
        this.gameController = gameController;
    }

    /**
     * Get the game controller class.
     *
     * @return the game controller class.
     */
    public GameController getViewController() {
        return gameController;
    }

//	/**
//	 * Get the score achieved in this level.
//	 *
//	 * @return the score.
//	 */
//	public static int getScore() {
//		return score;
//	}
    @Override
    public void drawOnScreen(Node n) {
        gameController.drawNode(n);
    }

    @Override
    public void removeFromScreen(Node n) {
        gameController.removeNode(n);
    }

    /**
     * Get the score achieved in this level.
     *
     * @return the score.
     */
    public int getScore() {
        return gameObjects.getScore();
    }

    @Override
    public void playerDied() {
        gameController.died();
    }
}
