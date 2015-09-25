/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import nl.joshuaslik.tudelft.SEM.model.container.IntersectionPoint;
import nl.joshuaslik.tudelft.SEM.model.container.Point;

/**
 *
 * @author faris
 */
public interface IIntersectable {

    /**
     * Get the closest point of this object to point p.
     *
     * @param p point to get closest location to.
     * @return closest point on the object to point p.
     */
    public IntersectionPoint getClosestIntersection(final Point p);
    
}
