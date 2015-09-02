/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.control;

import app.model.container.IntersectionPoint;
import app.model.container.Point;
import java.util.ArrayList;

/**
 *
 * @author faris
 */
public class CurrentSceneObjects {
	
	private static ArrayList<PhysicsObject> objects = new ArrayList<>();

	public static void addObject(PhysicsObject object) {
		objects.add(object);
	}
	
	public static void removeObject(PhysicsObject object) {
		objects.remove(object);
	}
	
	public static IntersectionPoint getClosestPoint(Point p) {
		double dist = Double.MAX_VALUE;
		IntersectionPoint ip = null;
		for(PhysicsObject e : objects) {
			IntersectionPoint tempIP = e.getClosestIntersection(p);
			if(tempIP.getDistance() < dist) {
				dist = tempIP.getDistance();
				ip = tempIP;
			}
		}
		return ip;
	}
}
