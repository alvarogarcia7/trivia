package com.adaptionsoft.games.uglytrivia;

public class Player {

	public class Position {

		private final int value;

		public Position(int value) {
			this.value = value;
		}

		public Position add(PositionIncrease positionIncrease) {
			return new Position(value + positionIncrease.getValue());
		}

		public int getValue() {
			return value;
		}

	}

	private final String name;
	private Position position;

	public Player(String playerName) {
		name = playerName;
		this.position = new Position(0);
	}

	public String getValue() {
		return name;
	}

	public void add(PositionIncrease positionIncrease) {
		position = position.add(positionIncrease);
	}

	public Position getPosition() {
		return position;
	}

	public void initialize() {
		this.position = new Position(0);
	}

}
