package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import nl.joshuaslik.tudelft.SEM.model.container.Point;

public class TimeDoor extends AbstractDoor {

    private long waitTime;

    public TimeDoor(IGameObjects game, Point upperLeft, Point upperRight, Point bottomLeft, Point bottomRight, long starttime, long delay) {
        super(game, upperLeft, upperRight, bottomLeft, bottomRight);
        waitTime = delay - starttime;
    }

    @Override
    public void update(long nanoFrameTime) {
        waitTime -= nanoFrameTime;
        super.update(nanoFrameTime);
    }

    @Override
    public boolean isOpen() {
        return waitTime <= 0;
    }

}
