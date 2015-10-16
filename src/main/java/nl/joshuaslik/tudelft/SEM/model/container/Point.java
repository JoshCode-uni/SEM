/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.model.container;

/**
 * A container class which stores a point (x/y coordinate).
 *
 * @author faris
 */
public class Point {

    private final double xPos;
    private final double yPos;

    /**
     * Create a point.
     *
     * @param xPos X position of this point.
     * @param yPos Y position of this point.
     */
    public Point(double xPos, double yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    /**
     * Distance from this point to point 2.
     *
     * @param p2 point 2.
     * @return the distance between this and point 2.
     */
    public double distanceTo(Point p2) {
        return Math.sqrt((xPos - p2.xPos) * (xPos - p2.xPos) + (yPos - p2.yPos) * (yPos - p2.yPos));
    }

    /**
     * Get the point which is this point translated of the distance deltaX and
     * deltaY.
     *
     * @param deltaX delta X.
     * @param deltaY delta Y
     * @return the translated point
     */
    public Point translate(double deltaX, double deltaY) {
        return new Point(xPos + deltaX, yPos + deltaY);
    }

    /**
     * Translate by point p.
     *
     * @param p point
     * @return the new point
     */
    public Point translate(Point p) {
        return translate(p.xPos, p.yPos);
    }

    /**
     * Get the x position of this point.
     *
     * @return the x position of this point.
     */
    public double getxPos() {
        return xPos;
    }

    /**
     * Get the y position of this point.
     *
     * @return the y position of this point.
     */
    public double getyPos() {
        return yPos;
    }

    /**
     * Represent this point as a String.
     *
     * @return String representation of this point.
     */
    @Override
    public String toString() {
        return "Position: (x: " + xPos + ", y: " + yPos + ")";
    }

    /**
     * Check if this and obj are equal.
     *
     * @param obj the object to compare to.
     * @return if the objects are equal.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Point other = (Point) obj;
        if (Double.doubleToLongBits(this.xPos) != Double.doubleToLongBits(other.xPos)) {
            return false;
        }
        if (Double.doubleToLongBits(this.yPos) != Double.doubleToLongBits(other.yPos)) {
            return false;
        }
        return true;
    }
}
