package nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects;

import static org.junit.Assert.*;
import nl.joshuaslik.tudelft.SEM.control.viewController.GameController;
import java.io.InputStream;
import org.junit.Test;
import org.mockito.Mockito;

public class LineViewObjectTest {

	GameController gc = Mockito.mock(GameController.class);
	
	LineViewObject lvo = new LineViewObject(1, 2, 3, 4, gc);
	
//	@Test
//	public void testConstructror() {
//		
//	}
	
//	@Test
//	public void testGetNode() {
//		
//	}
	
	@Test
	public void testGetStartX() {
		assertEquals(1.0, lvo.getStartX(), 0.0);
	}
	
	@Test
	public void testGetStartY() {
		assertEquals(2.0, lvo.getStartY(), 0.0);
	}
	
	@Test
	public void testGetEndX() {
		assertEquals(3.0, lvo.getEndX(), 0.0);
	}
	
	@Test
	public void testGetEndY() {
		assertEquals(4.0, lvo.getEndY(), 0.0);
	}
	
	@Test
	public void testSetStartX() {
		lvo.setStartX(5.0);
		assertEquals(5.0, lvo.getStartX(), 0.0);
	}
	
	@Test
	public void testSetStartY() {
		lvo.setStartY(6.0);
		assertEquals(6.0, lvo.getStartY(), 0.0);
	}
	
	@Test
	public void testSetEndX() {
		lvo.setEndX(7.0);
		assertEquals(7.0, lvo.getEndX(), 0.0);
	}
	
	@Test
	public void testSetEndY() {
		lvo.setEndY(8.0);
		assertEquals(8.0, lvo.getEndY(), 0.0);
	}
	
//	@Test
//	public void testSetStrokeWidth() {	
//	}
	
//	@Test
//	public void testSetColor() {
//	}
	
//	@Test
//	public void testSetOpacity() {
//	}
}
