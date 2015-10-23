/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.model.container;

/**
 * A class used to store a vector and do calculations with it.
 *
 * @author faris
 */
public class Vector {

    private final double x, y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculate intersection point of 2 vectors. (null if no intersection)
     *
     * @param from
     * @param to
     * @return
     */
    public Point getIntersectionPoint(Point from, Point to) {
        Vector vec2 = new Vector(to.getxPos() - from.getxPos(), to.getyPos() - from.getyPos());
        if (this.y != 0) {
            double divider = vec2.x - this.x / this.y * vec2.y;
            if (Math.abs(divider) < 0.01) {
                return null;
            }
            double a = -(from.getxPos() - this.x / this.y * from.getyPos()) / divider;
            return new Point(vec2.x * a + from.getxPos(), vec2.y * a + from.getyPos());
        } else if (vec2.y != 0) {
            double divider = this.x - vec2.x / vec2.y * this.y;
            if (Math.abs(divider) < 0.01) {
                return null;
            }
            double a = (from.getxPos() - vec2.x / vec2.y * from.getyPos()) / divider;
            return new Point(this.x * a, this.y * a);
        }
        return null;
    }

    /**
     * Check if point p is in front of the vector (== check if the vector will have positive length
     * if it intersects with point p). This method doesn't say anything about 'if' the vector
     * actually hits the point if it returns true. The only thing which you can be sure of is that
     * the point is not hit when the method returns false.
     *
     * @param from
     * @param intersect
     * @return
     */
    public boolean positiveDirection(Point from, Point intersect) {
        double dist = from.distanceTo(intersect);
        Point moveDelta = new Point(from.getxPos() + 0.001 * this.x, from.getyPos() + 0.001 * this.y);
        return moveDelta.distanceTo(intersect) < dist;
    }

    /**
     * +1 if positive direction, otherwise -1.
     *
     * @return
     */
    public double getXdirection() {
        return x < 0 ? -1 : 1;
    }

    /**
     * Get a normal of this vector.
     *
     * @return a normal.
     */
    public Vector normal() {
        return new Vector(y, -x);
    }

    /**
     * Get the x value.
     *
     * @return the x value.
     */
    public double getX() {
        return x;
    }

    /**
     * Get the y value.
     *
     * @return the y value.
     */
    public double getY() {
        return y;
    }

    /**
     * Create a String representation of this class.
     *
     * @return String representation of this class.
     */
    @Override
    public String toString() {
        return "Vector{" + "x=" + x + ", y=" + y + '}';
    }

    /**
     * Check if this vector equals the object obj.
     *
     * @param obj Object to compare this vector to.
     * @return if this vector and obj are equal.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vector other = (Vector) obj;
        if (Double.doubleToLongBits(this.x) != Double.doubleToLongBits(other.x)) {
            return false;
        }
        if (Double.doubleToLongBits(this.y) != Double.doubleToLongBits(other.y)) {
            return false;
        }
        return true;
    }
    
    /**
     * Get a clone of this vector.
     * @return a clone of this vector.
     */
    @Override
    public Vector clone() {
        Vector clone = new Vector(x, y);
        return clone;
    }
}
