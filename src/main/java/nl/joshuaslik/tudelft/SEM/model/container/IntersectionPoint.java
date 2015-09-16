/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.model.container;

import java.util.Objects;

/**
 * A container class which contains an intersection point.
 *
 * @author faris
 */
public class IntersectionPoint extends Point {

    private final Vector normal;
    private final double distance;
    private Vector speedVec = null;

    /**
     * Create an intersection point.
     *
     * @param xPos x position.
     * @param yPos y position.
     * @param normal normal.
     * @param distance distance.
     */
    public IntersectionPoint(double xPos, double yPos, Vector normal, double distance) {
        super(xPos, yPos);
        this.normal = normal;
        this.distance = distance;
    }

    /**
     * Get the normal.
     *
     * @return the normal.
     */
    public Vector getNormal() {
        return normal;
    }

    /**
     * Get the distance.
     *
     * @return the distance.
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Check if there is a speed vector.
     *
     * @return if there is a speed vector.
     */
    public boolean hasSpeedVec() {
        return speedVec != null;
    }

    /**
     * Get the speed vector.
     *
     * @return the speed vector.
     */
    public Vector getSpeedVec() {
        return speedVec;
    }

    /**
     * Set the speed vector.
     *
     * @param speedVec the speed vector.
     */
    public void setSpeedVec(Vector speedVec) {
        this.speedVec = speedVec;
    }

    /**
     * Check if equal to obj.
     *
     * @param obj the obj to compare to.
     * @return if equal to obj.
     */
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
