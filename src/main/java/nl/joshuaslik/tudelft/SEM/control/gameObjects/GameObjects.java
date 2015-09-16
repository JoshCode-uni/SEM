/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import nl.joshuaslik.tudelft.SEM.control.IDraw;
import nl.joshuaslik.tudelft.SEM.control.viewController.GameController;
import nl.joshuaslik.tudelft.SEM.model.container.IntersectionPoint;
import nl.joshuaslik.tudelft.SEM.model.container.Levels;
import nl.joshuaslik.tudelft.SEM.model.container.Point;

/**
 *
 * @author faris
 */
public class GameObjects implements IUpdateable, IGameObjects {

    private ArrayList<PhysicsObject> allObjects = new ArrayList<>();
    private ArrayList<PhysicsObject> staticObjects = new ArrayList<>();
    private ArrayList<IDynamicObject> dynamicObjects = new ArrayList<>();

    private ArrayList<IDynamicObject> addDynamicBuffer = new ArrayList<>();
    private ArrayList<IDynamicObject> removeDynamicBuffer = new ArrayList<>();

    // list of updateable instances which aren't dynamic objects
    private ArrayList<IUpdateable> updateable = new ArrayList<>();

    private IDynamicObject player;
    private boolean hasProjectile = false;
    private Projectile projectile = null;
    private double topBorder, rightBorder, bottomBorder, leftBorder;

    private int bubbleCount = 0;
    private int score = 0;

    private final IDraw draw;

    public GameObjects(IDraw draw, int level, double topBorder, double rightBorder,
            double bottomBorder, double leftBorder, Scene scene) {
        this.draw = draw;
        initializeBorders(topBorder, rightBorder, bottomBorder, leftBorder);
        initializePlayer(scene);
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

        addBufferedDynamicObjects();
        removeBufferedDynamicObjects();

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
        if (hasProjectile) { // can hit 2 bubbles in the same frame
            projectile.update(nanoFrameTime);
        }

        for (IDynamicObject e : dynamicObjects) {
            if (hasProjectile) {
                projectile.checkCollision(e, nanoFrameTime);
            }
        }

        for (IUpdateable e : updateable) {
            e.update(nanoFrameTime);
        }

        for (IDynamicObject e : dynamicObjects) {
            e.prepareUpdate(nanoFrameTime);
        }

        for (IDynamicObject e : dynamicObjects) {
            for (IDynamicObject f : dynamicObjects) {
                e.checkCollision(f, nanoFrameTime);
            }
        }

        for (IDynamicObject e : dynamicObjects) {
            for (PhysicsObject f : staticObjects) {
                e.checkCollision(f, nanoFrameTime);
            }
        }

        for (int i = 0; i < dynamicObjects.size(); i++) {
            dynamicObjects.get(i).update(nanoFrameTime);
        }

        player.update(nanoFrameTime);
    }

    private void initializeBorders(double topBorder, double rightBorder, double bottomBorder, double leftBorder) {
        Point topLeft = new Point(leftBorder, topBorder);
        Point topRight = new Point(rightBorder, topBorder);
        Point bottomLeft = new Point(leftBorder, bottomBorder);
        Point bottomRight = new Point(rightBorder, bottomBorder);

        // add top, left, right and bottom lines
        addObject(new nl.joshuaslik.tudelft.SEM.control.gameObjects.Line(topLeft, topRight));
        addObject(new nl.joshuaslik.tudelft.SEM.control.gameObjects.Line(topLeft, bottomLeft));
        addObject(new nl.joshuaslik.tudelft.SEM.control.gameObjects.Line(topRight, bottomRight));
        addObject(new nl.joshuaslik.tudelft.SEM.control.gameObjects.Line(bottomLeft, bottomRight));

        setGameBounds(topBorder, rightBorder, bottomBorder, leftBorder);
    }

    private void initializePlayer(Scene scene) {
        //draw player
        Image playerImage = null;
        try {
            playerImage = new Image(getClass().getResource("/data/gui/img/penguin.png").openStream(), 100, 100, true, true);
            assert (playerImage != null);
        }
        catch (IOException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, "Couldn't load player image", ex);
        }
        ImageView playrImg = new ImageView(playerImage);
        playrImg.setX((rightBorder - leftBorder) / 2.0);
        playrImg.setY(bottomBorder - playerImage.getHeight());

        draw.drawOnScreen(playrImg);

        //listen to player controls
        Keyboard kb = new Keyboard(scene);
        kb.addListeners();

        player = new Player(playrImg, kb);
        player.setIGameObjects((IGameObjects) this);
    }

    private void initializeLevel(int level) {
        ArrayList<Bubble> bubbles = Levels.getLevel(level);
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
        allObjects.add(object);
    }

    /**
     * Get the closest intersection point to the given point which is not equal
     * to the given Physics object.
     *
     * @param point the point.
     * @param pObj the Physics Object.
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
                    if (e instanceof IDynamicObject) {
                        IDynamicObject d = (IDynamicObject) e;
                        ip.setSpeedVec(d.getSpeedVector());
                    }
                }
            }
        }
        return ip;
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

    private void addBufferedDynamicObjects() {
        for (IDynamicObject object : addDynamicBuffer) {
            object.setIGameObjects((IGameObjects) this);

            dynamicObjects.add(object);
            allObjects.add(object);

            if (object instanceof Bubble) {
                ++bubbleCount;
            }

            // let the dynamic object be drawn in the game view
            draw.drawOnScreen(object.getNode());
        }
        addDynamicBuffer.clear();
    }

    private void removeBufferedDynamicObjects() {
        for (IDynamicObject object : removeDynamicBuffer) {
            if (object instanceof Bubble && dynamicObjects.contains(object)) {
                score += 10;
                --bubbleCount;
            }

            dynamicObjects.remove(object);
            allObjects.remove(object);

            // remove the dynamic object from the view
            draw.removeFromScreen(object.getNode());
        }
        removeDynamicBuffer.clear();
    }

    /**
     * Get all objects which are currently in the game (except for
     * player/projectile).
     *
     * @return all objects which are currently in the game.
     */
    public ArrayList<PhysicsObject> getAllObjects() {
        return allObjects;
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

    public boolean allBubblesDestroyed() {
        return bubbleCount == 0;
    }

    @Override
    public boolean hasProjectile() {
        return hasProjectile;
    }

    @Override
    public void prepareUpdate(long nanoFrameTime) {

    }

    @Override
    public void playerDied() {
        draw.playerDied();
    }

    @Override
    public void addProjectile(Projectile projectile) {
        projectile.setIGameObjects((IGameObjects) this);
        this.projectile = projectile;
        hasProjectile = true;
        draw.drawOnScreen(projectile.getNode());
    }

    @Override
    public void removeProjectile() {
        draw.removeFromScreen(projectile.getNode());
        this.projectile = null;
        hasProjectile = false;
    }
}
