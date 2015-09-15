package nl.joshuaslik.tudelft.SEM;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class GameLog {
	private static Logger logger=
			Logger.getLogger("Log");;
	private FileHandler fh;
	
	/**
	 * Builds a FileHandler to handle the logs added by the logger, uses SimpleFormatter
	 * to write the Log in a desired format.
	 */
	public GameLog(){
		System.setProperty("java.util.logging.SimpleFormatter.format","%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS [%4$-4s] %5$s%6$s%n" );
		try {
			fh = new FileHandler("logs/Log.log",true);
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Adds a log with information to the logfile
	 * @param string Information
	 */
	public static void addInfoLog(String string){
		logger.info(string);
	}
	
	/**
	 * Adds a warning to the logfile
	 * @param string Warning
	 */
	public static void addWarningLog(String string){
		logger.warning(string);
	}
	
	/**
	 * Adds a severe warning to the logfile
	 * @param string Severe warning
	 */
	public static void addSevereLog(String string){
		logger.log(Level.SEVERE, string);
	}
}
