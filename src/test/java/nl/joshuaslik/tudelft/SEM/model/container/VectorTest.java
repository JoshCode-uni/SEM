package nl.joshuaslik.tudelft.SEM.model.container;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import nl.joshuaslik.tudelft.SEM.model.container.Point;
import nl.joshuaslik.tudelft.SEM.model.container.Vector;
import org.junit.Before;
import org.junit.Test;

public class VectorTest {

    Vector vec1, vec2, vec3, vec4, vec5, vec6;
    Point p1, p2;

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

    @Test
    public void testVector() {
        assertEquals(vec1, vec3);
        assertNotEquals(vec1, vec2);
        assertEquals(vec1, new Vector(5, 2));
        assertNotEquals(vec1, null);
    }

    @Test
    public void testGetIntersectionPoint() {
        assertEquals(vec1.getIntersectionPoint(p1, p2), new Point(5, 2));
        assertEquals(vec6.getIntersectionPoint(p1, p2), new Point(5, 0));
        assertEquals(vec1.getIntersectionPoint(p2, p2), null);
    }

    @Test
    public void testPositiveDirection() {
        assertTrue(vec1.positiveDirection(p1, p2));
        assertFalse(vec1.positiveDirection(p2, p1));
    }

    @Test
    public void testGetXdirection() {
        assertEquals(vec1.getXdirection(), 1, 0);
        assertEquals(vec5.getXdirection(), -1, 0);
        assertNotEquals(vec4.getXdirection(), -1, 0);
    }

    @Test
    public void testNormal() {
        assertEquals(vec1.normal(), new Vector(2, -5));
        assertEquals(vec4.normal(), new Vector(-2, -5));
    }

    @Test
    public void testGetX() {
        assertEquals(vec1.getX(), 5, 0);
        assertNotEquals(vec5.getX(), -6, 0);
    }

    @Test
    public void testGetY() {
        assertEquals(vec1.getY(), 2, 0);
        assertNotEquals(vec5.getY(), 3, 0);
    }

    @Test
    public void testToString() {
        assertEquals("Vector{x=5.0, y=2.0}", vec1.toString());
    }

}
