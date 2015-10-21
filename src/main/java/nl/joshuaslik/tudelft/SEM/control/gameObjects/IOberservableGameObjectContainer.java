/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import nl.joshuaslik.tudelft.SEM.utility.IObserver;

/**
 *
 * @author Faris
 */
public interface IOberservableGameObjectContainer {
    public void addObserver(IObserver observer);
}
