package com.adaptionsoft.games.uglytrivia;

import java.util.HashMap;
import java.util.Map;

public class QuestionPool{
	
	private static final Topic ROCK_TOPIC = new Topic("Rock");
	private static final Topic SPORTS_TOPIC = new Topic("Sports");
	private static final Topic SCIENCE_TOPIC = new Topic("Science");
	private static final Topic POP_TOPIC = new Topic("Pop");

	private Map<String, Questions> value = new HashMap<String, Questions>();
	private final int size;

	public QuestionPool(int size) {
		this.size = size;
	}

	private void add(String topicDescription, Questions questions) {
		this.value.put(topicDescription, questions);
	}

	private String getQuestionFor(Topic topic) {
		return value.get(topic.getValue()).removeFirst();
	}

	private void add(Topic topic, Questions questions) {
		add(topic.getValue(), questions);
	}

	public void addHowManyQuestionsForTopic(Topic topic) {
		add(topic, new Questions(topic, size));
	}

	public Topic getTopicForPlace(int playerPlace) {
		//TODO this is the current playerPlace % the size of question topics (currently 4)
		final int topicSize = value.keySet().size();
		if (playerPlace % topicSize == 0) return POP_TOPIC;
		if (playerPlace % topicSize == 1) return SCIENCE_TOPIC;
		if (playerPlace % topicSize == 2) return SPORTS_TOPIC;
		return ROCK_TOPIC;
	}

	public String getQuestionForPlace(int i) {
		return getQuestionFor(getTopicForPlace(i));
	}

	public void createQuestions() {
		addHowManyQuestionsForTopic(POP_TOPIC);
		addHowManyQuestionsForTopic(SCIENCE_TOPIC);
		addHowManyQuestionsForTopic(SPORTS_TOPIC);
		addHowManyQuestionsForTopic(ROCK_TOPIC);
	}

}
