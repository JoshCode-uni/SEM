package utility;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GameLog {
	private static PrintWriter pw;
	
	/**
	 * Constructs the logs directory and logfile if it is not created yet
	 * Constructs a PrintWriter to write to the logfile
	 * If the log gets bigger than 10kb it will reset the file
	 */
	public static void constructor(){
		File dir = new File("logs");
		dir.mkdirs();
		File log = new File("logs/Log.log");
		if(log.length() > 10000)
			log.delete();
		try {
			FileWriter fw = new FileWriter(log,true);
			pw = new PrintWriter(fw,true);
			pw.println("");
			pw.println("GAME STARTED AT: "+getCurrentTime());
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
		pw.println(getCurrentTime()+"[ERROR] "+string);
	}
	
	/**
	 * Undesirable runtime situations
	 * @param string Warning Description
	 */
	public static void addWarningLog(String string){
		pw.println(getCurrentTime()+"[WARNING] "+string);
	}
	
	/**
	 * Adds a log with information to the logfile
	 * @param string Info Description
	 */
	public static void addInfoLog(String string){
		pw.println(getCurrentTime()+"[INFO] "+string);
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
