/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import java.io.InputStream;
import java.util.ArrayList;

import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.player.AbstractPlayerDecorator;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.player.IPlayerModifier;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.player.PlayerBaseModifier;
import nl.joshuaslik.tudelft.SEM.control.viewController.IKeyboard;
import nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects.IImageViewObject;
import nl.joshuaslik.tudelft.SEM.utility.GameLog;
import nl.joshuaslik.tudelft.SEM.utility.IObservable;
import nl.joshuaslik.tudelft.SEM.utility.IObserver;
import nl.joshuaslik.tudelft.SEM.utility.Time;

/**
 * A class containing the position of the player. This class also controller the player.
 *
 * @author faris
 */
public class Player extends AbstractPhysicsObject implements IUpdateable, ICollider, IObservable<Player, Player.EventType> {

    private IPlayerModifier modifier = new PlayerBaseModifier();
    private final IImageViewObject image;
    private final IKeyboard keyboard;
    private static final double MAX_SPEED = 300;
    private ArrayList<Double> leftDoor = new ArrayList<>();
    private ArrayList<Double> rightDoor = new ArrayList<>();
    private final double playerXstart;
    private final boolean p2;
    private boolean isDead = false;
    private int score = 0;
    private final ArrayList<IObserver<Player, EventType>> observers = new ArrayList<>();

    /**
     * Create a player.
     *
     * @param gameObjects
     * @param is
     * @param kb keyboard which controller the actions of the player.
     */
    public Player(final IGameObjects gameObjects, final InputStream is, final IKeyboard kb, boolean p2) {
        super(gameObjects);
        image = getGameObjects().makeImage(is, 100, 100);
        image.setX((getGameObjects().getRightBorder() - getGameObjects().getLeftBorder()) / 2.0);
        image.setY(getGameObjects().getBottomBorder() - image.getHeight());
        this.p2 = p2;
        keyboard = kb;
        playerXstart = image.getStartX();
        if (p2) {
            image.adjustHSB(-0.30, 1.0, 0.35);
        }
    }

    /**
     * Check if the player has collided with anything. Not implemented.
     *
     * @param obj2 object to check collision with
     * @param nanoFrameTime the framerate (nanoseconds/frame)
     */
    @Override
    public void checkCollision(final IIntersectable obj2, final long nanoFrameTime) {
        if (Bubble.class.isAssignableFrom(obj2.getClass())) {
            Bubble bubble = (Bubble) obj2;
            if (image.intersects(bubble.getCircleViewObject())) {
                getGameObjects().playerDied();
                isDead = true;
                notifyObservers(EventType.DIED);
            }
        }
    }

    /**
     * Update the position of the player.
     *
     * @param nanoFrameTime the framerate (nanoseconds/frame)
     */
    @Override
    public void update(final long nanoFrameTime) {
        if (keyboard.isMoveLeft(p2)) {
            moveLeft(nanoFrameTime);
        } else if (keyboard.isMoveRight(p2)) {
            moveRight(nanoFrameTime);
        }
        if (keyboard.isShoot(p2) && !getGameObjects().hasProjectile(p2)) {
            shoot();
        }
    }

    /**
     * Move left.
     *
     * @param nanoFrameTime the framerate (nanoseconds/frame)
     */
    private void moveLeft(final long nanoFrameTime) {
        double leftPos = image.getStartX() + -MAX_SPEED * nanoFrameTime / Time.SECOND_NANO * getMoveSpeedMultiplier();
        double leftBorder = getClosestLeftBorder();
        if (leftBorder < leftPos) {
            image.setX(leftPos);
        } else {
            image.setX(leftBorder);
        }
        image.setScaleX(1);
        notifyObservers(EventType.WALK);
    }

    /**
     * Move right.
     *
     * @param nanoFrameTime the framerate (nanoseconds/frame)
     */
    private void moveRight(final long nanoFrameTime) {
        double rightPos = image.getStartX() + MAX_SPEED * nanoFrameTime / Time.SECOND_NANO * getMoveSpeedMultiplier();
        double rightBorder = getClosestRightBorder();
        if (rightBorder - 100 > rightPos) {
            image.setX(rightPos);
        } else {
            image.setX(rightBorder - 100);
        }
        image.setScaleX(-1);
        notifyObservers(EventType.WALK);
    }

    /**
     * Shoot.
     */
    private void shoot() {
        double bulletX = (image.getStartX() + image.getEndX()) / 2.0;
        double bulletY = image.getEndY();
        GameLog.addInfoLog("Player shoots at: (" + Double.toString(bulletX) + ", " + Double.toString(bulletY) + ")");
        Projectile proj = makeProjectile(getGameObjects(), bulletX, bulletY);
        proj.setPlayer(this);
        getGameObjects().addProjectile(proj);
        notifyObservers(EventType.SHOOT);
    }

