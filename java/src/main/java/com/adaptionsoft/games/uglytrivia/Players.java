package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;

//TODO find better name rather than player+s (plural)
public class Players {
	private static final long serialVersionUID = 3434721441550093656L;
	
	private final ArrayList<String> value;

	int currentPlayer = 0;
	
	public Players(){
		value = new ArrayList<String>();
	}

	public void add(Player player){
		value.add(player.getValue());
	}
	
	public int size() {
		return value.size();
	}

	public String get(int currentPlayer) {
		return value.get(currentPlayer);
	}
	
}
