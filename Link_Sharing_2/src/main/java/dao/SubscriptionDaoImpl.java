package main.java.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import main.java.dto.TopicDetails;
import main.java.enums.Seriousness;
import main.java.model.Subscription;
import main.java.model.Topic;
import main.java.model.User;

@Repository("subscriptionDao")
public class SubscriptionDaoImpl implements SubscriptionDao {

	@Autowired
	private SessionFactory sessionFactory ;
	@Override
	public void addSubscription(Subscription sub) {
		// TODO Auto-generated method stub
		Session s = sessionFactory.openSession();
		s.beginTransaction();
		s.save(sub);
		s.getTransaction().commit();
		s.close();
	}
	@Override
	public List<Topic> getSubscribedTopics(User u) {
		// TODO Auto-generated method stub
		Session s = sessionFactory.openSession();
		s.beginTransaction();
		System.out.println("Reached here");
		
//		Query q = s.createQuery("select Topic.name , Topic.id , Topic.visibility , Topic.createdBy , "
//				+ "Topic.dateCreated , Topic.lastUpdated from Topic inner join Subscription on Topic.id=Subscription.topic");
//		
//		Query q = s.createSQLQuery("select topic.name , topic.id , topic.visibility , topic.createdBy ,"
//				+ "topic.dateCreated , topic.lastUpdated from topic inner join "
//				+ "subscription on topic.id = subscription.topic_id");
		
		Query q = s.createQuery("select t from Topic t inner join Subscription s on t.id = s.topic where s.user=? ");
		q.setString(0, u.getUsername());
		List<Topic> topics = (List<Topic>) q.list();
		
		return topics;
	}
	
	@Override
	public void updateSeriousness(User u , TopicDetails t , Seriousness ser){
		// TODO Auto-generated method stub
		Session s = sessionFactory.openSession();
		s.beginTransaction();
		Query q = s.createQuery("update Seriousness set seriousness=? where user=?");
		q.setString(0, ser.toString());
		q.setString(1, u.getUsername());
		
		q.executeUpdate();
	}

}
