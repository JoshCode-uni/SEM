/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.viewController;

/**
 * @author faris
 */
public interface IKeyboard {

    /**
     * Check if left arrow is pressed.
     *
     * @param p2 if this is called by player 2.
     * @return true if left arrow is pressed, otherwise false.
     */
    boolean isMoveLeft(boolean p2);

    /**
     * Check if right arrow is pressed.
     *
     * @param p2 if this is called by player 2.
     * @return true if right arraow is pressed, otherwise false.
     */
    boolean isMoveRight(boolean p2);

    /**
     * Check if spacebar is pressed.
     *
     * @param p2 if this is called by player 2.
     * @return true if spacebar is pressed, otherwise false.
     */
    boolean isShoot(boolean p2);
}
