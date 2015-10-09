package nl.joshuaslik.tudelft.SEM.model.container;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

public class IntersectionPointTest {

    private IntersectionPoint point1;
    private IntersectionPoint point2;
    private IntersectionPoint point3;
    private IntersectionPoint point4;
    private Point point5;

    @Before
    public void beforeTest() {
        point1 = new IntersectionPoint(20, 20, new Vector(5, 2), 50);
        point2 = new IntersectionPoint(20, 20, new Vector(5, 2), 50);
        point3 = new IntersectionPoint(20, 20, new Vector(5, 2), 60);
        point4 = new IntersectionPoint(20, 20, null, 60);
		point5 = new Point(20, 20);
	}
	
	/**
	 * Tests initialization.
	 */
	@Test
	public void testIntersectionPoint() {
		assertEquals(point1, point2);
		assertNotEquals(point1, null);
		assertNotEquals(point1, point5);
	}
	
	/**
	 * Tests if the normal is correctly called.
	 */
	@Test
	public void testGetNormal() {
		assertEquals(point3.getNormal(), new Vector(5, 2));
		assertNotEquals(point3.getNormal(), new Vector(5, 3));
	}
	
	/**
	 * Tests if the distance between points is correctly calculated.
	 */
	@Test
	public void testGetDistance() {
		assertEquals(point1.getDistance(), 50, 0);
		assertNotEquals(point1.getDistance(), 25, 0);
	}
	
	/**
	 * Tests if an intersectionpoint has a speedvector (initialization).
	 */
	@Test
	public void testHasSpeedVec() {
		assertFalse(point4.hasSpeedVec());
	}
	
	/**
	 * Tests updating of a speed vector.
	 */
	@Test
	public void testGetSpeedVec() {
		point4.setSpeedVec(new Vector(50, 2));
		assertEquals(new Vector(50, 2), point4.getSpeedVec());
	}
	
	/**
	 * Tests if a speedvector is correctly set
	 */
	@Test
	public void testSetSpeedVec() {
		assertEquals(null, point1.getSpeedVec());
		point1.setSpeedVec(new Vector(7, 2));
		assertEquals(new Vector(7, 2), point1.getSpeedVec());
	}
}
