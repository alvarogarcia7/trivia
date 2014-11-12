package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Game {

	private static final int QUESTION_POOL_SIZE = 50;
	Players players = new Players();
    int[] places = new int[6];
    int[] purses  = new int[6];
    boolean[] inPenaltyBox  = new boolean[6];
    
    boolean isGettingOutOfPenaltyBox;
	private QuestionPool questionPool = new QuestionPool(QUESTION_POOL_SIZE);
    
	private void createQuestions() {
    	//cf comments by @jbrains at https://github.com/alvarogarcia7/trivia/commit/b8f7c04e1b0d0b5e4477dc1a66edcc947ef25976#commitcomment-8505308
    	questionPool.createQuestions();
	}

	public boolean isPlayable() {
		return (howManyPlayers() >= 2);
	}

	public boolean add(String playerName) {
	    players.add(new Player(playerName));
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
		System.out.println(getCurrentPlayer() + " is the current player");
		System.out.println("They have rolled a " + roll);
		
		if (inPenaltyBox[players.currentPlayer()]) {
			if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;
				
				System.out.println(getCurrentPlayer() + " is getting out of the penalty box");
				places[players.currentPlayer()] = getCurrentPlayerPlace() + roll;
				if (getCurrentPlayerPlace() > 11) places[players.currentPlayer()] = getCurrentPlayerPlace() - 12;
				
				System.out.println(getCurrentPlayer() 
						+ "'s new location is " 
						+ getCurrentPlayerPlace());
				System.out.println("The category is " + currentCategory());
				askQuestion();
			} else {
				System.out.println(getCurrentPlayer() + " is not getting out of the penalty box");
				isGettingOutOfPenaltyBox = false;
				}
			
		} else {
		
			places[players.currentPlayer()] = getCurrentPlayerPlace() + roll;
			if (getCurrentPlayerPlace() > 11) places[players.currentPlayer()] = getCurrentPlayerPlace() - 12;
			
			System.out.println(getCurrentPlayer() 
					+ "'s new location is " 
					+ getCurrentPlayerPlace());
			System.out.println("The category is " + currentCategory());
			askQuestion();
		}
		
	}

	private String getCurrentPlayer() {
		return players.getCurrentPlayer();
	}

	private void askQuestion() {
		String questionDescription = questionPool.getQuestionForPlace(getCurrentPlayerPlace());
		System.out.println(questionDescription);		
	}

	private int getCurrentPlayerPlace() {
		return places[players.currentPlayer()];
	}

	private String currentCategory() {
		return currentTopic().getValue();
	}

	private Topic currentTopic() {
		//TODO AGB: this responsibility shouldn't be on the question pool but on the board
		return questionPool.getTopicForPlace(getCurrentPlayerPlace());
	}

	public boolean wasCorrectlyAnswered() {
		if (inPenaltyBox[players.currentPlayer()]){
			if (isGettingOutOfPenaltyBox) {
				System.out.println("Answer was correct!!!!");
				purses[players.currentPlayer()]++;
				System.out.println(getCurrentPlayer() 
						+ " now has "
						+ purses[players.currentPlayer()]
						+ " Gold Coins.");
				
				boolean winner = didPlayerWin();
				endOfCurrentPlayerTurn();
				
				return winner;
			} else {
				endOfCurrentPlayerTurn();
				return true;
			}
			
			
			
		} else {
		
			System.out.println("Answer was corrent!!!!");
			purses[players.currentPlayer()]++;
			System.out.println(getCurrentPlayer() 
					+ " now has "
					+ purses[players.currentPlayer()]
					+ " Gold Coins.");
			
			boolean winner = didPlayerWin();
			endOfCurrentPlayerTurn();
			
			return winner;
		}
	}

	private boolean isLastOnePlaying() {
		return players.isTheLastOnePlaying();
	}
	
	public boolean wrongAnswer(){
		System.out.println("Question was incorrectly answered");
		System.out.println(getCurrentPlayer()+ " was sent to the penalty box");
		inPenaltyBox[players.currentPlayer()] = true;
		
		endOfCurrentPlayerTurn();
		return true;
	}

	private void endOfCurrentPlayerTurn() {
		players.increaseCurrentPlayer();
		if (isLastOnePlaying()) players.resetCurrentPlayer();
	}


	private boolean didPlayerWin() {
		return !(purses[players.currentPlayer()] == 6);
	}

	public static Game withQuestions() {
		Game game = new Game();
		game.createQuestions();
		return game;
	}
}
