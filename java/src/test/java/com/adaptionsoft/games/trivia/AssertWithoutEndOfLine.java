package com.adaptionsoft.games.trivia;

import static org.junit.Assert.assertEquals;

public class AssertWithoutEndOfLine {

	public static void equals(String expected, String actual) {
		assertEquals(removeWindowsEndOfLine(expected),removeWindowsEndOfLine(actual));
	}
	
	private static String removeWindowsEndOfLine(String content) {
		return content.replace("\r", "");
	}

}
