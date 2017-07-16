package main.java.dao;

import java.util.List;

import main.java.dto.TopicDetails;
import main.java.enums.Seriousness;
import main.java.model.Subscription;
import main.java.model.Topic;
import main.java.model.User;

public interface SubscriptionDao {

	public void addSubscription(Subscription s );
	public List<Topic> getSubscribedTopics(User u);
	public void updateSeriousness(User u , TopicDetails t , Seriousness s);
}
