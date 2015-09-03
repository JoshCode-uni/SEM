/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.model.container;

import java.util.Objects;

/**
 * @author faris
 */
public class IntersectionPoint extends Point {
	
	private final Vector normal;
	private final double distance;
	
	public IntersectionPoint(double xPos, double yPos, Vector normal, double distance) {
		super(xPos, yPos);
		this.normal = normal;
		this.distance = distance;
	}
	
	public Vector getNormal() {
		return normal;
	}
	
	public double getDistance() {
		return distance;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final IntersectionPoint other = (IntersectionPoint) obj;
		if (!Objects.equals(this.normal, other.normal)) {
			return false;
		}
		return true;
	}
	
}
