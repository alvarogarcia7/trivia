package com.adaptionsoft.games.trivia;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.util.Random;

import org.junit.Test;

import com.adaptionsoft.games.uglytrivia.Game;

public class GameAcceptanceTest {

	@Test
	public void acceptanceTestWithSeed1() {
		MockSystemOutput mockOutput = MockSystemOutput.inject();
		
		Game aGame = new Game();

		aGame.add("Chet");
		aGame.add("Pat");
		aGame.add("Sue");

		Random rand = new Random(1L);

		boolean notAWinner;
		do {

			aGame.roll(rand.nextInt(5) + 1);

			if (rand.nextInt(9) == 7) {
				notAWinner = aGame.wrongAnswer();
			} else {
				notAWinner = aGame.wasCorrectlyAnswered();
			}

		} while (notAWinner);

		assertEqualsOSEndOfLineIndependent(expectedOutputForGameWithSeed1(), mockOutput.toString());

		System.err.println(mockOutput.toString());

	}

	private void assertEqualsOSEndOfLineIndependent(String gameWithSeed1, String string) {
		assertEquals(removeWindowsEndOfLine(gameWithSeed1),removeWindowsEndOfLine(string));
	}

	private String removeWindowsEndOfLine(String gameWithSeed1) {
		return gameWithSeed1.replace("\r", "");
	}

	private String expectedOutputForGameWithSeed1() {
		return "Chet was added\n" +
				"They are player number 1\n" +
				"Pat was added\n" +
				"They are player number 2\n" +
				"Sue was added\n" +
				"They are player number 3\n" +
				"Chet is the current player\n" +
				"They have rolled a 1\n" +
				"Chet's new location is 1\n" +
				"The category is Science\n" +
				"Science Question 0\n" +
				"Answer was corrent!!!!\n" +
				"Chet now has 1 Gold Coins.\n" +
				"Pat is the current player\n" +
				"They have rolled a 3\n" +
				"Pat's new location is 3\n" +
				"The category is Rock\n" +
				"Rock Question 0\n" +
				"Answer was corrent!!!!\n" +
				"Pat now has 1 Gold Coins.\n" +
				"Sue is the current player\n" +
				"They have rolled a 5\n" +
				"Sue's new location is 5\n" +
				"The category is Science\n" +
				"Science Question 1\n" +
				"Answer was corrent!!!!\n" +
				"Sue now has 1 Gold Coins.\n" +
				"Chet is the current player\n" +
				"They have rolled a 5\n" +
				"Chet's new location is 6\n" +
				"The category is Sports\n" +
				"Sports Question 0\n" +
				"Answer was corrent!!!!\n" +
				"Chet now has 2 Gold Coins.\n" +
				"Pat is the current player\n" +
				"They have rolled a 4\n" +
				"Pat's new location is 7\n" +
				"The category is Rock\n" +
				"Rock Question 1\n" +
				"Answer was corrent!!!!\n" +
				"Pat now has 2 Gold Coins.\n" +
				"Sue is the current player\n" +
				"They have rolled a 5\n" +
				"Sue's new location is 10\n" +
				"The category is Sports\n" +
				"Sports Question 1\n" +
				"Question was incorrectly answered\n" +
				"Sue was sent to the penalty box\n" +
				"Chet is the current player\n" +
				"They have rolled a 3\n" +
				"Chet's new location is 9\n" +
				"The category is Science\n" +
				"Science Question 2\n" +
				"Answer was corrent!!!!\n" +
				"Chet now has 3 Gold Coins.\n" +
				"Pat is the current player\n" +
				"They have rolled a 3\n" +
				"Pat's new location is 10\n" +
				"The category is Sports\n" +
				"Sports Question 2\n" +
				"Question was incorrectly answered\n" +
				"Pat was sent to the penalty box\n" +
				"Sue is the current player\n" +
				"They have rolled a 3\n" +
				"Sue is getting out of the penalty box\n" +
				"Sue's new location is 1\n" +
				"The category is Science\n" +
				"Science Question 3\n" +
				"Answer was correct!!!!\n" +
				"Sue now has 2 Gold Coins.\n" +
				"Chet is the current player\n" +
				"They have rolled a 2\n" +
				"Chet's new location is 11\n" +
				"The category is Rock\n" +
				"Rock Question 2\n" +
				"Answer was corrent!!!!\n" +
				"Chet now has 4 Gold Coins.\n" +
				"Pat is the current player\n" +
				"They have rolled a 2\n" +
				"Pat is not getting out of the penalty box\n" +
				"Sue is the current player\n" +
				"They have rolled a 1\n" +
				"Sue is getting out of the penalty box\n" +
				"Sue's new location is 2\n" +
				"The category is Sports\n" +
				"Sports Question 3\n" +
				"Answer was correct!!!!\n" +
				"Sue now has 3 Gold Coins.\n" +
				"Chet is the current player\n" +
				"They have rolled a 5\n" +
				"Chet's new location is 4\n" +
				"The category is Pop\n" +
				"Pop Question 0\n" +
				"Answer was corrent!!!!\n" +
				"Chet now has 5 Gold Coins.\n" +
				"Pat is the current player\n" +
				"They have rolled a 4\n" +
				"Pat is not getting out of the penalty box\n" +
				"Sue is the current player\n" +
				"They have rolled a 3\n" +
				"Sue is getting out of the penalty box\n" +
				"Sue's new location is 5\n" +
				"The category is Science\n" +
				"Science Question 4\n" +
				"Answer was correct!!!!\n" +
				"Sue now has 4 Gold Coins.\n" +
				"Chet is the current player\n" +
				"They have rolled a 1\n" +
				"Chet's new location is 5\n" +
				"The category is Science\n" +
				"Science Question 5\n" +
				"Answer was corrent!!!!\n" +
				"Chet now has 6 Gold Coins.\n";
	}

}
