package com.adaptionsoft.games.trivia;

public class MockSystemOutput {
	private final StringBuilder stringBuilder= new StringBuilder();

	public void write(int b) {
		stringBuilder.append((char) b);
	}
	
	@Override
	public String toString(){
		return stringBuilder.toString();
	}
}