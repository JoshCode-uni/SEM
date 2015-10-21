/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.utility;

/**
 *
 * @author Faris
 * @param <EVENT> an event enum.
 */
public interface IObservable<OBSERVED, EVENT> {
    public void addObserver(IObserver o);
    public void deleteObserver(IObserver o);
    public void notifyObservers(EVENT arg);
}
