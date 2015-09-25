/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control;

import java.io.InputStream;
import nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects.ICircleViewObject;
import nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects.IImageViewObject;
import nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects.ILineViewObject;

/**
 * Drawing interface which gives a limited set of methods to the game container
 * classes, to indirectly draw things on the screen.
 * @author faris
 */
public interface IDraw {

    /**
     * Handle the event: player died.
     */
    public void playerDied();
    
    /**
     * Create a circle in the view.
     * @param centerX the x coordinate of the center of the circle.
     * @param centerY the y coordinate of the center of the circle.
     * @param radius the radius of the circle.
     * @return the interface of the circle view object.
     */
    public ICircleViewObject makeCircle(double centerX, double centerY, 
            double radius);
    
     /**
     * Create an image in the view.
     * @param is the input stream of the image.
     * @param height the height of the image.
     * @param width the width of the image.
     * @return the interface of the image view object.
     */
    public IImageViewObject makeImage(InputStream is, double width, double height);
    
    /**
     * Create a line in the view.
     * @param startX the x coordinate of the start point of the line.
     * @param startY the y coordinate of the start point of the line.
     * @param endX the x coordinate of the end point of the line.
     * @param endY the y coordinate of the end point of the line.
     * @return the interface of the line view object.
     */
    public ILineViewObject makeLine(double startX, double startY, double endX,
            double endY);
    
    public void addLife();
}