    /**
     * Method for testing purposes.
     *
     * @return the max speed.
     */
    public static double getMaxSpeed() {
        return MAX_SPEED;
    }

    /**
     * Method for testing purposes. Avoiding object creation.
     *
     * @param gameObjects game object.
     * @param startX start X.
     * @param startY start Y.
     * @return the requested projectile.
     */
    public Projectile makeProjectile(final IGameObjects gameObjects, final double startX, final double startY) {
        return new Projectile(gameObjects, startX, startY, getProjectileSpeedMultiplier(), getProjectileSpikeDelay());
    }

    /**
     * Destroy this player.
     */
    public void destroy() {
        image.destroy();
    }
    
    /**
     * If the player intersects with the given image.
     * @param otherimage an image.
     * @return if the player image and other image intersect.
     */
    public boolean intersectsWith(IImageViewObject otherimage) {
        return image.intersects(otherimage);
    }

    /**
     * Add a modifier.
     * @param newmod a new modifier to add.
     */
    public void addModifier(final AbstractPlayerDecorator newmod) {
        modifier = newmod.decorate(modifier);
    }

    /**
     * Get the speed multiplier of the player.
     * @return the speed multiplier of the player.
     */
    private double getMoveSpeedMultiplier() {
        return modifier.getMoveSpeedMultiplier();
    }

    /**
     * Get the speed multiplier of a projectile.
     * @return the speed multiplier of a projectile.
     */
    private double getProjectileSpeedMultiplier() {
        return modifier.getProjectileSpeedMultiplier();
    }

    /**
     * Get the delay of a projectile.
     * @return the delay of a projectile.
     */
    private int getProjectileSpikeDelay() {
        return modifier.getProjectileSpikeDelay();
    }

    /**
     * Add a door.
     * @param xCoordinate the x coordinate.
     */
    public void setDoor(final double xCoordinate) {
        if (xCoordinate > image.getStartX()) {
            rightDoor.add(xCoordinate);
        } else {
            leftDoor.add(xCoordinate);
        }
    }

    /**
     * Remove a door.
     * @param xCoordinate the x coordinate.
     */
    public void removeDoor(final double xCoordinate) {
        rightDoor.remove(xCoordinate);
        leftDoor.remove(xCoordinate);
    }

    /**
     * Get the closest left border.
     * @return the closest left border.
     */
    private double getClosestLeftBorder() {
        double res = getGameObjects().getLeftBorder();
        for (double e : leftDoor) {
            if (e > res && e < playerXstart) {
                res = e;
            }
        }
        return res;
    }

    /**
     * Get the closest right border.
     * @return the closest right border. 
     */
    private double getClosestRightBorder() {
        double res = getGameObjects().getRightBorder();
        for (double e : rightDoor) {
            if (e < res && e > playerXstart) {
                res = e;
            }
        }
        return res;
    }

    /**
     * Set a left door.
     * @param leftDoor a left door.
     */
    void setLeftDoor(final ArrayList<Double> leftDoor) {
        this.leftDoor = leftDoor;
    }

    /**
     * Set a right door.
     * @param rightDoor a right door.
     */
    void setRightDoor(final ArrayList<Double> rightDoor) {
        this.rightDoor = rightDoor;
    }

    /**
     * @return if this is player 2.
     */
    public boolean getP2() {
        return p2;
    }

    /**
     * Add points to the score of this player.
     * @param n the amount of points to add.
     */
    public void addPoints(int n) {
        score += n;
    }

    /**
     * Get the score of the player.
     * @return the score.
     */
    public int getScore() {
        return score;
    }

    /**
     * @return if the player is dead.
     */
    public boolean isDead() {
        return isDead;
    }

    /**
     * Add an observer to this observable object.
     * @param o an observer.
     */
    @Override
    public void addObserver(IObserver o) {
        if(o.sameClass(Player.class)) {
            observers.add(o);
        }
    }

    /**
     * Delete an observer from this observable object.
     * @param o an observer.
     */
    @Override
    public void deleteObserver(IObserver o) {
        if(o.sameClass(Player.class)) {
            observers.remove(o);
        }
    }

    /**
     * Notify the observers of an event of this observable object.
     * @param event an event.
     */
    @Override
    public void notifyObservers(EventType event) {
        for (IObserver o : observers) {
            o.update(this, event);
        }
    }
    
    /**
     * Enum containing all of the events which can be triggered by a player.
     */
    public static enum EventType {
        SHOOT, WALK, DIED;
    }
}
