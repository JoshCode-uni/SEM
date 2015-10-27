package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects.ILineViewObject;

/**
 * Contains position of a line. Contains a collision functionality.
 *
 * @author faris
 */
public class Line extends AbstractLine implements IIntersectable {

    private final ILineViewObject line;

    /**
     * @param gameObjects
     * @param startX
     * @param startY
     * @param endX
     * @param endY
     */
    public Line(final IGameObjects gameObjects, final double startX, final double startY, final double endX, final double endY) {
        super(gameObjects, startX, startY, endX, endY);
        line = getGameObjects().makeLine(startX, startY, endX, endY);
        line.setStrokeWidth(10);
    }

    /**
     * Destroys the line object
     */
    @Override
    public final void destroy() {
        getGameObjects().removeObject(this);
        line.destroy();
    }

}
