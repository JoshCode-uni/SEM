package nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects;

import static org.junit.Assert.*;
import nl.joshuaslik.tudelft.SEM.control.viewController.GameController;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class CircleViewObjectTest {

	GameController gc = Mockito.mock(GameController.class);
	
	CircleViewObject cvo = new CircleViewObject(2.0, 3.0, 4.0, gc);
	
	@Test
	public void testConstructor() {
		assertEquals(2.0, cvo.getCenterX(), 0);
		assertEquals(3.0, cvo.getCenterY(), 0);
		assertEquals(4.0, cvo.getRadius(), 0);
	}

//	@Test
//	public void testGetNode() {
//		fail("Not yet implemented");
//	}
	
	@Test
	public void testGetCenterX() {
		assertEquals(2.0, cvo.getCenterX(), 0);
	}
	
	@Test
	public void testGetCenterY() {
		assertEquals(3.0, cvo.getCenterY(), 0);
	}
	
	@Test
	public void testSetCenterX() {
		cvo.setCenterX(5.0);
		assertEquals(5.0, cvo.getCenterX(), 0);
	}
	
	@Test
	public void testSetCenterY() {
		cvo.setCenterX(4.0);
		assertEquals(4.0, cvo.getCenterX(), 0);
	}
	
	@Test
	public void testSetRadius() {
		cvo.setCenterX(1.0);
		assertEquals(1.0, cvo.getCenterX(), 0);
	}
	
//	@Test
//	public void testSetBounds() {
//		fail("Not implemented yet");
//	}
}
