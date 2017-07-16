package main.java.controller;

import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import main.java.dto.TopicDetails;
import main.java.enums.Visibility;
import main.java.model.Topic;
import main.java.model.User;
import main.java.service.TopicService;
import main.java.service.UserService;

@Controller
public class TopicController {

	@Autowired
	private TopicService topicService;
	
    @RequestMapping(value="/topic/create" , method = RequestMethod.POST)
    @ResponseBody
    public void createTopic(@RequestBody String json) {
        
        System.out.print("Reached");
        System.out.println(json);
       json = URLDecoder.decode(json);
		System.out.println("new json" + json);
		Gson gson = new Gson();

		System.out.println("iside");
		Topic t = new Gson().fromJson(json, Topic.class);
		User u = new User();
		u.setUsername("karan_gupta");
		u.setEmail("hgrbefv");
		t.setCreatedBy(u);
		t.setDateCreated(new Date());
		t.setLastUpdated(new Date());
		
		Type type = new TypeToken<Map<String, String>>(){}.getType();
		Map<String, String> myMap = gson.fromJson(json, type);
		System.out.println(myMap.get("visibility"));
		if(myMap.get("visibility").equals("Private")){
			t.setVisibility(Visibility.PRIVATE);
		}
		
		else{
			t.setVisibility(Visibility.PUBLIC);
		}
		topicService.createTopic(t);
        
        
   }
    
    @RequestMapping(value="/topic/update" , method = RequestMethod.POST)
    @ResponseBody
    public void updateTopic(@RequestBody String json) {
        
        System.out.print("Reached");
        System.out.println(json);
       json = URLDecoder.decode(json);
		System.out.println("new json" + json);
		Gson gson = new Gson();

		System.out.println("iside");
		TopicDetails t = new Gson().fromJson(json, TopicDetails.class);
		System.out.println(t.getName() + " " + t.getCreatedBy());
//		User u = new User();
//		u.setUsername("vasu123");
//		u.setEmail("hgrbefv");
//		t.setCreatedBy(u);
		//t.setDateCreated(new Date());
		
		topicService.updateTopicDate(t);;
        
        
   }

    
    @RequestMapping(value="/created/topics" , method = RequestMethod.GET)
    @ResponseBody
    public List<Topic> getCreatedTopics() {
        
        System.out.print("Reached");
        		System.out.println("iside");
		
        		User u = new User();
        		u.setUsername("karan_gupta");
		
        		return topicService.getCreatedTopics(u);
        
        
   }

    
    
}
