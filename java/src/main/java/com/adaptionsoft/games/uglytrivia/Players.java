package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;

import com.adaptionsoft.games.uglytrivia.Player.Position;

//TODO find better name rather than player+s (plural)
public class Players {
	private static final long serialVersionUID = 3434721441550093656L;
	
	private final ArrayList<Player> value;
	int[] purses = new int[6];

	// TODO AGB this should be a pointer instead of its position - need to
	// remove all access to its index before
	private int currentPlayer = 0;
	
	public Players(){
		value = new ArrayList<Player>();
	}

	public void add(Player player){
		value.add(player);
		setInitialPurseForPlayer();
		initializePosition();
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

	public Position getCurrentPlayerPlace() {
		return value.get(currentPlayer()).getPosition();
	}

	public void updateCurrentPlayerPlaceBy(PositionIncrease positionIncrease) {
		value.get(currentPlayer()).add(positionIncrease);
	}

	public void initializePosition() {
		value.get(currentPlayer()).initialize();
	}

	public void increaseCurrentPlayerPurse() {
		purses[currentPlayer()]++;
	}

	public int getCurrentPlayerPurse() {
		return purses[currentPlayer()];
	}

	public void setInitialPurseForPlayer() {
		purses[size()] = 0;
	}
}
