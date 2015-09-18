/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects;

/**
 * Interface which defines which methods may be accessed by the objects inside
 * the GameObjects class.
 * @author faris
 */
public interface IGameObjects {

    /**
     * Add a Dynamic Object to the game.
     *
     * @param object the Dynamic Object to add to the scene.
     */
    public void addObject(IDynamicObject object);

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
    public void removeObject(IDynamicObject object);

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
     * @return if there is currently a projectile spawned in the game.
     */
    public boolean hasProjectile();

    /**
     * Called when the player dies. Handles quiting the game and reducing the
     * amount of lives by 1.
     */
    public void playerDied();
}
