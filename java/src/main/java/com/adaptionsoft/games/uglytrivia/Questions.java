package com.adaptionsoft.games.uglytrivia;

import java.util.LinkedList;

public class Questions {
	private final LinkedList<String> value = new LinkedList<String>();
	private final Topic topic;

	public Questions(Topic topic, int questionPoolSize) {
		this.topic = topic;
		
		for (int i = 0; i < questionPoolSize; i++) {
			addWithDescriptionFromTopic();
    	}
	}

	public String removeFirst() {
		return value.removeFirst();
	}

	private void addWithName(Topic topic) {
		value.add(topic.getValue() + " Question " + value.size());
	}

	public void addWithDescriptionFromTopic() {
		addWithName(topic);
	}
	
}
