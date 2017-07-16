package main.java.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.stereotype.Repository;

import main.java.model.User;


@Repository("userDao")
public class UserDaoImpl implements UserDao {

	@Autowired
	 private SessionFactory sessionFactory;

	@Override
	public void addUser(User u) {
		// TODO Auto-generated method stub
		Session s = sessionFactory.openSession();
		s.beginTransaction();
		s.save(u);
		s.getTransaction().commit();
		s.close();
		
		
	}

	@Override
	public boolean doesUserExist(String username) {
		// TODO Auto-generated method stub
		Session s = sessionFactory.openSession();
		s.beginTransaction();
		
	//	System.out.println(username);
		Query query = s.createQuery("select username FROM User where username=?");
		query.setString(0, username);
		List<String> userNames = query.list();
		
		if(userNames.size() > 0)
			return true;
		
		return false;
	}

	@Override
	public boolean validateUser(String username, String password) {
		// TODO Auto-generated method stub
		
		Session s = sessionFactory.openSession();
		s.beginTransaction();
		
		Query query = s.createQuery("select username from User where username = ? and password = ?");
		query.setString(0, username);
		query.setString(1, password);
		
		List<String> userNames = query.list();
		
		if(userNames.size() > 0)
			return true;
		
		return false;
	}

	@Override
	public boolean isActiveUser(User u) {
		// TODO Auto-generated method stub
		
		Session s = sessionFactory.openSession();
		s.beginTransaction();
		Query query = s.createQuery("select username from User where username=? and isActive=true");
		List<String> userNames = query.list();
		
		if(userNames.size() > 0)
			return true;
		
		return false;
	}
	
		
	

}
