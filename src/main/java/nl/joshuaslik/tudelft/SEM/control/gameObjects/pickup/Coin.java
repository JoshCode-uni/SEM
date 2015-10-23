/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup;

import java.util.ArrayList;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.IGameObjects;
import nl.joshuaslik.tudelft.SEM.utility.IObservable;
import nl.joshuaslik.tudelft.SEM.utility.IObserver;

/**
 * A coin which gives 10 points upon collision.
 *
 * @author faris
 */
public class Coin extends AbstractPickup implements IObservable<Coin, PickupEventType> {

    private final ArrayList<IObserver<Coin, PickupEventType>> observers = new ArrayList<>();

    /**
     * Create a goin.
     *
     * @param gameObjects the game objects.
     * @param xCoordinate the x coordinate.
     * @param yCoordinate the y coordinate.
     */
    public Coin(IGameObjects gameObjects, double xCoordinate, double yCoordinate) {
        super(gameObjects, Life.class.getResourceAsStream("/data/gui/img/coin.png"), 50, 50,
                xCoordinate, yCoordinate);
    }

    /**
     * Handle player collision.
     */
    @Override
    public void handlePlayerCollision() {
        destroy();
        getGameObjects().addPoints(10);
        notifyObservers(PickupEventType.COIN);
    }

    /**
     * Add an observer to this observable object.
     *
     * @param o an observer.
     */
    @Override
    public void addObserver(IObserver o) {
        if (o.sameClass(Coin.class)) {
            observers.add(o);
        }
    }

    /**
     * Delete an observer from this observable object.
     *
     * @param o an observer.
     */
    @Override
    public void deleteObserver(IObserver o) {
        if (o.sameClass(Coin.class)) {
            observers.remove(o);
        }
    }

    /**
     * Notify the observers of an event of this observable object.
     *
     * @param event an event.
     */
    @Override
    public void notifyObservers(PickupEventType event) {
        for (IObserver o : observers) {
            o.update(this, event);
        }
    }

}
