/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import nl.joshuaslik.tudelft.SEM.model.container.IntersectionPoint;
import nl.joshuaslik.tudelft.SEM.model.container.Point;

/**
 * Defines the methods needed by an object which is intersectable.
 *
 * @author faris
 */
interface IIntersectable extends IPhysicsObject {

    /**
     * Get the closest point of this object to point p.
     *
     * @param p point to get closest location to.
     * @return closest point on the object to point p.
     */
    public IntersectionPoint getClosestIntersection(final Point p);

}
