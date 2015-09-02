/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.control;

import app.model.container.IntersectionPoint;
import app.model.container.Point;
import javafx.scene.Node;

/**
 *
 * @author faris
 */
public interface PhysicsObject {
	
	public Node getNode();
	public IntersectionPoint getClosestIntersection(Point p);
	
}
