/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects;

/**
 *
 * @author faris
 */
public interface IGameObjects {

    /**
     * Add a Dynamic Object to the game.
     *
     * @param object the Dynamic Object to add to the scene.
     */
    public void addObject(IDynamicObject object);

    public void addProjectile(Projectile projectile);
    
    /**
     * Remove a Dynamic Object from the game.
     *
     * @param object the Dynamic Object to remove from the game.
     */
    public void removeObject(IDynamicObject object);

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
    
    public boolean hasProjectile();

    public void playerDied();
}
