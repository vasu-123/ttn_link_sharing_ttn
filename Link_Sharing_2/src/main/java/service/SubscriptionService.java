package main.java.service;

import java.util.List;

import main.java.dto.TopicDetails;
import main.java.enums.Seriousness;
import main.java.model.Subscription;
import main.java.model.Topic;
import main.java.model.User;

public interface SubscriptionService {
	public void addSubscription(Subscription s , String topic_name , String createdBy);
	public List<Topic> getSubscribedTopics(User u);
	public void updateSeriousness(User u , TopicDetails t , Seriousness s);


}
