package main.java.dao;

import java.util.List;

import main.java.dto.TopicDetails;
import main.java.model.Topic;
import main.java.model.User;

public interface TopicDao {
public boolean createTopic(Topic t);
public void updateTopicDate(TopicDetails t);
public Topic getTopic(String topic_name , String createdBy);
public List<Topic> getCreatedTopics(User u);

}
