package nl.joshuaslik.tudelft.SEM.model.container;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LevelsTest {
	Levels levels;
	@Before
	public void setUp() throws Exception {
		levels = new Levels();
	}

	@Test
	public void testAmountOfLevels() {
		assertEquals(levels.amountOfLevels(),5);
		assertNotEquals(levels.amountOfLevels(),7);
	}

	@Test
	public void testGetLevel() {
		fail("Not yet implemented");
	}

}
