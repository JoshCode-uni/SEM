package nl.joshuaslik.tudelft.SEM.control;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.joshuaslik.tudelft.SEM.model.container.Users;
import nl.joshuaslik.tudelft.SEM.model.container.Highscore;
import nl.joshuaslik.tudelft.SEM.model.container.PlayerMode;

import nl.joshuaslik.tudelft.SEM.utility.xml.SAXParser;
import nl.joshuaslik.tudelft.SEM.utility.xml.XMLFile;
import nl.joshuaslik.tudelft.SEM.utility.xml.XMLTag;

/**
 * A highscore reader/writer.
 *
 * @author Joshua Slik
 */
public class HighscoreHandler {

    private static HighscoreHandler highscoreHandler = null;
    private final ArrayList<Highscore> highscores = new ArrayList<>();
    private final String highscore_file = "logs/highscores.xml";
    private static final Object LOCK_SAVE = new Object();
    private int highestScore = 0;

    /**
     * Create a highscore handler.
     */
    private HighscoreHandler() {
        read();
    }

    /**
     * Add score if one of the highest 10.
     *
     * @param name is the name of the player
     * @param score is the score achieved
     */
    public synchronized void addScore(String name, Integer score) {
        int size = countUsers();
        if (removeUser(name)) {
            size -= 1;
        }
        for (int i = 0; i < highscores.size(); i++) {
            if (score > highscores.get(i).getScore()) {
                highscores.add(i, new Highscore(name, score));
                if (size == 10) {
                    highscores.remove(10);
                }
                break;
            }
        }
    }

    /**
     * Remove a user from the highscore list.
     *
     * @param user the name of the user.
     * @return if a user was removed.
     */
    private boolean removeUser(String user) {
        for (int i = 0; i < highscores.size(); i++) {
            if (user.equals(highscores.get(i).getUser())) {
                highscores.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Count the amount of users in the highscore list.
     *
     * @return the amount of users in the highscore list.
     */
    private int countUsers() {
        int userCount = 0;
        for (Highscore h : highscores) {
            if (!h.getUser().equals("")) {
                userCount += 1;
            }
        }
        return userCount;
    }

    /**
     * Read the xml file containing all of the highscores.
     */
    private void read() {
        synchronized (LOCK_SAVE) {
            try {
                FileInputStream f = new FileInputStream(new File(highscore_file));
                XMLFile file = SAXParser.parseFile(new FileInputStream(new File(highscore_file)));
                XMLTag root = file.getElement("highscores");
                for (XMLTag score : root.getElements()) {
                    highscores.add(new Highscore(score.getAttribute("user"),
                            Integer.parseInt(score.getElement("score").getContent())));
                }
            }
            catch (Exception ex) {
                Logger.getLogger(HighscoreHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Write the highscore list to a file.
     */
    public void write() {
        synchronized (LOCK_SAVE) {
            XMLTag root = new XMLTag("highscores");
            XMLFile file = new XMLFile(root);
            for (int i = 0; i < highscores.size(); i++) {
                String user = highscores.get(i).getUser();
                if (user.equals("")) {
                    continue;
                }
                XMLTag high = new XMLTag("highscore");
                high.addAttribute("pos", Integer.toString(i));
                high.addAttribute("user", user);
                XMLTag score = new XMLTag("score");
                score.setContent(Integer.toString(highscores.get(indexOf(user)).getScore()));
                high.addElement(score);
                root.addElement(high);
            }
            printToFile(file);
        }
    }

    /**
     * Get the index of the user.
     *
     * @param username the name of the user.
     * @return the index of the user.
     */
    private int indexOf(String username) {
        for (int i = 0; i < highscores.size(); i++) {
            if (highscores.get(i).getUser().equals(username)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Print the XMLFile to a file.
     *
     * @param file the xml file.
     */
    private synchronized void printToFile(XMLFile file) {
        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(
                    new File(highscore_file))));
            pw.print(file.toString());
            pw.flush();
            pw.close();
        }
        catch (IOException e) {
            Logger.getLogger(HighscoreHandler.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * Save the current highscores to a file.
     */
    public synchronized void save() {
        Users gi = Users.getInstance();
        if (gi.getPlayerMode().equals(PlayerMode.SINGLE_PLAYER)) {
            String name = gi.getPlayerNames().get(0);
            int score = gi.getTotalScore();
            if (score > highestScore) {
                highestScore = score;
                addScore(name, score);
                write();
            }
        }
    }

    /**
     * Get the list of highscores.
     *
     * @return the list of highscores.
     */
    public synchronized ArrayList<Highscore> getHighscores() {
        Collections.sort(highscores);
        return highscores;
    }

    /**
     * Get the instance of highscorehandler.
     *
     * @return the highscorehandler.
     */
    public synchronized static HighscoreHandler getInstance() {
        if (highscoreHandler == null) {
            highscoreHandler = new HighscoreHandler();
        }
        return highscoreHandler;
    }
}
