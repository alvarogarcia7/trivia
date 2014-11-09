package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;

public class Game {

	private static final String ROCK_TOPIC_DESCRIPTION = "Rock";
	private static final String SPORTS_TOPIC_DESCRIPTION = "Sports";
	private static final String SCIENCE_TOPIC_DESCRIPTION = "Science";
	private static final String POP_TOPIC_DESCRIPTION = "Pop";
	private static final int QUESTION_POOL_SIZE = 50;
	ArrayList players = new ArrayList();
    int[] places = new int[6];
    int[] purses  = new int[6];
    boolean[] inPenaltyBox  = new boolean[6];
    
    Questions popQuestions = new Questions(new Topic(POP_TOPIC_DESCRIPTION));
    Questions scienceQuestions = new Questions(new Topic(SCIENCE_TOPIC_DESCRIPTION));
    Questions sportsQuestions = new Questions(new Topic(SPORTS_TOPIC_DESCRIPTION));
    Questions rockQuestions = new Questions(new Topic(ROCK_TOPIC_DESCRIPTION));
    
    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;
    
    public  Game(){
    	createQuestions();
    }

	private void createQuestions() {
		for (int i = 0; i < QUESTION_POOL_SIZE; i++) {
			popQuestions.addWithDescriptionFromTopic();
			scienceQuestions.addWithDescriptionFromTopic();
			sportsQuestions.addWithDescriptionFromTopic();
			rockQuestions.addWithDescriptionFromTopic();
    	}
	}

	public boolean isPlayable() {
		return (howManyPlayers() >= 2);
	}

	public boolean add(String playerName) {
		
		
	    players.add(playerName);
	    places[howManyPlayers()] = 0;
	    purses[howManyPlayers()] = 0;
	    inPenaltyBox[howManyPlayers()] = false;
	    
	    System.out.println(playerName + " was added");
	    System.out.println("They are player number " + players.size());
		return true;
	}
	
	public int howManyPlayers() {
		return players.size();
	}

	public void roll(int roll) {
		System.out.println(players.get(currentPlayer) + " is the current player");
		System.out.println("They have rolled a " + roll);
		
		if (inPenaltyBox[currentPlayer]) {
			if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;
				
				System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");
				places[currentPlayer] = places[currentPlayer] + roll;
				if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;
				
				System.out.println(players.get(currentPlayer) 
						+ "'s new location is " 
						+ places[currentPlayer]);
				System.out.println("The category is " + currentCategory());
				askQuestion();
			} else {
				System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");
				isGettingOutOfPenaltyBox = false;
				}
			
		} else {
		
			places[currentPlayer] = places[currentPlayer] + roll;
			if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;
			
			System.out.println(players.get(currentPlayer) 
					+ "'s new location is " 
					+ places[currentPlayer]);
			System.out.println("The category is " + currentCategory());
			askQuestion();
		}
		
	}

	private void askQuestion() {
		if (currentCategory() == POP_TOPIC_DESCRIPTION)
			System.out.println(popQuestions.removeFirst());
		if (currentCategory() == SCIENCE_TOPIC_DESCRIPTION)
			System.out.println(scienceQuestions.removeFirst());
		if (currentCategory() == SPORTS_TOPIC_DESCRIPTION)
			System.out.println(sportsQuestions.removeFirst());
		if (currentCategory() == ROCK_TOPIC_DESCRIPTION)
			System.out.println(rockQuestions.removeFirst());		
	}
	
	
	private String currentCategory() {
		if (places[currentPlayer] == 0) return POP_TOPIC_DESCRIPTION;
		if (places[currentPlayer] == 4) return POP_TOPIC_DESCRIPTION;
		if (places[currentPlayer] == 8) return POP_TOPIC_DESCRIPTION;
		if (places[currentPlayer] == 1) return SCIENCE_TOPIC_DESCRIPTION;
		if (places[currentPlayer] == 5) return SCIENCE_TOPIC_DESCRIPTION;
		if (places[currentPlayer] == 9) return SCIENCE_TOPIC_DESCRIPTION;
		if (places[currentPlayer] == 2) return SPORTS_TOPIC_DESCRIPTION;
		if (places[currentPlayer] == 6) return SPORTS_TOPIC_DESCRIPTION;
		if (places[currentPlayer] == 10) return SPORTS_TOPIC_DESCRIPTION;
		return ROCK_TOPIC_DESCRIPTION;
	}

	public boolean wasCorrectlyAnswered() {
		if (inPenaltyBox[currentPlayer]){
			if (isGettingOutOfPenaltyBox) {
				System.out.println("Answer was correct!!!!");
				purses[currentPlayer]++;
				System.out.println(players.get(currentPlayer) 
						+ " now has "
						+ purses[currentPlayer]
						+ " Gold Coins.");
				
				boolean winner = didPlayerWin();
				currentPlayer++;
				if (currentPlayer == players.size()) currentPlayer = 0;
				
				return winner;
			} else {
				currentPlayer++;
				if (currentPlayer == players.size()) currentPlayer = 0;
				return true;
			}
			
			
			
		} else {
		
			System.out.println("Answer was corrent!!!!");
			purses[currentPlayer]++;
			System.out.println(players.get(currentPlayer) 
					+ " now has "
					+ purses[currentPlayer]
					+ " Gold Coins.");
			
			boolean winner = didPlayerWin();
			currentPlayer++;
			if (currentPlayer == players.size()) currentPlayer = 0;
			
			return winner;
		}
	}
	
	public boolean wrongAnswer(){
		System.out.println("Question was incorrectly answered");
		System.out.println(players.get(currentPlayer)+ " was sent to the penalty box");
		inPenaltyBox[currentPlayer] = true;
		
		currentPlayer++;
		if (currentPlayer == players.size()) currentPlayer = 0;
		return true;
	}


	private boolean didPlayerWin() {
		return !(purses[currentPlayer] == 6);
	}
}
