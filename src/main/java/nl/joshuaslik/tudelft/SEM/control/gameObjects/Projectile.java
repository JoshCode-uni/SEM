/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects.ILineViewObject;
import nl.joshuaslik.tudelft.SEM.utility.GameLog;

/**
 * A class which holds the information of a projectile which is shot by the palyer.
 *
 * @author faris
 */
public class Projectile extends AbstractLine implements IUpdateable, ICollideReceiver {

    private final ILineViewObject line;

    private final double growSpeed;
    private double delay;
    private boolean isActive = true;
    private Player player;

    /**
     * Create a projectile.
     *
     * @param gameObjects   gameobjects reference.
     * @param startX        start x coordinate of the projectile.
     * @param startY        start y coordinate of the projectile.
     * @param speed         the speed of the projectile.
     * @param delay         the delay of the projectile upon hitting the ceiling.
     */
    public Projectile(final IGameObjects gameObjects, final double startX, final double startY, 
            final double speed, final int delay) {
        super(gameObjects, startX, startY - 2, startX, startY - 3);

        growSpeed = 1000 * speed;
        this.delay = delay * 1_000_000_000.0;

        line = getGameObjects().makeLine(startX, startY - 2, startX, startY - 3);

        line.setStrokeWidth(7);
        line.setColor(0.2, 0.1, 0.1);
        line.setOpacity(0.8);

        GameLog.addInfoLog("Projectile created at: (" + Double.toString(startX) + ", " + Double.toString(startY) + ")");
    }
    
    @Override
    public void destroy() {
        getGameObjects().removeProjectile(player.getP2());
        line.destroy();
    }

    /**
     * Update the length of the projectile.
     *
     * @param nanoFrameTime the framerate (nanoseconds/frame).
     */
    @Override
    public void update(final long nanoFrameTime) {
        if (line.getEndY() <= getGameObjects().getTopBorder() + 2) {
            if (delay > 0) {
                delay -= nanoFrameTime;
                return;
            }
            GameLog.addInfoLog("Projectile hit ceiling at: (" + Double.toString(line.getEndX()) + 
                    ", " + Double.toString(line.getEndY()) + ")");
            destroy();
            isActive = false;
        }
        double endY = line.getEndY() - growSpeed * (nanoFrameTime / 1_000_000_000.0);
        if (endY < getGameObjects().getTopBorder() + 2) {
            line.setEndY(getGameObjects().getTopBorder() + 2);
        } else {
            line.setEndY(endY);
        }
        updateLinePoints();
    }

    /**
     * Recalculated p1 and p2.
     */
    public void updateLinePoints() {
        updateLinePoints(line);
    }

    /**
     * Called when a dynamic object collides with this projectile. If it is a bubble we will split
     * the bubble.
     *
     * @param obj2          the dynamic object which collided with this projectile.
     * @param nanoFrameTime the time which a frame takes.
     */
    @Override
    public void collide(final ICollider obj2, final long nanoFrameTime) {
        if (isActive && Bubble.class.isAssignableFrom(obj2.getClass())) {
            Bubble bubble = (Bubble) obj2;
            bubble.splitBubble();
            player.addPoints(10);
            GameLog.addInfoLog("Projectile hit bubble at: (" + Double.toString(line.getEndX()) + ", " + Double.toString(line.getEndY())
                               + ")");
            getGameObjects().removeProjectile(player.getP2());
            line.destroy();
            isActive = false;
        }
    }

    /**
     * Get the delay of the projectile.
     * @return the delay.
     */
    public double getDelay() {
        return delay;
    }

    /**
     * Get the speed of the projectile.
     * @return the speed.
     */
    public double getGrowSpeed() {
        return growSpeed;
    }
    
    public void setPlayer(Player player) {
    	this.player = player;
    }
    
    public Player getPlayer(){
    	return player;
    }
}
