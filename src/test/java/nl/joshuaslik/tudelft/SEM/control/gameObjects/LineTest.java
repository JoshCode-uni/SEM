package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects.ILineViewObject;
import nl.joshuaslik.tudelft.SEM.model.container.IntersectionPoint;
import nl.joshuaslik.tudelft.SEM.model.container.Point;

@RunWith(MockitoJUnitRunner.class)
public class LineTest {
	
	@Mock
	IGameObjects gameObjects;
	
	@Mock
	ILineViewObject line;
	
	@Mock
	Point p;
	
	Line L;
	
	@Before
	public void setUp() throws Exception {
		when(gameObjects.makeLine(0.0, 5.0, 15.0, 25.0)).thenReturn(line);
		L = new Line(gameObjects,0.0,5.0,15.0,25.0);
	}
	
	@Test
	public void testSetStrokeWidth(){
		verify(line).setStrokeWidth(10);
	}
	@Test
	public void testPoint1Set() {
		assertEquals(L.getPoint1().getxPos(),0.0,0);
		assertEquals(L.getPoint1().getyPos(),5.0,0);
	}
	
	@Test
	public void testPoint2Set() {
		assertEquals(L.getPoint2().getxPos(),15.0,0);
		assertEquals(L.getPoint2().getyPos(),25.0,0);
	}
	
	@Test
	public void testVectorSet() {
		assertEquals(L.getVector().getX(),15,0);
		assertEquals(L.getVector().getY(),20,0);
	}
	
	@Test
	public void testGetClosestIntersection() {
		when(p.getxPos()).thenReturn(50d);
		when(p.getyPos()).thenReturn(25d);
		assertEquals(L.getClosestIntersection(p),new IntersectionPoint(15.0,25.0,L.getVector().normal(),Double.MAX_VALUE));
	}
/*
	@Test
	public void testDestroyObject() {
		verify(gameObjects).removeObject(L);
	}
	*/
	@Test
	public void testDestroyLine() {
		L.destroy();
		verify(gameObjects).removeObject(L);
		verify(line).destroy();
	}

}
