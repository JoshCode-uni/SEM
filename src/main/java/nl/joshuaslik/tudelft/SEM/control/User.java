package nl.joshuaslik.tudelft.SEM.control;

import java.util.ArrayList;

import nl.joshuaslik.tudelft.SEM.control.gameObjects.Player;
import nl.joshuaslik.tudelft.SEM.model.container.Levels;

public class User {
	
	private static int userNumber=0;
	private static String user1;
	private static String user2;
	private String name;
	private static int score = 0;
	private Player player;
	private int survivalScore;
	private final ArrayList<Integer> singlePlayerScore;
	private final ArrayList<Integer> multiPlayerCoopScore;
	private final ArrayList<Integer> multiPlayerVsScore;
	
	public User(String name) {
		userNumber +=1;
		this.name = name;
		survivalScore = 0;
		singlePlayerScore = new ArrayList<>();
		multiPlayerCoopScore = new ArrayList<>();
		multiPlayerVsScore = new ArrayList<>();
	}
	
	public void initializePlayer(Player player) {
		this.player=player;
	}
	
	public void setSurvivalScore() {
		if(survivalScore<player.getScore())
			survivalScore = player.getScore();
	}
	
	public void setSinglePlayerScore() {
        ensureSize(singlePlayerScore, Levels.getCurrentLevel() + 1);
		if(singlePlayerScore.get(Levels.getCurrentLevel())<player.getScore())
			singlePlayerScore.set(Levels.getCurrentLevel(), player.getScore());
		System.out.println(singlePlayerScore);
	}
	
	public void setCoopScore() {
        ensureSize(multiPlayerCoopScore, Levels.getCurrentLevel() + 1);
		if(multiPlayerCoopScore.get(Levels.getCurrentLevel())<player.getScore())
			multiPlayerCoopScore.set(Levels.getCurrentLevel(), player.getScore());
		System.out.println(multiPlayerCoopScore);
	}
	
	public void setVsScore() {

        ensureSize(multiPlayerVsScore, Levels.getCurrentLevel() + 1);
		if(multiPlayerVsScore.get(Levels.getCurrentLevel())<player.getScore())
			multiPlayerVsScore.set(Levels.getCurrentLevel(), player.getScore());
		System.out.println(multiPlayerVsScore);
	}
	
	public void calculateTotalScore() {
		int scores = 0;
		scores+=survivalScore;
		for (int score: singlePlayerScore){
			scores+=score;
		}
		for (int score: multiPlayerCoopScore){
			scores+=score;
		}
		for (int score: multiPlayerVsScore){
			scores+=score;
		}
		score = scores;
	}
	
	public static int getTotalScore(){
		return score;
	}

	public String getName() {
		return name;
	}
	private void ensureSize(final ArrayList<Integer> list, final int size) {
        while (list.size() < size) {
            list.add(0);
        }
    }
}
