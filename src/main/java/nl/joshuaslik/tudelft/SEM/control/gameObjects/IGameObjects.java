/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import java.io.InputStream;

import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.IModifier;
import nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects.ICircleViewObject;
import nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects.IImageViewObject;
import nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects.ILineViewObject;
import nl.joshuaslik.tudelft.SEM.model.container.Point;

/**
 * Interface which defines which methods may be accessed by the objects inside
 * the GameObjects class.
 *
 * @author faris
 */
public interface IGameObjects {
	
	/**
	 * Add a Dynamic Object to the game.
	 *
	 * @param object the Dynamic Object to add to the scene.
	 */
	public void addObject(PhysicsObject object);
	
	/**
	 * Add a Projectile to the game.
	 *
	 * @param projectile the Projectile to add to the scene.
	 */
	public void addProjectile(Projectile projectile);
	
	/**
	 * Remove a Dynamic Object from the game.
	 *
	 * @param object the Dynamic Object to remove from the game.
	 */
	public void removeObject(PhysicsObject object);
	
	/**
	 * Remove a Projectile from the game.
	 */
	public void removeProjectile();
	
	/**
	 * Get the min y value.
	 *
	 * @return min y value.
	 */
	public double getTopBorder();
	
	/**
	 * Get the maximum x value.
	 *
	 * @return max x value.
	 */
	public double getRightBorder();
	
	/**
	 * Get the maximum y value.
	 *
	 * @return max y value.
	 */
	public double getBottomBorder();
	
	/**
	 * Get the minimum x value.
	 *
	 * @return min x value.
	 */
	public double getLeftBorder();
	
	/**
	 * Check if there is currently a projectile spawned in the game.
	 *
	 * @return if there is currently a projectile spawned in the game.
	 */
	public boolean hasProjectile();
	
	/**
	 * Called when the player dies. Handles quiting the game and reducing the
	 * amount of lives by 1.
	 */
	public void playerDied();
	
	/**
	 * Create a circle in the view.
	 *
	 * @param centerX the x coordinate of the center of the circle.
	 * @param centerY the y coordinate of the center of the circle.
	 * @param radius  the radius of the circle.
	 * @return the interface of the circle view object.
	 */
	public ICircleViewObject makeCircle(double centerX, double centerY, double radius);
	
	/**
	 * Create a line in the view.
	 *
	 * @param startX the x coordinate of the start point of the line.
	 * @param startY the y coordinate of the start point of the line.
	 * @param endX   the x coordinate of the end point of the line.
	 * @param endY   the y coordinate of the end point of the line.
	 * @return the interface of the line view object.
	 */
	public ILineViewObject makeLine(double startX, double startY, double endX, double endY);
	
	/**
	 * Create an image in the view.
	 *
	 * @param is     the input stream of the image.
	 * @param height the height of the image.
	 * @param width  the width of the image.
	 * @return the interface of the image view object.
	 */
	public IImageViewObject makeImage(InputStream is, double height, double width);
	
	public Player getPlayer();
	
	public void handleModifierCollision(IModifier pickup, boolean isPlayerPickup, boolean isBubblePickup);
	
	public void handleBubbleSplit(Point p);
	
	public void addPoints(int points);
	
	public void addLife();
	
	public int bubblesLeft();
}
