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
public interface ICircleViewObject extends IViewObject {
    public void setCenterX(double xCoordinate);
    public void setCenterY(double yCoordinate);
    public void setRadius(double radius);
    public double getCenterX();
    public double getCenterY();
    public double getRadius();
    public void setBounds(double minX, double minY, double maxX, double maxY);
}
