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
 *
 * @author faris
 */
public class CircleViewObject extends AbstractViewObject implements ICircleViewObject {

    private final Circle circle;
    private boolean bounds = false;
    private double minX, minY, maxX, maxY;

    public CircleViewObject(double centerX, double centerY, double radius, 
            GameController gc) {
        super(gc);
        this.circle = new Circle(centerX, centerY, radius);
        gc.drawNode(circle);
    }
    
    @Override
    protected Node getNode() {
        return circle;
    }

    @Override
    public void setCenterX(double xCoordinate) {
        circle.setCenterX(checkXBounds(xCoordinate));
    }

    @Override
    public void setCenterY(double yCoordinate) {
        circle.setCenterY(checkYBounds(yCoordinate));
    }

    @Override
    public void setRadius(double radius) {
        circle.setRadius(radius);
    }

    @Override
    public double getCenterX() {
        return circle.getCenterX();
    }

    @Override
    public double getCenterY() {
        return circle.getCenterY();
    }

    @Override
    public double getRadius() {
        return circle.getRadius();
    }

    @Override
    public void setBounds(double minX, double minY, double maxX, double maxY) {
        bounds = true;
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
    }
    
    private double checkXBounds(double xCoordinate){
        if(!bounds)
            return xCoordinate;
        if(xCoordinate > maxX)
            return maxX;
        else if(xCoordinate < minX)
            return minX;
        else
            return xCoordinate;
    }
    
    private double checkYBounds(double yCoordinate){
        if(!bounds)
            return yCoordinate;
        if(yCoordinate > maxY)
            return maxY;
        else if(yCoordinate < minY)
            return minY;
        else
            return yCoordinate;
    }
}
