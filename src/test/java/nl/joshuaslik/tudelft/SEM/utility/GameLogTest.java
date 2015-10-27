package nl.joshuaslik.tudelft.SEM.utility;

import static org.junit.Assert.assertTrue;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Test the gamelog class.
 * @author Faris
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(GameLog.class)
public class GameLogTest {

    private StringWriter sw;
    private PrintWriter pw;
    private String date = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

    /**
     * Setup the test.
     *
     * @throws Exception an exception is expected.
     */
    @Before
    public void setup() throws Exception {
        sw = new StringWriter();
        pw = new PrintWriter(sw);
        GameLog.setPrintWriter(pw);
        GameLog.setInitialization(true);
        PowerMockito.spy(GameLog.class);
        PowerMockito.doReturn(date).when(GameLog.class, "getCurrentTime");
    }

    /**
     * Tests if the correct information is logged by the error logger.
     */
    @Test
    public void testAddErrorLog() {
        String randomString = UUID.randomUUID().toString();
        GameLog.addErrorLog(randomString);
        assertTrue(sw.toString().contains(date));
        assertTrue(sw.toString().contains("[ERROR] " + randomString));
    }

    /**
     * Tests if the correct information is logged by the warning logger.
     */
    @Test
    public void testAddWarningLog() {
        String randomString = UUID.randomUUID().toString();
        GameLog.addWarningLog(randomString);
        assertTrue(sw.toString().contains(date));
        assertTrue(sw.toString().contains("[WARNING] " + randomString));
    }

    /**
     * Tests if the correct information is logged by the information logger.
     */
    @Test
    public void testAddInfoLog() {
        String randomString = UUID.randomUUID().toString();
        GameLog.addInfoLog(randomString);
        assertTrue(sw.toString().contains(date));
        assertTrue(sw.toString().contains("[INFO] " + randomString));
    }

    /**
     * Tests if the correct information is logged by the debug logger.
     */
    @Test
    public void testAddDebugLog() {
        String randomString = UUID.randomUUID().toString();
        GameLog.addDebugLog(randomString);
        assertTrue(sw.toString().contains(date));
        assertTrue(sw.toString().contains("[DEBUG] " + randomString));
    }

    /**
     * Tests if the correct information is logged by the trace logger.
     */
    @Test
    public void testAddTraceLog() {
        String randomString = UUID.randomUUID().toString();
        GameLog.addTraceLog(randomString);
        assertTrue(sw.toString().contains(date));
        assertTrue(sw.toString().contains("[TRACE] " + randomString));
    }

}
