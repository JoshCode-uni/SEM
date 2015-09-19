/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects;

/**
 * This interface defines the methods which should be implemented by the image
 * object class.
 * @author faris
 */
public interface IImageViewObject extends IViewObject  {
    
    /**
     * @param xCoordinate set the x coordinate of this image.
     */
    public void setX(double xCoordinate);

    /**
     * @param yCoordinate set the y coordinate of this image.
     */
    public void setY(double yCoordinate);

    /**
     * Check if this image intersects with the given view object.
     * @param viewObject a view object.
     * @return if the image intersects with the view object.
     */
    public boolean intersects(IViewObject viewObject);
    
    /**
     * Get the x coordinate of the start position of this image.
     * @return x coordinate.
     */
    public double getStartX();

    /**
     * Get the y coordinate of the start position of this image.
     * @return y coordinate.
     */
    public double getStartY();

    /**
     * Get the x coordinate of the end position of this image.
     * @return x coordinate.
     */
    public double getEndX();

    /**
     * Get the y coordinate of the end position of this image.
     * @return y coordinate.
     */
    public double getEndY();
    
    /**
     * Get the height of this image.
     * @return the height.
     */
    public double getHeight();
    
    /**
     * Set the scale of this image.
     * @param scale the scale.
     */
    public void setScaleX(double scale);
}