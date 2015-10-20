package nl.joshuaslik.tudelft.SEM.model.container;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

public class PointTest {

    private Point point1;
    private Point point2;
    private Point point3;

    @Before
    public void setUp() throws Exception {
        point1 = new Point(20, 20);
        point2 = new Point(25, 25);
        point3 = new Point(20, 20);
    }

    @Test
    public void testPoint() {
        assertEquals(point1, point3);
        assertNotEquals(point1, point2);
        assertNotEquals(point1, null);
    }

    /**
     * Tests correctly calculated distance.
     */
    @Test
    public void testDistanceTo() {
        double distance = Math.sqrt(50);
        assertEquals(distance, point1.distanceTo(point2), 0);
    }

    /**
     * Test translation from points with double values
     */
    @Test
    public void testTranslateDoubleDouble() {
        assertEquals(point1.getxPos(), point3.getxPos(), 0);
        assertEquals(point1.getyPos(), point3.getyPos(), 0);
        assertEquals(point1.translate(5, 5).getxPos(), point2.getxPos(), 0);
        assertEquals(point1.translate(5, 5).getyPos(), point2.getyPos(), 0);
    }

    /**
     * Test translation from points with points.
     */
    @Test
    public void testTranslatePoint() {
        assertEquals(point1.getxPos(), point3.getxPos(), 0);
        assertEquals(point1.getyPos(), point3.getyPos(), 0);
        assertEquals(point1.translate(new Point(5, 5)), point2);
    }

    /**
     * Test getter.
     */
    @Test
    public void testGetxPos() {
        assertEquals(point1.getxPos(), 20, 0);
        assertNotEquals(point1.getxPos(), 50, 0);
    }

    /**
     * Test getter.
     */
    @Test
    public void testGetyPos() {
        assertEquals(point1.getyPos(), 20, 0);
        assertNotEquals(point1.getyPos(), 50, 0);
    }

    /**
     * Testing toString.
     */
    @Test
    public void testToString() {
        assertEquals("Position: (x: 20.0, y: 20.0)", point1.toString());
    }
}
