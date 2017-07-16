package main.java.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import main.java.model.LinkResource;

@Repository("resourceDao")
public class ResourceDaoImpl implements ResourceDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addResource(LinkResource l) {
		// TODO Auto-generated method stub
		Session s = sessionFactory.openSession();
		s.beginTransaction();
		s.save(l);
		s.getTransaction().commit();
		s.close();
	}

}
