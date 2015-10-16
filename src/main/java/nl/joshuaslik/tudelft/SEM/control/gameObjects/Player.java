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
import nl.joshuaslik.tudelft.SEM.utility.Time;

/**
 * A class containing the position of the player. This class also controller the player.
 *
 * @author faris
 */
public class Player extends AbstractPhysicsObject implements IUpdateable, ICollider {

    private IPlayerModifier modifier = new PlayerBaseModifier();
    private final IImageViewObject image;
    private final IKeyboard keyboard;
    private static final double MAX_SPEED = 300;
    private ArrayList<Double> leftDoor = new ArrayList<>();
    private ArrayList<Double> rightDoor = new ArrayList<>();
    private final double playerXstart;
    private boolean p2;
    private boolean isDead = false;
    private int score = 0;

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

    public IImageViewObject getImage() {
        return image;
    }

    public void addModifier(final AbstractPlayerDecorator newmod) {
        modifier = newmod.decorate(modifier);
    }

    private double getMoveSpeedMultiplier() {
        return modifier.getMoveSpeedMultiplier();
    }

    private double getProjectileSpeedMultiplier() {
        return modifier.getProjectileSpeedMultiplier();
    }

    private int getProjectileSpikeDelay() {
        return modifier.getProjectileSpikeDelay();
    }

    public void setDoor(final double xCoordinate) {
        if (xCoordinate > image.getStartX()) {
            rightDoor.add(xCoordinate);
        } else {
            leftDoor.add(xCoordinate);
        }
    }

    public void removeDoor(final double xCoordinate) {
        rightDoor.remove(xCoordinate);
        leftDoor.remove(xCoordinate);
    }

    private double getClosestLeftBorder() {
        double res = getGameObjects().getLeftBorder();
        for (double e : leftDoor) {
            if (e > res && e < playerXstart) {
                res = e;
            }
        }
        return res;
    }

    private double getClosestRightBorder() {
        double res = getGameObjects().getRightBorder();
        for (double e : rightDoor) {
            if (e < res && e > playerXstart) {
                res = e;
            }
        }
        return res;
    }

    void setLeftDoor(final ArrayList<Double> leftDoor) {
        this.leftDoor = leftDoor;
    }

    void setRightDoor(final ArrayList<Double> rightDoor) {
        this.rightDoor = rightDoor;
    }

    public boolean getP2() {
        return p2;
    }

    public void addPoints(int n) {
        score += n;
    }

    public int getScore() {
        return score;
    }

    public boolean isDead() {
        return isDead;
    }
}
