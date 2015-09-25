/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup;

import java.io.InputStream;

/**
 *
 * @author faris
 * @param <T>
 */
public interface IDecorator<T extends IModifier> {
    
    /**
     * Variable mod should be of the correct type. For example, if T is a Player
     * modifier, mod should be an instance of IPlayerModifier
     * @param mod should be of the correct type.
     * @return 
     */
    public T decorate(IModifier mod);
    
//    public abstract InputStream getImageStream();
//    public abstract double getImageHeight();
//    public abstract double getImageWidth();
}
