/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects;

import java.io.InputStream;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import nl.joshuaslik.tudelft.SEM.control.viewController.GameController;

/**
 * This class can be used to add a image to the view and update the properties 
 * of that image.
 * @author faris
 */
public class ImageViewObject extends AbstractViewObject implements IImageViewObject {

    private final ImageView image;

    /**
     * Create an image.
     * @param is the input stream of the image file.
     * @param width the width of the image.
     * @param height the height of the image.
     * @param gc a reference to the view in which this image will be drawn.
     */
    public ImageViewObject(InputStream is, double width, 
            double height, GameController gc) {
        super(gc);
        Image img = new Image(is, width, height, true, true);
        this.image = new ImageView(img);
        gc.drawNode(image);
    }
    
    /**
     * @return this node.
     */
    @Override
    protected Node getNode() {
        return image;
    }

    /**
     * @param xCoordinate set the x coordinate of this image.
     */
    @Override
    public void setX(double xCoordinate) {
        image.setX(xCoordinate);
    }

    /**
     * @param yCoordinate set the y coordinate of this image.
     */
    @Override
    public void setY(double yCoordinate) {
        image.setY(yCoordinate);
    }

    /**
     * Check if this image intersects with the given view object.
     * @param viewObject a view object.
     * @return if the image intersects with the view object.
     */
    @Override
    public boolean intersects(IViewObject viewObject) {
        return image.intersects(
                ((AbstractViewObject)viewObject).getNode().getLayoutBounds());
    }

    /**
     * Get the x coordinate of the start position of this image.
     * @return x coordinate.
     */
    @Override
    public double getStartX() {
        return image.getLayoutBounds().getMinX();
    }

    /**
     * Get the y coordinate of the start position of this image.
     * @return y coordinate.
     */
    @Override
    public double getStartY() {
        return image.getLayoutBounds().getMinY();
    }

    /**
     * Get the x coordinate of the end position of this image.
     * @return x coordinate.
     */
    @Override
    public double getEndX() {
        return image.getLayoutBounds().getMaxX();
    }

    /**
     * Get the y coordinate of the end position of this image.
     * @return y coordinate.
     */
    @Override
    public double getEndY() {
        return image.getLayoutBounds().getMaxY();
    }

    /**
     * Set the scale of this image.
     * @param scale the scale.
     */
    @Override
    public void setScaleX(double scale) {
        image.setScaleX(scale);
    }

    /**
     * Get the height of this image.
     * @return the height.
     */
    @Override
    public double getHeight() {
        return image.getLayoutBounds().getMaxY() - 
                image.getLayoutBounds().getMinY();
    }
}
