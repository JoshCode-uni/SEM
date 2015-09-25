package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import nl.joshuaslik.tudelft.SEM.model.container.Point;

public class BubbleDoor extends Door {

	public BubbleDoor(IGameObjects game, Point upperLeft, Point upperRight, Point bottomLeft,
			Point bottomRight) {
		super(game, upperLeft, upperRight, bottomLeft, bottomRight);
	}
	
	// Is already available in Door class?
	public void update(long nanoFrameTime) {
	//	if(open == true){
	//		destroy();
	//	}
	}	

}
