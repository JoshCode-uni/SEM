package nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import javafx.scene.image.ImageView;

import nl.joshuaslik.tudelft.SEM.control.viewController.GameViewController;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Image view object test class.
 * @author Faris
 */
@RunWith(MockitoJUnitRunner.class)
public class ImageViewObjectTest {

    InputStream is;

    @Mock
    GameViewController gc;

    ImageView imageSpy;

    ImageViewObject ivo;

    /**
     * Initialize test.
     *
     * @throws IOException
     */
    @Before
    public void setup() throws IOException {
        is = getClass().getResource("/data/gui/img/penguin.png").openStream();
        ivo = new ImageViewObject(is, 2.0, 3.0, gc);

    }

    /**
     * Tests if constructor is initialized in the correct way.
     */
    @Test
    public void testConstructor() {
        assertNotEquals(ivo.getNode(), null);
        Mockito.verify(gc).drawNode(ivo.getNode());
    }

    /**
     * Tests getter
     */
    @Test
    public void testGetStartX() {
        assertEquals(0.0, ivo.getStartX(), 0.0);
    }

    /**
     * Tests getter
     */
    @Test
    public void testGetStartY() {
        assertEquals(0.0, ivo.getStartY(), 0.0);
    }

    /**
     * Tests getter
     */
    @Test
    public void testGetEndX() {
        assertEquals(2.0, ivo.getEndX(), 0.0);
    }

    /**
     * Tests getter
     */
    @Test
    public void testGetEndY() {
        assertEquals(2.0, ivo.getEndY(), 0.0);
    }

    /**
     * Tests setter
     */
    @Test
    public void testSetX() {
        ivo.setX(4.0);
        assertEquals(4.0, ivo.getStartX(), 0.0);
    }

    /**
     * Tests setter
     */
    @Test
    public void testSetY() {
        ivo.setY(5.0);
        assertEquals(5.0, ivo.getStartY(), 0.0);
    }

    /**
     * Tests if boundaries are handled in the correct way.
     */
    @Test
    public void testCheckBoundsOutOfBounds() {
        ivo.setBounds(0, 0, 20, 20);
        ivo.setX(-5);
        assertEquals(ivo.getStartX(), 0, 0.0);
        ivo.setY(-5);
        assertEquals(ivo.getStartY(), 0, 0.0);
        ivo.setX(25);
        assertEquals(ivo.getStartX(), 20.0, 0);
        ivo.setY(25);
        assertEquals(ivo.getStartY(), 18.0, 0);
    }

    /**
     * Tests if boundaries are handled in correct way.
     */
    @Test
    public void testCheckInBounds() {
        ivo.setBounds(0, 0, 20, 20);
        ivo.setX(5);
        assertEquals(ivo.getStartX(), 5, 0.0);
        ivo.setY(5);
        assertEquals(ivo.getStartY(), 5, 0.0);
    }

    /**
     * Tests getter
     */
    @Test
    public void testGetHeight() {
        assertEquals(ivo.getHeight(), 2.0, 0.0);
    }

    /**
     * Tests setter
     */
    @Test
    public void testSetScaleX() {
        assertEquals(ivo.getNode().getScaleX(), 1.0, 0.0);
        ivo.setScaleX(2);
        assertEquals(ivo.getNode().getScaleX(), 2.0, 0.0);
    }

    /**
     * Tests intersections
     */
    @Test
    public void testIntersects() {
        ImageViewObject ivo2 = new ImageViewObject(is, 2.0, 3.0, gc);
        assertTrue(ivo.intersects(ivo2));
        ivo2.setX(50);
        ivo2.setY(50);
        assertFalse(ivo.intersects(ivo2));
    }
}
