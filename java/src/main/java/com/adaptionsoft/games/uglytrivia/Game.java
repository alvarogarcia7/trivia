package com.adaptionsoft.games.uglytrivia;


public class Game {


	private static final int QUESTION_POOL_SIZE = 50;
	Players players = new Players();
	boolean isGettingOutOfPenaltyBox;
	private QuestionPool questionPool = new QuestionPool(QUESTION_POOL_SIZE);
    
	private void createQuestions() {
    	//cf comments by @jbrains at https://github.com/alvarogarcia7/trivia/commit/b8f7c04e1b0d0b5e4477dc1a66edcc947ef25976#commitcomment-8505308
    	questionPool.createQuestions();
	}

	public boolean isPlayable() {
		return (players.size() >= 2);
	}

	public boolean add(String playerName) {
	    players.add(new Player(playerName));
	    
	    System.out.println(playerName + " was added");
	    System.out.println("They are player number " + players.size());
		return true;
	}

	public void roll(int roll) {
		System.out.println(getCurrentPlayer() + " is the current player");
		System.out.println("They have rolled a " + roll);
		
		if (isInPenaltyBox()) {
			if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;
				
				System.out.println(getCurrentPlayer() + " is getting out of the penalty box");
				rollDieAndAskQuestion(roll);
			} else {
				System.out.println(getCurrentPlayer() + " is not getting out of the penalty box");
				isGettingOutOfPenaltyBox = false;
				}
			
		} else {
		
			rollDieAndAskQuestion(roll);
		}
		
	}

	public boolean setInPenaltyBox() {
		return players.setCurrentPlayerInPenaltyBox();
	}

	public boolean isInPenaltyBox() {
		return players.isCurrentPlayerInPenaltyBox();
	}

	private void rollDieAndAskQuestion(int roll) {
		setCurrentPlayerPlace(roll);
		if (getCurrentPlayerPlace() > 11) setBackCurrentPlayerPlaceBy12();
		
		System.out.println(getCurrentPlayer() 
				+ "'s new location is " 
				+ getCurrentPlayerPlace());
		System.out.println("The category is " + currentCategory());
		askQuestion();
	}

	private void setBackCurrentPlayerPlaceBy12() {
		setCurrentPlayerPlace(-12);
	}

	private void setCurrentPlayerPlace(int roll) {
		players.updateCurrentPlayerPlaceBy(new PositionIncrease(roll));
	}

	private String getCurrentPlayer() {
		return players.getCurrentPlayerValue();
	}

	private void askQuestion() {
		String questionDescription = questionPool.getQuestionForPlace(getCurrentPlayerPlace());
		System.out.println(questionDescription);		
	}

	private int getCurrentPlayerPlace() {
		return players.getCurrentPlayerPlace().getValue();
	}

	private String currentCategory() {
		return currentTopic().getValue();
	}

	private Topic currentTopic() {
		//TODO AGB: this responsibility shouldn't be on the question pool but on the board
		return questionPool.getTopicForPlace(getCurrentPlayerPlace());
	}

	public boolean wasCorrectlyAnswered() {
		if (isInPenaltyBox()){
			if (isGettingOutOfPenaltyBox) {
				System.out.println("Answer was correct!!!!");
				players.increaseCurrentPlayerPurse();
				System.out.println(getCurrentPlayer() 
						+ " now has "
						+ getCurrentPlayerPurse()
						+ " Gold Coins.");
				
				boolean winner = didPlayerWin();
				players.endOfCurrentTurn();
				
				return winner;
			} else {
				players.endOfCurrentTurn();
				return true;
			}
			
			
			
		} else {
		
			System.out.println("Answer was corrent!!!!");
			players.increaseCurrentPlayerPurse();
			System.out.println(getCurrentPlayer() 
					+ " now has "
					+ getCurrentPlayerPurse()
					+ " Gold Coins.");
			
			boolean winner = didPlayerWin();
			players.endOfCurrentTurn();
			
			return winner;
		}
	}

	private int getCurrentPlayerPurse() {
		return players.getCurrentPlayerPurse();
	}

	public boolean wrongAnswer(){
		System.out.println("Question was incorrectly answered");
		System.out.println(getCurrentPlayer()+ " was sent to the penalty box");
		setInPenaltyBox();
		
		players.endOfCurrentTurn();
		return true;
	}

	private boolean didPlayerWin() {
		return !(getCurrentPlayerPurse() == 6);
	}

	public static Game withQuestions() {
		Game game = new Game();
		game.createQuestions();
		return game;
	}
}
