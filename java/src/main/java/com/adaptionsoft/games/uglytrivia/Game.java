package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Game {

	public class QuestionPool{

		private Map<String, Questions> value = new HashMap<String, Questions>();

		private void add(String topicDescription, Questions questions) {
			this.value.put(topicDescription, questions);
		}

		public String getQuestionFor(String currentCategory) {
			return value.get(currentCategory).removeFirst();
		}

		public void add(Topic topic, Questions questions) {
			add(topic.getValue(), questions);
		}

	}

	private static final Topic ROCK_TOPIC = new Topic("Rock");
	private static final Topic SPORTS_TOPIC = new Topic("Sports");
	private static final Topic SCIENCE_TOPIC = new Topic("Science");
	private static final Topic POP_TOPIC = new Topic("Pop");
	private static final int QUESTION_POOL_SIZE = 50;
	ArrayList players = new ArrayList();
    int[] places = new int[6];
    int[] purses  = new int[6];
    boolean[] inPenaltyBox  = new boolean[6];
    
    Questions popQuestions = new Questions(POP_TOPIC, QUESTION_POOL_SIZE);
    Questions scienceQuestions = new Questions(SCIENCE_TOPIC, QUESTION_POOL_SIZE);
    Questions sportsQuestions = new Questions(SPORTS_TOPIC, QUESTION_POOL_SIZE);
    Questions rockQuestions = new Questions(ROCK_TOPIC, QUESTION_POOL_SIZE);
    
    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;
	private QuestionPool questionPool = new QuestionPool();
    
    public  Game(){
    	createQuestions();
    }

	private void createQuestions() {
		questionPool.add(POP_TOPIC, popQuestions);
		questionPool.add(SCIENCE_TOPIC, scienceQuestions);
		questionPool.add(SPORTS_TOPIC, sportsQuestions);
		questionPool.add(ROCK_TOPIC, rockQuestions);
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
		askQuestionFor(currentCategory());		
	}

	private void askQuestionFor(String currentCategory) {
		String questionDescription = getQuestionFor(currentCategory);
		System.out.println(questionDescription);
	}

	private String getQuestionFor(String currentCategory) {
		return questionPool.getQuestionFor(currentCategory);
	}
	
	
	private String currentCategory() {
		return currentTopic().getValue();
	}

	private Topic currentTopic() {
		//TODO this is the currentPlayerPlace % the size of question topics (currently 4)
		int currentPlayerPlace = places[currentPlayer];
		if (currentPlayerPlace % 4 == 0) return POP_TOPIC;
		if (currentPlayerPlace % 4 == 1) return SCIENCE_TOPIC;
		if (currentPlayerPlace % 4 == 2) return SPORTS_TOPIC;
		return ROCK_TOPIC;
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
