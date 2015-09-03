/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control;

import javafx.scene.Node;
import nl.joshuaslik.tudelft.SEM.model.container.IntersectionPoint;
import nl.joshuaslik.tudelft.SEM.model.container.Point;
import nl.joshuaslik.tudelft.SEM.model.container.Vector;

/**
 * @author faris
 */
public class Line implements PhysicsObject {
	
	private final javafx.scene.shape.Line fxLine;
	private final Point p1, p2;
	private final Vector dir;
	
	public Line(Point p1, Point p2) {
		fxLine = new javafx.scene.shape.Line(p1.getxPos(), p1.getyPos(), p2.getxPos(), p2.getyPos());
		this.p1 = p1;
		this.p2 = p2;
		
		dir = new Vector(p2.getxPos() - p1.getxPos(), p2.getyPos() - p1.getyPos());
	}
	
	@Override
	public Node getNode() {
		return fxLine;
	}
	
	@Override
	public IntersectionPoint getClosestIntersection(Point p) {
		Vector normal = dir.normal();
		Point intersection = normal.getIntersectionPoint(p1.translate(-p.getxPos(), -p.getyPos()), p2.translate(-p.getxPos(), -p.getyPos()));
		return new IntersectionPoint(intersection.getxPos() + p.getxPos(), intersection.getyPos() + p.getyPos(), normal, intersection.distanceTo(new Point(0, 0)));
	}
	
}
