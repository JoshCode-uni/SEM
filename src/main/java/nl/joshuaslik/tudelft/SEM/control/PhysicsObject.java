/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control;

import javafx.scene.Node;
import nl.joshuaslik.tudelft.SEM.model.container.IntersectionPoint;
import nl.joshuaslik.tudelft.SEM.model.container.Point;

/**
 * @author faris
 */
public interface PhysicsObject {
	
	public Node getNode();
	
	public IntersectionPoint getClosestIntersection(Point p);
	
}
