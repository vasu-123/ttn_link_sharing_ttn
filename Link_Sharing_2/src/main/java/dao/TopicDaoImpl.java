package main.java.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import main.java.dto.TopicDetails;
import main.java.model.Topic;
import main.java.model.User;

@Repository("topicDao")
public class TopicDaoImpl implements TopicDao {
	
	@Autowired
	 private SessionFactory sessionFactory;

	@Override
	public boolean createTopic(Topic t) {
		// TODO Auto-generated method stub
		
		Session s = sessionFactory.openSession();
		s.beginTransaction();
		s.save(t);
		s.getTransaction().commit();
		s.close();
		
		return true;
	}

	@Override
	public void updateTopicDate(TopicDetails t) {
		// TODO Auto-generated method stub
		Session s = sessionFactory.openSession();
		s.beginTransaction();
		Query q = s.createQuery("update Topic set lastUpdated=? where name=? and createdBy=?");
		q.setDate(0, new Date());
		q.setString(1, t.getName());
		q.setString(2, t.getCreatedBy());
		int result = q.executeUpdate();
		s.getTransaction().commit();
		s.close();
		
		
	}

	@Override
	public Topic getTopic(String topic_name, String createdBy) {
		// TODO Auto-generated method stub
		Session s = sessionFactory.openSession();
		s.beginTransaction();
		Query q = s.createQuery("from Topic where name=? and createdBy=?");
		q.setString(0, topic_name);
		q.setString(1, createdBy);
		List<Topic> topic = q.list();
		return topic.get(0);
	}

	@Override
	public List<Topic> getCreatedTopics(User u) {
		// TODO Auto-generated method stub
		
		Session s = sessionFactory.openSession();
		s.beginTransaction();
		Query q = s.createQuery("from Topic where createdBy=?");
		q.setString(0, u.getUsername());
		
		return q.list();
		
	}

}
