/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects;

/**
 * This interface defines the methods which should be implemented by the circle
 * object class.
 *
 * @author faris
 */
public interface ICircleViewObject extends IViewObject {

    /**
     * Set the x coordinate of the center of the circle.
     *
     * @param xCoordinate the x coordinate.
     */
    public void setCenterX(double xCoordinate);

    /**
     * Set the y coordinate of the center of the circle.
     *
     * @param yCoordinate the y coordinate.
     */
    public void setCenterY(double yCoordinate);

    /**
     * Set the radius of the circle.
     *
     * @param radius the radius.
     */
    public void setRadius(double radius);

    /**
     * @return the x coordinate of the center of the circle.
     */
    public double getCenterX();

    /**
     * @return the y coordinate of the center of the circle.
     */
    public double getCenterY();

    /**
     * @return the radius of the circle.
     */
    public double getRadius();

    /**
     * Set the bounds outside of which the circle is not allowed to move.
     *
     * @param minX minimum x coordinate value.
     * @param minY minimum y coordinate value.
     * @param maxX maximum x coordinate value.
     * @param maxY maximum y coordinate value.
     */
    public void setBounds(double minX, double minY, double maxX, double maxY);
}
