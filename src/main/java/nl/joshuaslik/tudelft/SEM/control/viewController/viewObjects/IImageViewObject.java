/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects;

/**
 *
 * @author faris
 */
public interface IImageViewObject extends IViewObject  {
    public void setX(double xCoordinate);
    public void setY(double yCoordinate);
    public boolean intersects(IViewObject viewObject);
    
    public double getStartX();
    public double getStartY();
    public double getEndX();
    public double getEndY();
    
    public double getHeight();
    
    public void setScaleX(double scale);
}
