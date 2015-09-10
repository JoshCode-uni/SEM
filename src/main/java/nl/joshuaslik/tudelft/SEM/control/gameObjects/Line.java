/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import javafx.scene.Node;
import nl.joshuaslik.tudelft.SEM.model.container.IntersectionPoint;
import nl.joshuaslik.tudelft.SEM.model.container.Point;
import nl.joshuaslik.tudelft.SEM.model.container.Vector;

/**
 * @author faris
 */
public class Line implements PhysicsObject {

	protected final javafx.scene.shape.Line fxLine;
	private Point p1, p2;
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
	public IntersectionPoint getClosestIntersection(final Point p) {
		Vector normal = dir.normal();
		Point intersection = normal.getIntersectionPoint(p1.translate(-p.getxPos(), -p.getyPos()), p2.translate(-p.getxPos(), -p.getyPos()));

		if (intersection == null) {
			return new IntersectionPoint(Double.MAX_VALUE, Double.MAX_VALUE, new Vector(1, 1), Double.MAX_VALUE);
		}

		double distance = intersection.distanceTo(new Point(0, 0));
		intersection = new Point(intersection.getxPos() + p.getxPos(), intersection.getyPos() + p.getyPos());

		Point smallestXpoint = getSmallestXpoint();
		Point smallestYpoint = getSmallestYpoint();
		Point largestXpoint = getLargestXpoint();
		Point largestYpoint = getLargestYpoint();

		// assure the intersection point is on the line
		if (intersection.getxPos() < smallestXpoint.getxPos()) {
			intersection = smallestXpoint;
		} else if (intersection.getxPos() > largestXpoint.getxPos()) {
			intersection = largestXpoint;
		} else if (intersection.getyPos() < smallestYpoint.getyPos()) {
			intersection = smallestYpoint;
		} else if (intersection.getyPos() > largestYpoint.getyPos()) {
			intersection = largestYpoint;
		}

		return new IntersectionPoint(intersection.getxPos(), intersection.getyPos(), normal, distance);
	}

	private Point getSmallestYpoint() {
		return p1.getyPos() < p2.getyPos() ? p1 : p2;
	}

	private Point getLargestYpoint() {
		return p1.getyPos() < p2.getyPos() ? p2 : p1;
	}

	private Point getSmallestXpoint() {
		return p1.getxPos() < p2.getxPos() ? p1 : p2;
	}

	private Point getLargestXpoint() {
		return p1.getxPos() < p2.getxPos() ? p2 : p1;
	}

	/**
	 * Recalculated p1 and p2.
	 */
	public void updatePoints() {
		p1 = new Point(fxLine.getStartX(), fxLine.getStartY());
		p2 = new Point(fxLine.getEndX(), fxLine.getEndY());
	}
}
