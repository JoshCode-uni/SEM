package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import nl.joshuaslik.tudelft.SEM.model.container.Point;

public class BubbleDoor extends AbstractDoor {

    private final int bubblesOtherSide;

    public BubbleDoor(final IGameObjects game, final Point upperLeft, final Point upperRight, 
            final Point bottomLeft, final Point bottomRight, final int bubblesOtherSide) {
        super(game, upperLeft, upperRight, bottomLeft, bottomRight);
        this.bubblesOtherSide = bubblesOtherSide;
    }

    @Override
    public boolean isOpen() {
        return getGameObjects().bubblesLeft() <= bubblesOtherSide;
    }

}
