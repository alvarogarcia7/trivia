package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;

//TODO find better name rather than player+s (plural)
public class Players {
	private static final long serialVersionUID = 3434721441550093656L;
	
	private final ArrayList<String> value;

	private int currentPlayer = 0;
	
	public Players(){
		value = new ArrayList<String>();
	}

	public void add(Player player){
		value.add(player.getValue());
	}
	
	public int size() {
		return value.size();
	}

	public int currentPlayer(){
		return currentPlayer;
	}

	private void resetCurrentPlayer() {
		currentPlayer = 0;
	}

	public String getCurrentPlayer() {
		return value.get(currentPlayer);
	}

	private boolean isTheLastOnePlaying() {
		return currentPlayer() == value.size();
	}

	public void endOfCurrentTurn() {
		currentPlayer++;
		if (isTheLastOnePlaying()) {
			resetCurrentPlayer();
		}
	}
	
}
