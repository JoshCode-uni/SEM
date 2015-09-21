
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import nl.joshuaslik.tudelft.SEM.model.container.Levels;
import org.junit.Test;

public class LevelsTest {

    @Test
    public void testAmountOfLevels() {
        assertEquals(Levels.amountOfLevels(), 4);
        assertNotEquals(Levels.amountOfLevels(), 7);
    }

    @Test
    public void testGetLevel() {
//        ArrayList<Bubble> bubbles = Levels.getLevel(0);
//        assertEquals(bubbles.size(), 1);
//        bubbles = Levels.getLevel(1);
//        assertEquals(bubbles.size(), 2);
//        bubbles = Levels.getLevel(2);
//        assertEquals(bubbles.size(), 3);
//        assertEquals(bubbles.get(1).getRadius(), 20d, 0);
//        bubbles = Levels.getLevel(3);
//        assertEquals(bubbles.size(), 3);
//        assertEquals(bubbles.get(1).getRadius(), 70d, 0);
//        bubbles = Levels.getLevel(4);
//        assertEquals(bubbles.size(), 4);
    }

}
