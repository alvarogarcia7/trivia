package com.adaptionsoft.games.uglytrivia;

public class Player {

	public class Purse {

		private int value;

		public Purse(int i) {
			value = i;
		}

		public int getValue() {
			return value;
		}

		public void increase() {
			value++;
		}

	}

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
	private Purse purse;

	public Player(String playerName) {
		name = playerName;
		this.position = new Position(0);
		this.purse = new Purse(0);
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

	public Purse getPurse() {
		return purse;
	}

}
