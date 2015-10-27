/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.utility;

/**
 * An observable which can be observed by an IObserver object.
 *
 * @author Faris
 * @param <OBSERVED> the observed class.
 * @param <EVENT> the event enum class of the observed class.
 */
public interface IObservable<OBSERVED, EVENT> {

    /**
     * Add an observer to this observable object.
     *
     * @param o an observer.
     */
    public void addObserver(IObserver o);

    /**
     * Delete an observer from this observable object.
     *
     * @param o an observer.
     */
    public void deleteObserver(IObserver o);

    /**
     * Notify the observers of an event of this observable object.
     *
     * @param event an event.
     */
    public void notifyObservers(EVENT event);
}
