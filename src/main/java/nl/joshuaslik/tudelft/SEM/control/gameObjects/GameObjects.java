/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.joshuaslik.tudelft.SEM.control.IDraw;
import nl.joshuaslik.tudelft.SEM.control.viewController.GameController;
import nl.joshuaslik.tudelft.SEM.control.viewController.IKeyboard;
import nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects.ICircleViewObject;
import nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects.IImageViewObject;
import nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects.ILineViewObject;
import nl.joshuaslik.tudelft.SEM.model.container.Levels;

/**
 * Game objects stores all objects of a level and updates them. It also keeps
 * track of other things, like if there are any bubbles left and the score.
 * @author faris
 */
public class GameObjects implements IUpdateable, IGameObjects {

    private final ArrayList<PhysicsObject> staticObjects = new ArrayList<>();
    private final ArrayList<IDynamicObject> dynamicObjects = new ArrayList<>();

    private final ArrayList<IDynamicObject> addDynamicBuffer = new ArrayList<>();
    private final ArrayList<IDynamicObject> removeDynamicBuffer = new ArrayList<>();

    private boolean hasProjectile = false;
    private Projectile projectile = null;
    private double topBorder, rightBorder, bottomBorder, leftBorder;

    private int bubbleCount = 0;
    private int score = 0;

    private final IDraw draw;

    /**
     * Construct all required objects for the given level.
     * @param draw interface to the drawing class which allows us to draw the game objects.
     * @param level the current level.
     * @param topBorder y value of the top border.
     * @param rightBorder x value of the right border.
     * @param bottomBorder y value of the bottom border.
     * @param leftBorder x value of the left border.
     * @param keyBoard
     */
    public GameObjects(IDraw draw, int level, double topBorder, double rightBorder,
            double bottomBorder, double leftBorder, IKeyboard keyBoard) {
        this.draw = draw;
        initializeBorders(topBorder, rightBorder, bottomBorder, leftBorder);
        initializePlayer(keyBoard);
        initializeLevel(level);
        addBufferedDynamicObjects();
    }

    /**
     * Update the positions of all objects.
     *
     * @param nanoFrameTime the time of a frame in nanoseconds.
     */
    @Override
    public void update(long nanoFrameTime) {
        // only add/remove objects at the beginning of each update
        addBufferedDynamicObjects();
        removeBufferedDynamicObjects();

        // calculate next positions
        for (IDynamicObject e : dynamicObjects) {
            e.prepareUpdate(nanoFrameTime);
        }

        // check for collisions
        for (int i = 0; i < dynamicObjects.size(); i++) {
            for (int j = i + 1; j < dynamicObjects.size(); j++) {
                dynamicObjects.get(i).checkCollision(dynamicObjects.get(j), nanoFrameTime);
            }
            for (PhysicsObject e : staticObjects) {
                dynamicObjects.get(i).checkCollision(e, nanoFrameTime);
            }
        }

        // update positions based on the calculated positions (which might've
        // been changed by a collision)
        for (IDynamicObject e : dynamicObjects) {
            e.update(nanoFrameTime);
        }
    }

    /**
     * Initialize the borders of the game.
     * @param topBorder y value of the top border.
     * @param rightBorder x value of the right border.
     * @param bottomBorder y value of the bottom border.
     * @param leftBorder x value of the left border.
     */
    private void initializeBorders(double topBorder, double rightBorder, 
            double bottomBorder, double leftBorder) {
        
        // add top, left, right and bottom lines
        Line top = new Line((IGameObjects) this, leftBorder, topBorder, 
                rightBorder, topBorder);
        
        Line left = new Line((IGameObjects) this, leftBorder, topBorder, 
                leftBorder, bottomBorder);
        
        Line right = new Line((IGameObjects) this,rightBorder, topBorder, 
                rightBorder, bottomBorder);
        
        Line bottom = new Line((IGameObjects) this,leftBorder, bottomBorder, 
                rightBorder, bottomBorder);
        
        addObject(top);
        addObject(left);
        addObject(right);
        addObject(bottom);

        setGameBounds(topBorder, rightBorder, bottomBorder, leftBorder);
    }

    /**
     * Initialize the player.
     * @param scene the scene of the game (to add a keylistener to).
     */
    private void initializePlayer(IKeyboard keyBoard) {
        InputStream is;
        try {
            is = getClass().getResource("/data/gui/img/penguin.png").openStream();
        }
        catch (IOException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, "Couldn't load player image", ex);
            return;
        }

