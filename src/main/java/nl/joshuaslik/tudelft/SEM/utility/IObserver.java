/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.utility;

/**
 * An observer which can observe an IObservable object.
 * @author Faris
 * @param <OBSERVED> the observed class.
 * @param <EVENT> the event enum class of the observed class.
 */
public interface IObserver<OBSERVED, EVENT> {
    
    /**
     * The update method. Used to notify the observer of an event.
     * @param observed the observable object which triggered the event.
     * @param event the type of event.
     */
    public void update(OBSERVED observed, EVENT event);
    
    /**
     * Checks if the given class is the class which this observer can observe.
     * @param observed a class.
     * @return if the given class is the class which this observer can observe.
     */
    public boolean sameClass(Class observed);
}
