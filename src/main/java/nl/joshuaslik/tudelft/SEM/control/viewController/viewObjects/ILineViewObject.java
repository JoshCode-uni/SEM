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
public interface ILineViewObject extends IViewObject  {

    public void setStartX(double xCoordinate);

    public void setStartY(double yCoordinate);
    
    public void setEndX(double xCoordinate);

    public void setEndY(double yCoordinate);
    
    public double getStartX();
    public double getStartY();
    public double getEndX();
    public double getEndY();
    
    public void setStrokeWidth(double width);
    public void setColor(double red, double green, double blue);
    public void setOpacity(double opacity);
}
