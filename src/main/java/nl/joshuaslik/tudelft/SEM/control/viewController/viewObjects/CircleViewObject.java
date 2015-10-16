/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects;

import javafx.scene.Node;
import javafx.scene.shape.Circle;
import nl.joshuaslik.tudelft.SEM.control.viewController.GameController;

/**
 * This class can be used to add a circle to the view and update the properties of that circle.
 *
 * @author faris
 */
public class CircleViewObject extends AbstractViewObject implements ICircleViewObject {

    private final Circle circle;
    private boolean bounds = false;
    private double minX, minY, maxX, maxY;

    /**
     * Create a circle.
     *
     * @param centerX the x coordinate of the center of the circle.
     * @param centerY the y coordinate of the center of the circle.
     * @param radius the radius of the circle.
     * @param gc a reference to the view in which this circle should be drawn.
     */
    public CircleViewObject(double centerX, double centerY, double radius, GameController gc) {
        super(gc);
        this.circle = new Circle(centerX, centerY, radius);
        gc.drawNode(circle);
    }

    /**
     * @return the node of this circle.
     */
    @Override
    protected Node getNode() {
        return circle;
    }

    /**
     * Set the x coordinate of the center of the circle.
     *
     * @param xCoordinate the x coordinate.
     */
    @Override
    public void setCenterX(double xCoordinate) {
        circle.setCenterX(checkXBounds(xCoordinate));
    }

    /**
     * Set the y coordinate of the center of the circle.
     *
     * @param yCoordinate the y coordinate.
     */
    @Override
    public void setCenterY(double yCoordinate) {
        circle.setCenterY(checkYBounds(yCoordinate));
    }

    /**
     * Set the radius of the circle.
     *
     * @param radius the radius.
     */
    @Override
    public void setRadius(double radius) {
        circle.setRadius(radius);
    }

    /**
     * @return the x coordinate of the center of the circle.
     */
    @Override
    public double getCenterX() {
        return circle.getCenterX();
    }

    /**
     * @return the y coordinate of the center of the circle.
     */
    @Override
    public double getCenterY() {
        return circle.getCenterY();
    }

    /**
     * @return the radius of the circle.
     */
    @Override
    public double getRadius() {
        return circle.getRadius();
    }

    /**
     * Set the bounds outside of which the circle is not allowed to move.
     *
     * @param minX minimum x coordinate value.
     * @param minY minimum y coordinate value.
     * @param maxX maximum x coordinate value.
     * @param maxY maximum y coordinate value.
     */
    @Override
    public void setBounds(double minX, double minY, double maxX, double maxY) {
        bounds = true;
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
    }

    /**
     * Check if the x coordinate is outside of the set bounds.
     *
     * @param xCoordinate the x coordinate to check.
     * @return the same as the parameter if inside, or on the bounds if otherwise outside of the
     * bounds
     */
    private double checkXBounds(double xCoordinate) {
        if (!bounds) {
            return xCoordinate;
        }
        if (xCoordinate > maxX) {
            return maxX;
        } else if (xCoordinate < minX) {
            return minX;
        } else {
            return xCoordinate;
        }
    }

    /**
     * Check if the y coordinate is outside of the set bounds.
     *
     * @param yCoordinate the y coordinate to check.
     * @return the same as the parameter if inside, or on the bounds if otherwise outside of the
     * bounds
     */
    private double checkYBounds(double yCoordinate) {
        if (!bounds) {
            return yCoordinate;
        }
        if (yCoordinate > maxY) {
            return maxY;
        } else if (yCoordinate < minY) {
            return minY;
        } else {
            return yCoordinate;
        }
    }
}
