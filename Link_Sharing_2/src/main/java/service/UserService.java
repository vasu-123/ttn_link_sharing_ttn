package main.java.service;

import main.java.model.User;

public interface UserService {

	public boolean doesUserExist(String username);
	public void addUser(User u);
	public boolean validateUser(String username , String password);

}
