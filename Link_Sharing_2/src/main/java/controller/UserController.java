package main.java.controller;

import java.net.URLDecoder;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;


import main.java.model.User;
import main.java.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
    @RequestMapping(value="/user/register" , method = RequestMethod.POST)
    @ResponseBody
    public void addUser(@RequestBody String json) {
        
        System.out.print("Reached");
        System.out.println(json);
       json = URLDecoder.decode(json);
		System.out.println("new json" + json);
		Gson gson = new Gson();

		System.out.println("iside");
		User u = new Gson().fromJson(json, User.class);
		System.out.println(u.getEmail());
		System.out.println(u.getFirstName());
		System.out.println(u.getUsername());
		u.setDateCreated(new Date());
		u.setLastUpdated(new Date());
		u.setAdmin(false);
		u.setActive(false);
		
       
		if(!userService.doesUserExist(u.getUsername())){
            userService.addUser(u);
            System.out.println("Added");
        }
        
        else{
        	System.out.println("User already exists");
        }
        
        
   }
    
    
}