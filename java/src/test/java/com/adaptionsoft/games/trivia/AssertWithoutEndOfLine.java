package com.adaptionsoft.games.trivia;

import static org.junit.Assert.assertEquals;

public class AssertWithoutEndOfLine {

	public static void equals(String gameWithSeed1, String string) {
		assertEquals(removeWindowsEndOfLine(gameWithSeed1),removeWindowsEndOfLine(string));
	}
	
	private static String removeWindowsEndOfLine(String gameWithSeed1) {
		return gameWithSeed1.replace("\r", "");
	}

}
