package nl.joshuaslik.tudelft.SEM.control;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import nl.joshuaslik.tudelft.SEM.utility.xml.SAXParser;
import nl.joshuaslik.tudelft.SEM.utility.xml.XMLFile;
import nl.joshuaslik.tudelft.SEM.utility.xml.XMLTag;

/**
 * @author Joshua Slik
 */
public class Highscore {
	
	private ArrayList<String> users;
	private ArrayList<Integer> scores;
	private String highscore_file = "logs/highscores.xml";
	
	public Highscore() {
		users = new ArrayList<>();
		scores = new ArrayList<>();
		read();
	}
	
	/**
	 * Add score if one of the highest 10
	 *
	 * @param name  is the name of the player
	 * @param score is the score achieved
	 */
	public void addScore(String name, Integer score) {
		for (int i = 0; i < 10; i--) {
			if (score > scores.get(i)) {
				scores.add(i, score);
				scores.remove(10);
				users.add(i, name);
				users.remove(10);
			}
		}
	}
	
	public ArrayList<String> getUsers() {
		return users;
	}
	
	public ArrayList<Integer> getScores() {
		return scores;
	}
	
	private void read() {
            try {
                new File(highscore_file).createNewFile();
            }
            catch (IOException ex) {
                Logger.getLogger(Highscore.class.getName()).log(Level.SEVERE, null, ex);
            }
		XMLFile file = SAXParser.parseFile(highscore_file);
		XMLTag root = file.getElement("highscores");
		
		for (XMLTag score : root.getElements()) {
			users.add(score.getAttribute("user"));
			scores.add(Integer.parseInt(score.getElement("score").getContent()));
		}
	}
	
	public void write() {
		XMLTag root = new XMLTag("highscores");
		XMLFile file = new XMLFile(root);
		
		for (String user : users) {
			XMLTag high = new XMLTag("highscore");
			high.addAttribute("pos", "" + users.indexOf(user));
			high.addAttribute("user", user);
			XMLTag score = new XMLTag("score");
			score.setContent("" + scores.get(users.indexOf(user)));
			high.addElement(score);
			root.addElement(high);
		}
		
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new BufferedWriter(new FileWriter(new File(highscore_file))));
		} catch (IOException e) {
			e.printStackTrace();
		}
		assert pw != null;
		
		pw.print(file.toString());
		pw.flush();
		pw.close();
	}
}
