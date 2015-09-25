package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import nl.joshuaslik.tudelft.SEM.model.container.Point;

public class BubbleDoor extends Door {

    private final int bubblesOtherSide;

    public BubbleDoor(IGameObjects game, Point upperLeft, Point upperRight, Point bottomLeft,
            Point bottomRight, int bubblesOtherSide) {
        super(game, upperLeft, upperRight, bottomLeft, bottomRight);
        this.bubblesOtherSide = bubblesOtherSide;
    }

    @Override
    public boolean isOpen() {
        return getGameObjects().bubblesLeft() <= bubblesOtherSide;
    }

}
