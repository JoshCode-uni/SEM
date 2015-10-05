package nl.joshuaslik.tudelft.SEM.utility;

import static org.junit.Assert.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

public class GameLogTest {
	StringWriter sw;
	PrintWriter pw;
	
	/**
	 * Setup the test.
	 */
	@Before
	public void setup() {
		sw = new StringWriter();
		pw = new PrintWriter(sw);
		GameLog.setPrintWriter(pw);
		GameLog.setInitialization(true);
	}
	
	/**
	 * Tests if the correct information is logged by the error logger.
	 */
	@Test
	public void testAddErrorLog() {
		String randomString = UUID.randomUUID().toString();
		GameLog.addErrorLog(randomString);
		assertTrue(sw.toString().contains(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
		assertTrue(sw.toString().contains("[ERROR] "+randomString));
	}

	/**
	 * Tests if the correct information is logged by the warning logger.
	 */
	@Test
	public void testAddWarningLog() {
		String randomString = UUID.randomUUID().toString();
		GameLog.addWarningLog(randomString);
		assertTrue(sw.toString().contains(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
		assertTrue(sw.toString().contains("[WARNING] "+randomString));
	}

	/**
	 * Tests if the correct information is logged by the information logger.
	 */
	@Test
	public void testAddInfoLog() {
		String randomString = UUID.randomUUID().toString();
		GameLog.addInfoLog(randomString);
		assertTrue(sw.toString().contains(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
		assertTrue(sw.toString().contains("[INFO] "+randomString));	}

	/**
	 * Tests if the correct information is logged by the debug logger.
	 */
	@Test
	public void testAddDebugLog() {
		String randomString = UUID.randomUUID().toString();
		GameLog.addDebugLog(randomString);
		assertTrue(sw.toString().contains(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
		assertTrue(sw.toString().contains("[DEBUG] "+randomString));
	}

	/**
	 * Tests if the correct information is logged by the trace logger.
	 */
	@Test
	public void testAddTraceLog() {
		String randomString = UUID.randomUUID().toString();
		GameLog.addTraceLog(randomString);
		assertTrue(sw.toString().contains(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
		assertTrue(sw.toString().contains("[TRACE] "+randomString));
	}

}
