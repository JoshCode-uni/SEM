/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects;

/**
 * This interface defines the methods which should be implemented by the image object class.
 *
 * @author faris
 */
public interface IImageViewObject extends IViewObject {

    /**
     * @param xCoordinate set the x coordinate of this image.
     */
    void setX(double xCoordinate);

    /**
     * @param yCoordinate set the y coordinate of this image.
     */
    void setY(double yCoordinate);

    /**
     * Check if this image intersects with the given view object.
     *
     * @param viewObject a view object.
     * @return if the image intersects with the view object.
     */
    boolean intersects(IViewObject viewObject);

    /**
     * Get the x coordinate of the start position of this image.
     *
     * @return x coordinate.
     */
    double getStartX();

    /**
     * Get the y coordinate of the start position of this image.
     *
     * @return y coordinate.
     */
    double getStartY();

    /**
     * Get the x coordinate of the end position of this image.
     *
     * @return x coordinate.
     */
    double getEndX();

    /**
     * Get the y coordinate of the end position of this image.
     *
     * @return y coordinate.
     */
    double getEndY();

    /**
     * Get the height of this image.
     *
     * @return the height.
     */
    double getHeight();

    /**
     * Set the scale of this image.
     *
     * @param scale the scale.
     */
    void setScaleX(double scale);

    /**
     * Set the bounds outside of which the circle is not allowed to move.
     *
     * @param minX minimum x coordinate value.
     * @param minY minimum y coordinate value.
     * @param maxX maximum x coordinate value.
     * @param maxY maximum y coordinate value.
     */
    void setBounds(double minX, double minY, double maxX, double maxY);

    /**
     * Set the hsb.
     *
     * @param hue hue.
     * @param saturation saturation.
     * @param brightness brightness.
     */
    void adjustHSB(double hue, double saturation, double brightness);
}
