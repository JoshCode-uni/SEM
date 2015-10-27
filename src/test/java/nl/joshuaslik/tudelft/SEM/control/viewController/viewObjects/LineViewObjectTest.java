package nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects;

import static org.junit.Assert.*;

import nl.joshuaslik.tudelft.SEM.control.viewController.GameViewController;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javafx.scene.Node;
import javafx.scene.shape.Line;

import static org.mockito.Mockito.verify;

/**
 * Test the line view object class.
 * @author Faris
 */
@RunWith(MockitoJUnitRunner.class)
public class LineViewObjectTest {

    @Mock
    GameViewController gc;

    LineViewObject lvo;

    /**
     * Initialize test.
     */
    @Before
    public void Setup() {
        lvo = new LineViewObject(1, 2, 3, 4, gc);
    }

    /**
     * Tests if the constructor is initialized correct.
     */
    @Test
    public void testConstructror() {
        verify(gc).drawNode((Node) Mockito.any(Line.class));
    }

    /**
     * Tests getter.
     */
    @Test
    public void testGetStartX() {
        assertEquals(1.0, lvo.getStartX(), 0.0);
    }

    /**
     * Tests getter.
     */
    @Test
    public void testGetStartY() {
        assertEquals(2.0, lvo.getStartY(), 0.0);
    }

    /**
     * Tests getter.
     */
    @Test
    public void testGetEndX() {
        assertEquals(3.0, lvo.getEndX(), 0.0);
    }

    /**
     * Tests getter.
     */
    @Test
    public void testGetEndY() {
        assertEquals(4.0, lvo.getEndY(), 0.0);
    }

    /**
     * Tests setter.
     */
    @Test
    public void testSetStartX() {
        lvo.setStartX(5.0);
        assertEquals(5.0, lvo.getStartX(), 0.0);
    }

    /**
     * Tests setter.
     */
    @Test
    public void testSetStartY() {
        lvo.setStartY(6.0);
        assertEquals(6.0, lvo.getStartY(), 0.0);
    }

    /**
     * Tests setter.
     */
    @Test
    public void testSetEndX() {
        lvo.setEndX(7.0);
        assertEquals(7.0, lvo.getEndX(), 0.0);
    }

    /**
     * Tests setter.
     */
    @Test
    public void testSetEndY() {
        lvo.setEndY(8.0);
        assertEquals(8.0, lvo.getEndY(), 0.0);
    }

    /**
     * Tests setter.
     */
    @Test
    public void testSetStrokeWidth() {
        lvo.setStrokeWidth(50);
        assertEquals(50, ((Line) lvo.getNode()).getStrokeWidth(), 0.0);
    }

    /**
     * Tests setter.
     */
    @Test
    public void testSetColor() {
        lvo.setColor(0.5, .25, .32);
        assertEquals("0x804052ff", ((Line) lvo.getNode()).getStroke().toString());
    }

    /**
     * Tests setter.
     */
    @Test
    public void testSetOpacity() {
        lvo.setOpacity(0.32);
        assertEquals(0.32, ((Line) lvo.getNode()).getOpacity(), 0.0);
    }
}
