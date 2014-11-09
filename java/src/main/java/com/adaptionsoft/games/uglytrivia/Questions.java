package com.adaptionsoft.games.uglytrivia;

import java.util.LinkedList;

public class Questions {
	private final LinkedList<String> value = new LinkedList<String>();
	private final Topic topic;

	public Questions(Topic topic) {
		this.topic = topic;
	}
	
	public Questions(){
		topic = null;
	}

	public void add(String string) {
		value.add(string);
	}

	public String removeFirst() {
		return value.removeFirst();
	}

	private void addWithName(Topic topic) {
		add(topic.getValue() + " Question " + value.size());
	}

	public void addWithDescriptionFromTopic() {
		addWithName(topic);
	}
	
}
