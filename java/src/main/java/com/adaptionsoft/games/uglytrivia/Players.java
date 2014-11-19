package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;

import com.adaptionsoft.games.uglytrivia.Player.Position;

//TODO find better name rather than player+s (plural)
public class Players {
	private static final long serialVersionUID = 3434721441550093656L;
	
	private final ArrayList<Player> value;

	public boolean[] inPenaltyBox = new boolean[6];

	// TODO AGB this should be a pointer instead of its position - need to
	// remove all access to its index before
	private int currentPlayer = 0;
	
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

	public String getCurrentPlayerValue() {
		return getCurrentPlayer().getValue();
	}

	private Player getCurrentPlayer() {
		return value.get(currentPlayer());
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
		return getCurrentPlayer().getPosition();
	}

	public void updateCurrentPlayerPlaceBy(PositionIncrease positionIncrease) {
		getCurrentPlayer().add(positionIncrease);
	}

	public void increaseCurrentPlayerPurse() {
		getCurrentPlayer().getPurse().increase();
	}

	public int getCurrentPlayerPurse() {
		return getCurrentPlayer().getPurse().getValue();
	}

	public boolean setNotInPenaltyBox() {
		return inPenaltyBox[size()] = false;
	}

	public boolean setInPenaltyBox() {
		return inPenaltyBox[currentPlayer()] = true;
	}

	public boolean isInPenaltyBox() {
		return inPenaltyBox[currentPlayer()];
	}

}
