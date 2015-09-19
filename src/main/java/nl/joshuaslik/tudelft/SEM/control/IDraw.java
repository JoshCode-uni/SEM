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
    
    public ICircleViewObject makeCircle(double centerX, double centerY, 
            double radius);
    
    public IImageViewObject makeImage(InputStream is, double width, double height);
    
    public ILineViewObject makeLine(double startX, double startY, double endX,
            double endY);
}
