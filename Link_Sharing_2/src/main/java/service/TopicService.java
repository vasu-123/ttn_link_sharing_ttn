package main.java.service;

import java.util.List;

import main.java.dto.TopicDetails;
import main.java.model.Topic;
import main.java.model.User;

public interface TopicService {
	public boolean createTopic(Topic t);
	public void updateTopicDate(TopicDetails t);
	public List<Topic> getCreatedTopics(User u);
    public Topic getTopic(String topic_name , String createdBy);
}
