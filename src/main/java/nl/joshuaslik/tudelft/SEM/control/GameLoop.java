/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.GameObjects;
import nl.joshuaslik.tudelft.SEM.control.viewController.GameController;
import nl.joshuaslik.tudelft.SEM.control.viewController.Keyboard;
import nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects.ICircleViewObject;
import nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects.IImageViewObject;
import nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects.ILineViewObject;
import nl.joshuaslik.tudelft.SEM.utility.GameLog;

/**
 * The gameloop which updated all objects after each frame.
 *
 * @author faris
 */
public class GameLoop extends AnimationTimer implements IDraw {

    private final Keyboard kb;
    private GameController gameController;
    private final GameObjects gameObjects;
    private long time = 0;

    /**
     *
     * @param gameController the controller of the game view.
     * @param currentLevel the current level.
     * @param top y value of the top border.
     * @param right x value of the right border.
     * @param bottom y value of the bottom border.
     * @param left x value of the left border.
     * @param scene the scene of the game (to add a keylistener to).
     */
    public GameLoop(GameController gameController, int currentLevel, double top,
            double right, double bottom, double left, Scene scene) {
        this.gameController = gameController;
        kb = new Keyboard(scene);
        gameObjects = new GameObjects((IDraw) this, currentLevel, top, right,
                bottom, left, kb);
    }

    @Override
    public void start() {
        super.start();
        kb.addListeners();
    }

    @Override
    public void stop() {
        super.stop();
        kb.removeListeners();
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
            GameLog.addErrorLog("Exception in game loop");
            GameLog.addErrorLog(ex.getMessage());
            Logger.getLogger(GameLoop.class.getName()).log(Level.SEVERE, "Exception in game loop", ex);
        }
    }

    /**
     * Set the view controller class.
     *
     * @param gameController the view controller class.
     */
    public void setViewController(GameController gameController) {
        this.gameController = gameController;
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

    /**
     * Create a circle in the view.
     * @param centerX the x coordinate of the center of the circle.
     * @param centerY the y coordinate of the center of the circle.
     * @param radius the radius of the circle.
     * @return the interface of the circle view object.
     */
    @Override
    public ICircleViewObject makeCircle(double centerX, double centerY,
            double radius) {
        return gameController.makeCircle(centerX, centerY, radius);
    }

     /**
     * Create an image in the view.
     * @param is the input stream of the image.
     * @param height the height of the image.
     * @param width the width of the image.
     * @return the interface of the image view object.
     */
    @Override
    public IImageViewObject makeImage(InputStream is, double width,
            double height) {
        return gameController.makeImage(is, width, height);
    }

    /**
     * Create a line in the view.
     * @param startX the x coordinate of the start point of the line.
     * @param startY the y coordinate of the start point of the line.
     * @param endX the x coordinate of the end point of the line.
     * @param endY the y coordinate of the end point of the line.
     * @return the interface of the line view object.
     */
    @Override
    public ILineViewObject makeLine(double startX, double startY, double endX,
            double endY) {
        return gameController.makeLine(startX, startY, endX, endY);
    }
    
    @Override
    public void addLife() {
        gameController.addLife();
    }
}
