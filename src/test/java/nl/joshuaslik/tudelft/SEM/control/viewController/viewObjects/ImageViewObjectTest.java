package nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects;

import static org.junit.Assert.*;

import java.io.InputStream;

import nl.joshuaslik.tudelft.SEM.control.viewController.GameController;

import org.junit.Test;
import org.mockito.Mockito;

public class ImageViewObjectTest {
	InputStream is = Mockito.mock(InputStream.class);
	GameController gc = Mockito.mock(GameController.class);
	
	ImageViewObject ivo = new ImageViewObject(is, 2.0, 3.0, gc);
	
	@Test
	public void testConstructor() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetStartX() {
		assertEquals(2.0, ivo.getStartX());
	}
	
	@Test
	public void testGetStartY() {
		assertEquals(3.0, ivo.getStartY());
	}
	
	@Test
	public void testGetEndX() {
		assertEquals(2.0, ivo.getEndX());
	}
	
	@Test
	public void testGetEndY() {
		assertEquals(3.0, ivo.getEndY());
	}
	
	@Test
	public void testSetX() {
		ivo.setX(4.0);
		assertEquals(4.0, ivo.getStartX());
	}
	
	@Test
	public void testSetY() {
		ivo.setY(5.0);
		assertEquals(5.0, ivo.getStartY());
	}
	
	@Test
	public void testSetBounds() {
		
	}
	
	@Test
	public void testGetHeight() {
		
	}
	
	@Test
	public void testSetScaleX() {
		
	}
	
	@Test
	public void testIntersects() {
		
	}
	
	@Test
	public void testGetNode() {
		
	}

}
