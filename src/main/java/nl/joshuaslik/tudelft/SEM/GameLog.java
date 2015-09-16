package nl.joshuaslik.tudelft.SEM;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GameLog {
	private File log;
	private FileWriter fw;
	private static PrintWriter pw;
	
	/**
	 * Builds a FileHandler to handle the logs added by the logger, uses SimpleFormatter
	 * to write the Log in a desired format.
	 */
	public GameLog(){
//		System.setProperty("java.util.logging.SimpleFormatter.format","%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS [%4$-4s] %5$s%6$s%n" );
		File log = new File("logs/Log.log");
		try {
			fw = new FileWriter(log,true);
			pw = new PrintWriter(fw,true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * runtime errors or unexpected conditions
	 * @param string Error Description
	 */
	public static void addErrorLog(String string){
		pw.println(getCurrentTime()+"[ERROR] "+string+"\n");
	}
	
	/**
	 * Undesirable runtime situations
	 * @param string Warning Description
	 */
	public static void addWarningLog(String string){
		pw.println(getCurrentTime()+"[WARNING] "+string+"\n");
	}
	
	/**
	 * Adds a log with information to the logfile
	 * @param string Info Description
	 */
	public static void addInfoLog(String string){
		pw.println(getCurrentTime()+"[INFO] "+string+"\n");
	}
	
	/**
	 * Adds a log with detailed information
	 * @param string Debug Description
	 */
	public static void addDebugLog(String string){
		pw.println(getCurrentTime()+"[DEBUG] "+string+"\n");
	}
	
	/**
	 * Adds a log with more detailed information
	 * @param string Trace Description
	 */
	public static void addTraceLog(String string){
		pw.println(getCurrentTime()+"[TRACE] "+string+"\n");
	}
	
	/**
	 * Gives the current date and time
	 * @return string with date and Time
	 */
	private static String getCurrentTime(){
		Date now = new Date();
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(now)+" ";
	}
}
