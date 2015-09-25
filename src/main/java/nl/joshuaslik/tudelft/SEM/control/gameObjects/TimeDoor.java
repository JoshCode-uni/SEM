package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import nl.joshuaslik.tudelft.SEM.model.container.Point;

public class TimeDoor extends Door {
	long startTime;
	long Delay;

	public TimeDoor(IGameObjects game, Point upperLeft, Point upperRight, Point bottomLeft,
			Point bottomRight, long starttime, long delay) {
		super(game, upperLeft, upperRight, bottomLeft, bottomRight);
		starttime = this.startTime;
		delay = this.Delay;
	}
	
	// Is already available in Door class?
	public void update(long nanoFrameTime) {
	//	if(open == true){
	//		destroy();
	//	}
	}

	@Override
	public boolean isOpen() {
		// TODO Auto-generated method stub
		return false;
	}	

}
