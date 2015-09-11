/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import javafx.scene.Node;
import nl.joshuaslik.tudelft.SEM.model.container.IntersectionPoint;
import nl.joshuaslik.tudelft.SEM.model.container.Point;

/**
 * Object which has physics (you can collide with it).
 *
 * @author faris
 */
public interface PhysicsObject {
	
	/**
	 * Get the node of the object.
	 *
	 * @return the node.
	 */
	public Node getNode();
	
	/**
	 * Get the closest point of this object to point p.
	 *
	 * @param p point to get closest location to.
	 * @return closest point on the object to point p.
	 */
	public IntersectionPoint getClosestIntersection(final Point p);
	
}
