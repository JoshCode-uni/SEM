package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import nl.joshuaslik.tudelft.SEM.model.container.Point;

/**
 * A door which opens after a certain amount of time.
 * @author Faris
 */
public class TimeDoor extends AbstractDoor {

    private long waitTime;

    /**
     * Constructor of the time door.
     * @param game the game objects.
     * @param upperLeft the upper left point.
     * @param upperRight the upper right point.
     * @param bottomLeft the bottom left point.
     * @param bottomRight the bottom right point.
     * @param starttime the start time.
     * @param delay the time after which the door must open.
     */
    public TimeDoor(IGameObjects game, Point upperLeft, Point upperRight, Point bottomLeft, 
            Point bottomRight, long starttime, long delay) {
        super(game, upperLeft, upperRight, bottomLeft, bottomRight);
        waitTime = delay - starttime;
    }

    /**
     * Update the door.
     * @param nanoFrameTime the time of a frame in ns.
     */
    @Override
    public void update(long nanoFrameTime) {
        waitTime -= nanoFrameTime;
        super.update(nanoFrameTime);
    }

    /**
     * @return if the door is open.
     */
    @Override
    public boolean isOpen() {
        return waitTime <= 0;
    }
}
