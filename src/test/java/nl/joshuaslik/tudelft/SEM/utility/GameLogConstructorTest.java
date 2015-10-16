package nl.joshuaslik.tudelft.SEM.utility;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;

import java.io.PrintWriter;
import java.io.Writer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(GameLog.class)
public class GameLogConstructorTest {

    private PrintWriter pw;

    /**
     * Setup the test.
     *
     * @throws Exception
     */
    @Before
    public void setup() throws Exception {
        pw = PowerMockito.mock(PrintWriter.class);
        PowerMockito.whenNew(PrintWriter.class).withParameterTypes(Writer.class)
                    .withArguments(Mockito.any(Writer.class), Mockito.anyBoolean()).thenReturn(pw);
        Mockito.doCallRealMethod().when(pw).print(Mockito.any(Object.class));
        Mockito.doCallRealMethod().when(pw).println(Mockito.any(Object.class));

    }

    /**
     * Tests if the constructor interacts correctly.
     */
    @Test
    public void testConstructor() {
        GameLog.constructor();
        Mockito.verify(pw, times(3)).println(Mockito.any(String.class));
        assertTrue(GameLog.getInitialization());
    }
}
