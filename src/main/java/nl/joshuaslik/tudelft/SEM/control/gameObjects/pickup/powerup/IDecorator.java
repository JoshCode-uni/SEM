/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup;

/**
 * A decorator.
 * @param <T> the type of the decorator.
 * @author faris
 */
public interface IDecorator<T> {

    /**
     * Variable mod should be of the correct type. For example, if T is a Player modifier, mod
     * should be an instance of IPlayerModifier
     *
     * @param mod should be of the correct type.
     * @return
     */
    T decorate(T mod);
}
