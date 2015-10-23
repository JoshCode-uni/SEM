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
 * A pickup which grants the player an extra life.
 *
 * @author faris
 */
public class Life extends AbstractPickup implements IObservable<Life, PickupEventType> {

    private final ArrayList<IObserver<Life, PickupEventType>> observers = new ArrayList<>();

    /**
     * Create a life.
     *
     * @param gameObjects the gameobjects.
     * @param xCoordinate the x coordinate.
     * @param yCoordinate the y coordinate.
     */
    public Life(IGameObjects gameObjects, double xCoordinate, double yCoordinate) {
        super(gameObjects, Life.class.getResourceAsStream("/data/gui/img/life2.png"), 50,
                48.5, xCoordinate, yCoordinate);
    }

    /**
     * Handle the collision with the player.
     */
    @Override
    public void handlePlayerCollision() {
        destroy();
        getGameObjects().addLife();
        notifyObservers(PickupEventType.LIFE);
    }

    /**
     * Add an observer to this observable object.
     *
     * @param o an observer.
     */
    @Override
    public void addObserver(IObserver o) {
        if (o.sameClass(Life.class)) {
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
        if (o.sameClass(Life.class)) {
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
