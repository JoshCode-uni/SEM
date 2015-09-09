/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control;

import nl.joshuaslik.tudelft.SEM.control.gameObjects.DynamicObject;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.PhysicsObject;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import nl.joshuaslik.tudelft.SEM.model.container.IntersectionPoint;
import nl.joshuaslik.tudelft.SEM.model.container.Point;

/**
 * @author faris
 */
public class GameLoop extends AnimationTimer {

	private ArrayList<PhysicsObject> allObjects = new ArrayList<>();
	private ArrayList<PhysicsObject> staticObjects = new ArrayList<>();
	private ArrayList<DynamicObject> dynamicObjects = new ArrayList<>();
	private DynamicObject player;
	private static double topBorder, rightBorder, bottomBorder, leftBorder;

	private static long time = 0;

	@Override
	public void handle(long time) {
		// update time
		long frametime = this.time != 0 ? time - this.time : 165_000_000;
		this.time = time;

//		System.out.println("framerate:  = " + 1.0 / frametime * 1_000_000_000 + "fps");

		// check if there are any collisions (for dynamic objects)
		// if so, update the direction
		// update the positions of al dynamic objects
//		for (DynamicObject e : dynamicObjects) {
//			e.prepareUpdate(frametime);
//		}
//		
//		for(int i=0; i<dynamicObjects.size(); i++) {
//			for(int j=0; j<dynamicObjects.size(); j++) {
//				dynamicObjects.get(i).checkCollision(dynamicObjects.get(j), frametime);
//			}
//			for(PhysicsObject e : staticObjects) {
//				dynamicObjects.get(i).checkCollision(e, frametime);
//			}
//		}
//		
//		for (DynamicObject e : dynamicObjects) {
//			e.update(frametime);
//		}
		player.update(frametime);
		
		for(DynamicObject e : dynamicObjects)
			e.prepareUpdate(frametime);
		
		for(DynamicObject e : dynamicObjects)
			for(DynamicObject f : dynamicObjects)
				e.checkCollision(f, frametime);
		
		for(DynamicObject e : dynamicObjects)
			for(PhysicsObject f : staticObjects)
				e.checkCollision(f, frametime);
		
		for(DynamicObject e : dynamicObjects)
			e.update(frametime);
	}

	public IntersectionPoint getClosestPoint(Point point, PhysicsObject pObj) {
		double dist = Double.MAX_VALUE;
		IntersectionPoint ip = null;
		for (PhysicsObject e : allObjects) {
			if (!pObj.equals(e)) {
				IntersectionPoint tempIP = e.getClosestIntersection(point);
				if (tempIP.getDistance() < dist) {
					dist = tempIP.getDistance();
					ip = tempIP;

					// add speed vector if the object is dynamic
					if (e instanceof DynamicObject) {
						DynamicObject d = (DynamicObject) e;
						ip.setSpeedVec(d.getSpeedVector());
					}
				}
			}
		}
		return ip;
	}
	
	public static long getTime() {
		return time;
	}

	public void addObject(PhysicsObject object) {
		staticObjects.add(object);
		allObjects.add(object);
	}

	public void addObject(DynamicObject object) {
		dynamicObjects.add(object);
		allObjects.add(object);
	}

	public void removeObject(PhysicsObject object) {
		staticObjects.remove(object);
		allObjects.remove(object);
	}

	public void removeObject(DynamicObject object) {
		dynamicObjects.remove(object);
		allObjects.remove(object);
	}
	
	public void addPlayer(DynamicObject pl){
		player = pl;
	}
	
	public void setGameBounds(double top, double right, double bottom, double left) {
		GameLoop.topBorder = top;
		GameLoop.rightBorder = right;
		GameLoop.bottomBorder = bottom;
		GameLoop.leftBorder = left;
	}

	public static double getTopBorder() {
		return topBorder;
	}

	public static double getRightBorder() {
		return rightBorder;
	}

	public static double getBottomBorder() {
		return bottomBorder;
	}

	public static double getLeftBorder() {
		return leftBorder;
	}
	
}
