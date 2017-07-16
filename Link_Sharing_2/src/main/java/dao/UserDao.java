package main.java.dao;

import main.java.model.User;

public interface UserDao {
	
	public boolean doesUserExist(String username);
	public void addUser(User u);
	public boolean validateUser(String username , String password);
	public boolean isActiveUser(User u);
}
