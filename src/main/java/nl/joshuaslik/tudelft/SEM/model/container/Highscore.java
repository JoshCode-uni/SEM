/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.model.container;

/**
 * Stores a highscore.
 *
 * @author Faris
 */
public class Highscore implements Comparable<Highscore> {

    private final String user;
    private final int score;

    /**
     * Create a highscore.
     *
     * @param user the users name.
     * @param score the score.
     */
    public Highscore(String user, int score) {
        this.user = user;
        this.score = score;
    }

    /**
     * Compare the highscore to another highscore.
     *
     * @param o the highscore to compare to.
     * @return a number: positive if other object is larger.
     */
    @Override
    public int compareTo(Highscore o) {
        return Integer.compare(o.score, this.score);
    }

    /**
     * Get the users name.
     *
     * @return the users name.
     */
    public String getUser() {
        return user;
    }

    /**
     * Get the score.
     *
     * @return the score.
     */
    public int getScore() {
        return score;
    }
}
