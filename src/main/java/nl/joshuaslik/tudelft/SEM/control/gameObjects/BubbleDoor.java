package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import nl.joshuaslik.tudelft.SEM.model.container.Point;

/**
 * A door which is opened when all bubbles at the side of the player are destroyed.
 *
 * @author Faris
 */
public class BubbleDoor extends AbstractDoor {

    private final int bubblesOtherSide;

    /**
     * Create a bubble door.
     *
     * @param game the game objects.
     * @param upperLeft the upper left point.
     * @param upperRight the upper right point.
     * @param bottomLeft the bottom left point.
     * @param bottomRight the bottom right point
     * @param bubblesOtherSide the amount of bubbles on the other side of the door.
     */
    public BubbleDoor(final IGameObjects game, final Point upperLeft, final Point upperRight,
            final Point bottomLeft, final Point bottomRight, final int bubblesOtherSide) {
        super(game, upperLeft, upperRight, bottomLeft, bottomRight);
        this.bubblesOtherSide = bubblesOtherSide;
    }

    /**
     * @return if the door is open.
     */
    @Override
    public boolean isOpen() {
        return getGameObjects().bubblesLeft() <= bubblesOtherSide;
    }

}
