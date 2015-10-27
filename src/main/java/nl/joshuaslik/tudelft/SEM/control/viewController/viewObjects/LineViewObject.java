/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import nl.joshuaslik.tudelft.SEM.control.viewController.GameViewController;

/**
 * This class can be used to add a line to the view and update the properties of that line.
 *
 * @author faris
 */
public class LineViewObject extends AbstractViewObject implements ILineViewObject {

    private final Line line;

    /**
     * Create a line.
     *
     * @param startX x coordinate of the start position of the line.
     * @param startY y coordinate of the start position of the line.
     * @param endX x coordinate of the end position of the line.
     * @param endY y coordinate of the end position of the line.
     * @param gc a reference to the controller of the view of this line.
     */
    public LineViewObject(double startX, double startY, double endX, double endY,
            GameViewController gc) {
        super(gc);
        this.line = new Line(startX, startY, endX, endY);
        gc.drawNode(line);
    }

    /**
     * @return this node.
     */
    @Override
    protected Node getNode() {
        return line;
    }

    /**
     * Set the x coordinate of the start position of the line.
     *
     * @param xCoordinate x coordinate.
     */
    @Override
    public void setStartX(double xCoordinate) {
        line.setStartX(xCoordinate);
    }

    /**
     * Set the y coordinate of the start position of the line.
     *
     * @param yCoordinate y coordinate.
     */
    @Override
    public void setStartY(double yCoordinate) {
        line.setStartY(yCoordinate);
    }

    /**
     * Set the x coordinate of the end position of the line.
     *
     * @param xCoordinate x coordinate.
     */
    @Override
    public void setEndX(double xCoordinate) {
        line.setEndX(xCoordinate);
    }

    /**
     * Set the y coordinate of the end position of the line.
     *
     * @param yCoordinate y coordinate.
     */
    @Override
    public void setEndY(double yCoordinate) {
        line.setEndY(yCoordinate);
    }

    /**
     * Set the width of the line.
     *
     * @param width width of the line.
     */
    @Override
    public void setStrokeWidth(double width) {
        line.setStrokeWidth(width);
    }

    /**
     * Set the color of the line/
     *
     * @param red value between 0 and 1.
     * @param green value between 0 and 1.
     * @param blue value between 0 and 1.
     */
    @Override
    public void setColor(double red, double green, double blue) {
        line.setStroke(new Color(red, green, blue, 1));
    }

    /**
     * Set the opacity.
     *
     * @param opacity value between 0 and 1.
     */
    @Override
    public void setOpacity(double opacity) {
        line.setOpacity(opacity);
    }

    /**
     * @return the x coordinate of the start point.
     */
    @Override
    public double getStartX() {
        return line.getStartX();
    }

    /**
     * @return the y coordinate of the start point.
     */
    @Override
    public double getStartY() {
        return line.getStartY();
    }

    /**
     * @return the x coordinate of the end point.
     */
    @Override
    public double getEndX() {
        return line.getEndX();
    }

    /**
     * @return the y coordinate of the end point.
     */
    @Override
    public double getEndY() {
        return line.getEndY();
    }
}
