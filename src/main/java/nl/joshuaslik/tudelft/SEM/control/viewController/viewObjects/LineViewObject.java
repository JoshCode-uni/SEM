/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import nl.joshuaslik.tudelft.SEM.control.viewController.GameController;

/**
 *
 * @author faris
 */
public class LineViewObject extends AbstractViewObject implements ILineViewObject {

    private final Line line;

    public LineViewObject(double startX, double startY, double endX, 
            double endY, GameController gc) {
        super(gc);
        this.line = new Line(startX, startY, endX, endY);
        gc.drawNode(line);
    }
    
    @Override
    protected Node getNode() {
        return line;
    }

    @Override
    public void setStartX(double xCoordinate) {
        line.setStartX(xCoordinate);
    }

    @Override
    public void setStartY(double yCoordinate) {
        line.setStartY(yCoordinate);
    }

    @Override
    public void setEndX(double xCoordinate) {
        line.setEndX(xCoordinate);
    }

    @Override
    public void setEndY(double yCoordinate) {
        line.setEndY(yCoordinate);
    }

    @Override
    public void setStrokeWidth(double width) {
        line.setStrokeWidth(width);
    }

    @Override
    public void setColor(double red, double green, double blue) {
        line.setStroke(new Color(red, green, blue, 1));
    }

    @Override
    public void setOpacity(double opacity) {
        line.setOpacity(opacity);
    }

    @Override
    public double getStartX() {
        return line.getStartX();
    }

    @Override
    public double getStartY() {
        return line.getStartY();
    }

    @Override
    public double getEndX() {
        return line.getEndX();
    }

    @Override
    public double getEndY() {
        return line.getEndY();
    }
    
}
