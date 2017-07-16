package main.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import main.java.dao.UserDao;
import main.java.model.User;

@Service("userService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public void addUser(User u) {
		// TODO Auto-generated method stub
		userDao.addUser(u);
	}

	@Override
	public boolean doesUserExist(String username) {
		// TODO Auto-generated method stub
		return userDao.doesUserExist(username);
	}

	@Override
	public boolean validateUser(String username, String password) {
		// TODO Auto-generated method stub
		return userDao.validateUser(username, password);
	}
	
	
}
