package main.java.service;

import java.util.Date;
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

@Service("topicService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class TopicServiceImpl implements TopicService {

	@Autowired
	private TopicDao topicDao;
	
	@Autowired
	private SubscriptionDao subscriptionDao ;
	
	@Override
	public boolean createTopic(Topic t) {
		// TODO Auto-generated method stub
		Subscription s = new Subscription();
		s.setDateCreated(new Date());
		s.setSeriousness(Seriousness.VERY_SERIOUS);
		s.setTopic(t);
		topicDao.createTopic(t);
		subscriptionDao.addSubscription(s);

		return true ;
	}

	@Override
	public void updateTopicDate(TopicDetails t) {
		// TODO Auto-generated method stub
		topicDao.updateTopicDate(t);
	}

	@Override
	public List<Topic> getCreatedTopics(User u) {
		// TODO Auto-generated method stub
		return topicDao.getCreatedTopics(u);
	}

	@Override
	public Topic getTopic(String topic_name, String createdBy) {
		// TODO Auto-generated method stub
		return topicDao.getTopic(topic_name, createdBy);
	}
	
	
	
	

}
