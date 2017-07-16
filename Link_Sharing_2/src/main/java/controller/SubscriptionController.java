package main.java.controller;

import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import main.java.dto.SubscriptionDetails;
import main.java.dto.TopicDetails;
import main.java.enums.Seriousness;
import main.java.enums.Visibility;
import main.java.model.Subscription;
import main.java.model.Topic;
import main.java.model.User;
import main.java.service.SubscriptionService;
import main.java.service.TopicService;

@Controller
public class SubscriptionController {
	
	@Autowired
	private SubscriptionService subscriptionService;
	
	@RequestMapping(value="/subscribe" , method = RequestMethod.POST )
    @ResponseBody
    public void createTopic(@RequestBody String json) {
        
        System.out.print("Reached");
        System.out.println(json);
       json = URLDecoder.decode(json);
		System.out.println("new json" + json);
		Gson gson = new Gson();

		System.out.println("iside");
		SubscriptionDetails t = new Gson().fromJson(json, SubscriptionDetails.class);
//		User u = new User();
//		u.setUsername("vasu123");
//		u.setEmail("hgrbefv");
//		t.setCreatedBy(u);
//		t.setDateCreated(new Date());
//		t.setLastUpdated(new Date());
//		
		Subscription s = new Subscription();
		if(t.getSeriousness().equals("Casual")){
			s.setSeriousness(Seriousness.CASUAL);
		}
		else if(t.getSeriousness().equals("Serious")){
			s.setSeriousness(Seriousness.SERIOUS);
		}
		else{
			s.setSeriousness(Seriousness.VERY_SERIOUS);
		}
		s.setDateCreated(new Date());
		subscriptionService.addSubscription(s, t.getName(), t.getCreatedBy());
        
        
   }

	@RequestMapping(value="/subscribed/topics" , method = RequestMethod.GET , produces = "application/json")
    @ResponseBody
    public List<Topic> getSubscribedTopics() {
        
        System.out.print("Reached");
		System.out.println("iside");
		User u = new User();
		u.setUsername("karan_gupta");
		u.setEmail("hgrbefv");

		Subscription s = new Subscription();
		s.setDateCreated(new Date());
		List<Topic> topics = subscriptionService.getSubscribedTopics(u);
        
		for(Topic t : topics){
			System.out.println(t.getName());
			System.out.println(t.getCreatedBy().getUsername());
			
		}
		return topics;
   }
	
	@RequestMapping(value="/update/seriousness" , method = RequestMethod.POST , produces = "application/json")
    @ResponseBody
    public void updateSeriousness(@RequestBody String json) {
        
		 System.out.print("Reached");
	        System.out.println(json);
	       json = URLDecoder.decode(json);
			System.out.println("new json" + json);
			Gson gson = new Gson();

			System.out.println("iside");
			SubscriptionDetails t = new Gson().fromJson(json, SubscriptionDetails.class);

        System.out.print("Reached");
		System.out.println("iside");
		User u = new User();
		u.setUsername("karan_gupta");
		u.setEmail("hgrbefv");

		Subscription s = new Subscription();
		s.setDateCreated(new Date());
		List<Topic> topics = subscriptionService.getSubscribedTopics(u);
        
		
   }

}
