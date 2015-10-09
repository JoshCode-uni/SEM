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
	 * @return true if left arrow is pressed, otherwise false.
	 */
	public boolean isMoveLeft();
	
	/**
	 * Check if right arrow is pressed.
	 *
	 * @return true if right arraow is pressed, otherwise false.
	 */
	public boolean isMoveRight();
	
	/**
	 * Check if spacebar is pressed.
	 *
	 * @return true if spacebar is pressed, otherwise false.
	 */
	public boolean isShoot();
}
