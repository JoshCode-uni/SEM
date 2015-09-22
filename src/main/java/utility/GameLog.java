package utility;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GameLog {

    private static PrintWriter pw;
    private static boolean initialized = false;

    /**
     * Constructs the logs directory and logfile if it is not created yet
     * Constructs a PrintWriter to write to the logfile If the log gets bigger
     * than 1MB it will reset the file
     */
    public static void constructor() {
        File dir = new File("logs");
        boolean mkdirs = dir.mkdirs();
        
        File log = new File("logs/Log.log");
        boolean logDelete = false;
        if (log.length() > 1_000_000) {
            logDelete = log.delete();
        }
        try {
            FileWriter fw = new FileWriter(log, true);
            pw = new PrintWriter(fw, true);
            pw.print("\n\n\n");
            pw.println("GAME STARTED AT: " + getCurrentTime());
            pw.println("Created directory = " + mkdirs);
            pw.println("Deleted old log = " + logDelete);
            pw.print("\n");
            initialized = true;
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * runtime errors or unexpected conditions
     *
     * @param string Error Description
     */
    public static void addErrorLog(String string) {
        if (initialized) {
            pw.println(getCurrentTime() + "[ERROR] " + string);
        }
    }

    /**
     * Undesirable runtime situations
     *
     * @param string Warning Description
     */
    public static void addWarningLog(String string) {
        if (initialized) {
            pw.println(getCurrentTime() + "[WARNING] " + string);
        }
    }

    /**
     * Adds a log with information to the logfile
     *
     * @param string Info Description
     */
    public static void addInfoLog(String string) {
        if (initialized) {
            pw.println(getCurrentTime() + "[INFO] " + string);
        }
    }

    /**
     * Adds a log with detailed information
     *
     * @param string Debug Description
     */
    public static void addDebugLog(String string) {
        if (initialized) {
            pw.println(getCurrentTime() + "[DEBUG] " + string + "\n");
        }
    }

    /**
     * Adds a log with more detailed information
     *
     * @param string Trace Description
     */
    public static void addTraceLog(String string) {
        if (initialized) {
            pw.println(getCurrentTime() + "[TRACE] " + string + "\n");
        }
    }

    /**
     * Gives the current date and time
     *
     * @return string with date and Time
     */
    private static String getCurrentTime() {
        Date now = new Date();
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(now) + " ";
    }
}
