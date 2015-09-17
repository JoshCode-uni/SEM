/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control;

import javafx.scene.Node;

/**
 * Drawing interface which gives a limited set of methods to the game container
 * classes, to indirectly draw things on the screen.
 * @author faris
 */
public interface IDraw {

    /**
     * Draw node n on the screen.
     * @param n the node to draw on the screen.
     */
    public void drawOnScreen(Node n);

    /**
     * Remove node n from the screen.
     * @param n the node to remove from the screen.
     */
    public void removeFromScreen(Node n);

    /**
     * Handle the event: player died.
     */
    public void playerDied();
}