        Player player = new Player((IGameObjects) this, is, keyBoard);
        addObject(player);
    }

    /**
     * Initialize the level.
     * @param level the level to initialize.
     */
    private void initializeLevel(int level) {
        ArrayList<Bubble> bubbles = Levels.getLevel(level, (IGameObjects) this);
        for (Bubble e : bubbles) {
            addObject(e);
        }
    }

    /**
     * Add a Physics Object to the game.
     *
     * @param object the Physics Object to add to the scene.
     */
    public void addObject(PhysicsObject object) {
        staticObjects.add(object);
    }

    /**
     * Add a Dynamic Object to the game.
     *
     * @param object the Dynamic Object to add to the scene.
     */
    @Override
    public void addObject(IDynamicObject object) {
        addDynamicBuffer.add(object);
    }

    /**
     * Remove a Dynamic Object from the game.
     *
     * @param object the Dynamic Object to remove from the game.
     */
    @Override
    public void removeObject(IDynamicObject object) {
        removeDynamicBuffer.add(object);
    }

    /**
     * Add all buffered dynamic objects to the scene.
     */
    private void addBufferedDynamicObjects() {
        for (IDynamicObject object : addDynamicBuffer) {
            dynamicObjects.add(object);

            if (object instanceof Bubble) {
                ++bubbleCount;
            }
        }
        addDynamicBuffer.clear();
    }

    /**
     * Remove all buffered dynamic objects from the game.
     */
    private void removeBufferedDynamicObjects() {
        for (IDynamicObject object : removeDynamicBuffer) {
            if (object instanceof Bubble) {
                score += 10;
                --bubbleCount;
            }

            dynamicObjects.remove(object);
        }
        removeDynamicBuffer.clear();
    }

    /**
     * Set the bounds of the game.
     *
     * @param top min y value.
     * @param right max x value.
     * @param bottom max y value.
     * @param left min x value.
     */
    private void setGameBounds(double top, double right, double bottom, double left) {
        topBorder = top;
        rightBorder = right;
        bottomBorder = bottom;
        leftBorder = left;
    }

    /**
     * Get the min y value.
     *
     * @return min y value.
     */
    @Override
    public double getTopBorder() {
        return topBorder;
    }

    /**
     * Get the maximum x value.
     *
     * @return max x value.
     */
    @Override
    public double getRightBorder() {
        return rightBorder;
    }

    /**
     * Get the maximum y value.
     *
     * @return max y value.
     */
    @Override
    public double getBottomBorder() {
        return bottomBorder;
    }

    /**
     * Get the minimum x value.
     *
     * @return min x value.
     */
    @Override
    public double getLeftBorder() {
        return leftBorder;
    }

    /**
     * Get the score achieved in this level.
     *
     * @return the score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Check if all bubbles are destroyed.
     * @return if all bubbles are destroyed.
     */
    public boolean allBubblesDestroyed() {
        return bubbleCount == 0;
    }

    /**
     * Check if the game currently has a spawned projectile.
     * @return if the game currently has a spawned projectile.
     */
    @Override
    public boolean hasProjectile() {
        return hasProjectile;
    }

    /**
     * Prepare for update.
     * @param nanoFrameTime the frame time in nano seconds. 
     */
    @Override
    public void prepareUpdate(long nanoFrameTime) {
        //no preparation required, everything is handled in the update method
    }

    /**
     * Handle event: player died.
     */
    @Override
    public void playerDied() {
        draw.playerDied();
    }

    /**
     * Add a projectile to the game.
     * @param projectile the projectile to add.
     */
    @Override
    public void addProjectile(Projectile projectile) {
        addObject(projectile);
        this.projectile = projectile;
        hasProjectile = true;
    }

    /**
     * Remove the projectile from the game.
     */
    @Override
    public void removeProjectile() {
        removeObject(projectile);
        this.projectile = null;
        hasProjectile = false;
    }
    
    @Override
    public ICircleViewObject makeCircle(double centerX, double centerY, 
            double radius) {
        return draw.makeCircle(centerX, centerY, radius);
    }
    
    @Override
    public ILineViewObject makeLine(double startX, double startY, 
            double endX, double endY) {
        return draw.makeLine(startX, startY, endX, endY);
    }
    
    @Override
    public IImageViewObject makeImage(InputStream is, double height, 
            double width) {
        return draw.makeImage(is, width, height);
    }
}
