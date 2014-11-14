package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;

//TODO find better name rather than player+s (plural)
public class Players {
	private static final long serialVersionUID = 3434721441550093656L;
	
	private final ArrayList<Player> value;

	private int currentPlayer = 0;
	
	int[] places = new int[6];

	public Players(){
		value = new ArrayList<Player>();
	}

	public void add(Player player){
		value.add(player);
	}
	
	public int size() {
		return value.size();
	}

	public int currentPlayer(){
		return currentPlayer;
	}

	private void makeTheCurrentPlayerTheFirstOne() {
		currentPlayer = 0;
	}

	public String getCurrentPlayer() {
		return value.get(currentPlayer).getValue();
	}

	private boolean isTheCurrentPlayerTheLastOne() {
		return currentPlayer() == value.size();
	}

	public void endOfCurrentTurn() {
		currentPlayer++;
		if (isTheCurrentPlayerTheLastOne()) {
			makeTheCurrentPlayerTheFirstOne();
		}
	}

	public int getCurrentPlayerPlace() {
		if (places[currentPlayer()] == value.get(currentPlayer()).getPosition().getValue()) {
			return value.get(currentPlayer()).getPosition().getValue();
		}

		return places[currentPlayer()];
	}

	public void updateCurrentPlayerPlaceBy(PositionIncrease positionIncrease) {
		places[currentPlayer()] = getCurrentPlayerPlace() + positionIncrease.getValue();
		value.get(currentPlayer()).add(positionIncrease);
	}

	public void initializePosition() {
		value.get(currentPlayer()).initialize();
	}
}
