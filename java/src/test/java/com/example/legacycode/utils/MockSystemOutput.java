package com.example.legacycode.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class MockSystemOutput {
	private final StringBuilder stringBuilder= new StringBuilder();

	public void write(int b) {
		stringBuilder.append((char) b);
	}
	
	@Override
	public String toString(){
		return stringBuilder.toString();
	}

	public static MockSystemOutput inject() {
		final MockSystemOutput mockOutput = new MockSystemOutput();

		System.setOut(new PrintStream(new OutputStream() {

			@Override
			public void write(int b) throws IOException {
				mockOutput.write(b);

			}
		}));
		return mockOutput;
	}
}