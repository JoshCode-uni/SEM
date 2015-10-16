package nl.joshuaslik.tudelft.SEM.model.container;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class VectorTest {

    private Vector vec1;
    private Vector vec2;
    private Vector vec3;
    private Vector vec4;
    private Vector vec5;
    private Vector vec6;
    private Point p1;
    private Point p2;

    @Before
    public void setUp() throws Exception {
        vec1 = new Vector(5, 2);
        vec2 = new Vector(5, 3);
        vec3 = new Vector(5, 2);
        vec4 = new Vector(5, -2);
        vec5 = new Vector(-5, 2);
        vec6 = new Vector(5, 0);
        p1 = new Point(5, 1);
        p2 = new Point(5, 6);
    }

    /**
     * Test initialization
     */
    @Test
    public void testVector() {
        assertEquals(vec1, vec3);
        assertNotEquals(vec1, vec2);
        assertEquals(vec1, new Vector(5, 2));
        assertNotEquals(vec1, null);
    }

    /**
     * Test correctly calculated intersectionpoint
     */
    @Test
    public void testGetIntersectionPoint() {
        assertEquals(vec1.getIntersectionPoint(p1, p2), new Point(5, 2));
        assertEquals(vec6.getIntersectionPoint(p1, p2), new Point(5, 0));
        assertEquals(vec1.getIntersectionPoint(p2, p2), null);
    }

    /**
     * Test correctly calculated direction.
     */
    @Test
    public void testPositiveDirection() {
        assertTrue(vec1.positiveDirection(p1, p2));
        assertFalse(vec1.positiveDirection(p2, p1));
    }

    /**
     * Test getter.
     */
    @Test
    public void testGetXdirection() {
        assertEquals(vec1.getXdirection(), 1, 0);
        assertEquals(vec5.getXdirection(), -1, 0);
        assertNotEquals(vec4.getXdirection(), -1, 0);
    }

    /**
     * Test getting the normal of a vector.
     */
    @Test
    public void testNormal() {
        assertEquals(vec1.normal(), new Vector(2, -5));
        assertEquals(vec4.normal(), new Vector(-2, -5));
    }

    /**
     * Test getter.
     */
    @Test
    public void testGetX() {
        assertEquals(vec1.getX(), 5, 0);
        assertNotEquals(vec5.getX(), -6, 0);
    }

    /**
     * Test getter.
     */
    @Test
    public void testGetY() {
        assertEquals(vec1.getY(), 2, 0);
        assertNotEquals(vec5.getY(), 3, 0);
    }

    /**
     * Test toString.
     */
    @Test
    public void testToString() {
        assertEquals("Vector{x=5.0, y=2.0}", vec1.toString());
    }
}
