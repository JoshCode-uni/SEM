/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.player;

import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.IDecorator;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.IModifier;

/**
 *
 * @author faris
 * @param <T>
 */
public abstract class AbstractPlayerModifierDecorator<T extends IPlayerModifier> implements IPlayerModifier, IDecorator<T> {

    private IPlayerModifier child;
    
//    @Override
//    public double getMoveSpeedMultiplier() {
//        double res = child.getProjectileSpeedMultiplier();
//        System.out.println("return res = " + res);
//        return res;
//    }

//    @Override
//    public double getProjectileSpeedMultiplier() {
//        return child.getProjectileSpeedMultiplier();
//    }
//
//    @Override
//    public int getProjectileSpikeDelay() {
//        return child.getProjectileSpikeDelay();
//    }
    
    @Override
    public T decorate(IModifier mod) {
        child = (IPlayerModifier) mod;
        return (T) this;
    }

    public IPlayerModifier getChild() {
        return child;
    }
}
