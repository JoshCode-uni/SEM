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
 *
 * @author faris
 */
public class ImageViewObject extends AbstractViewObject implements IImageViewObject {

    private final ImageView image;

    public ImageViewObject(InputStream is, double width, 
            double height, GameController gc) {
        super(gc);
        Image img = new Image(is, width, height, true, true);
        this.image = new ImageView(img);
        gc.drawNode(image);
    }
    
    @Override
    protected Node getNode() {
        return image;
    }

    @Override
    public void setX(double xCoordinate) {
        image.setX(xCoordinate);
    }

    @Override
    public void setY(double yCoordinate) {
        image.setY(yCoordinate);
    }

    @Override
    public boolean intersects(IViewObject viewObject) {
        return image.intersects(
                ((AbstractViewObject)viewObject).getNode().getLayoutBounds());
    }

    @Override
    public double getStartX() {
        return image.getLayoutBounds().getMinX();
    }

    @Override
    public double getStartY() {
        return image.getLayoutBounds().getMinY();
    }

    @Override
    public double getEndX() {
        return image.getLayoutBounds().getMaxX();
    }

    @Override
    public double getEndY() {
        return image.getLayoutBounds().getMaxY();
    }

    @Override
    public void setScaleX(double scale) {
        image.setScaleX(scale);
    }

    @Override
    public double getHeight() {
        return image.getLayoutBounds().getMaxY() - 
                image.getLayoutBounds().getMinY();
    }
}
