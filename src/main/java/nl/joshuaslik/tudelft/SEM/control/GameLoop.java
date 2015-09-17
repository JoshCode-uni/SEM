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

    /**
     * Get time of the last frame refresh.
     *
     * @return the time.
     */
    public long getTime() {
        return time;
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
     * Get the game controller class.
     *
     * @return the game controller class.
     */
    public GameController getViewController() {
        return gameController;
    }

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
