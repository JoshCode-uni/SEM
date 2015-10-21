/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import nl.joshuaslik.tudelft.SEM.utility.IObserver;

/**
 * Interface for gameobjects to give access to the addObserver method.
 * @author Faris
 */
public interface IOberservableGameObjectContainer {
    
    /**
     * Add an observer to all game objects and yet to be made game objects.
     * @param observer an observer.
     */
    public void addObserver(IObserver observer);
}
