package main.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import main.java.dao.SubscriptionDao;
import main.java.dao.TopicDao;
import main.java.dto.TopicDetails;
import main.java.enums.Seriousness;
import main.java.model.Subscription;
import main.java.model.Topic;
import main.java.model.User;

@Service("subscriptionService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SubscriptionServiceImpl implements SubscriptionService{

    @Autowired
    private SubscriptionDao subscriptionDao;
    
    @Autowired
    private TopicDao topicDao;
   
    
	@Override
	public void addSubscription(Subscription s , String topic_name , String createdBy) {
		// TODO Auto-generated method stub
		s.setTopic(topicDao.getTopic(topic_name, createdBy));
		subscriptionDao.addSubscription(s);
	}


	@Override
	public List<Topic> getSubscribedTopics(User u) {
		// TODO Auto-generated method stub
		return subscriptionDao.getSubscribedTopics(u);
	}


	@Override
	public void updateSeriousness(User u, TopicDetails t, Seriousness s) {
		// TODO Auto-generated method stub
		subscriptionDao.updateSeriousness(u, t, s);
	}
	

}
