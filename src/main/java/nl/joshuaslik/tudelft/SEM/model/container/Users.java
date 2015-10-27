/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.model.container;

import java.util.ArrayList;

/**
 * This is a singleton, containing information about the player.
 *
 * @author Faris
 */
public class Users {

    private static Users gameInfo;
    private PlayerMode playerMode = PlayerMode.SINGLE_PLAYER;
    private GameMode gameMode = GameMode.CLASSIC;
    private int lives = 3;
    private final ArrayList<Integer> levelScoresPlayer1;
    private final ArrayList<Integer> levelScoresPlayer2;
    private final ArrayList<String> playerNames;

    /**
     * Private constructor to ensure singleton.
     */
    private Users() {
        levelScoresPlayer1 = new ArrayList<>();
        playerNames = new ArrayList<>();
        levelScoresPlayer2 = new ArrayList<>();
    }

    /**
     * Get the instance of gameinfo.
     *
     * @return gameinfo instance.
     */
    public static Users getInstance() {
        if (gameInfo == null) {
            gameInfo = new Users();
        }
        return gameInfo;
    }

    /**
     * Get a copy of the player mode.
     *
     * @return the player mode.
     */
    public PlayerMode getPlayerMode() {
        return playerMode;
    }

    /**
     * Get a copy of the game mode.
     *
     * @return the game mode.
     */
    public GameMode getGameMode() {
        return gameMode;
    }

    /**
     * Get a copy of the player names list.
     *
     * @return player names list.
     */
    public ArrayList<String> getPlayerNames() {
        ArrayList<String> copy = new ArrayList<>();
        for (String str : playerNames) {
            copy.add(str);
        }
        return copy;
    }

    /**
     * Set the score of a certain level.
     *
     * @param level the level.
     * @param score the score.
     */
    public void setLevelScore(int level, int score) {
        setPlayer1LevelScore(level, score);
    }

    /**
     * Set the score of a certain level for player 1 specifically.
     *
     * @param level the level.
     * @param score the score.
     */
    public void setPlayer1LevelScore(int level, int score) {
        ensureSize(levelScoresPlayer1, level + 1);
        if (levelScoresPlayer1.get(level) < score) {
            levelScoresPlayer1.set(level, score);
        }
    }

    /**
     * Set the score of a certain level for player 2 specifically.
     *
     * @param level the level.
     * @param score the score.
     */
    public void setPlayer2LevelScore(int level, int score) {
        ensureSize(levelScoresPlayer2, level + 1);
        if (levelScoresPlayer2.get(level) < score) {
            levelScoresPlayer2.set(level, score);
        }
    }

    /**
     * Get the total score of the player.
     *
     * @return
     */
    public int getTotalScore() {
        return getPlayer1Score();
    }

    /**
     * Get the total score of the player 1.
     *
     * @return
     */
    public int getPlayer1Score() {
        int sum = 0;
        for (int i : levelScoresPlayer1) {
            sum += i;
        }
        return sum;
    }

    /**
     * Get the total score of the player 2.
     *
     * @return
     */
    public int getPlayer2Score() {
        int sum = 0;
        for (int i : levelScoresPlayer2) {
            sum += i;
        }
        return sum;
    }

    /**
     * Set the player mode.
     *
     * @param playerMode the player mode.
     */
    public void setPlayerMode(PlayerMode playerMode) {
        this.playerMode = playerMode;
    }

    /**
     * Set the name of a player.
     *
     * @param playerNr nr of the player.
     * @param playerName name of the player.
     */
    public void setPlayerName(int playerNr, String playerName) {
        ensureStringSize(playerNames, playerNr + 1);
        playerNames.set(playerNr, playerName);
    }

    /**
     * Get the amount of lives of the player.
     *
     * @return the amount of lives.
     */
    public int getLives() {
        return lives;
    }

    /**
     * Add a life.
     */
    public void addLife() {
        if (lives < 10) {
            ++lives;
        }
    }

    /**
     * Remove a life.
     */
    public void loseLife() {
        if (lives > 0) {
            --lives;
        }
    }

    /**
     * Reset the amount of lives.
     */
    public void reset() {
        levelScoresPlayer1.clear();
        levelScoresPlayer2.clear();
        lives = 3;
    }

    /**
     * Ensure the size of list is at least equal to size.
     *
     * @param list the list.
     * @param size the size.
     */
    private void ensureSize(final ArrayList<Integer> list, final int size) {
        while (list.size() < size) {
            list.add(0);
        }
    }

    /**
     * Ensure the size of list is at least equal to size.
     *
     * @param list the list.
     * @param size the size.
     */
    private void ensureStringSize(final ArrayList<String> list, final int size) {
        while (list.size() < size) {
            list.add("");
        }
    }

    /**
     * Switch to the classical mode.
     */
    public void setClassicMode() {
        gameMode = GameMode.CLASSIC;
    }

    /**
     * Switch to the survival mode.
     */
    public void setSurvivalMode() {
        gameMode = GameMode.SURVIVAL;
    }
}
