/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.utility;

/**
 *
 * @author Faris
 * @param <OBSERVED> the observed class.
 * @param <EVENT> an event enum.
 */
public interface IObserver<OBSERVED, EVENT> {
    public void update(OBSERVED observed, EVENT event);
    public boolean sameClass(Class observed);
}
