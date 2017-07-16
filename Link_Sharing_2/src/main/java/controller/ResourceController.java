package main.java.controller;

import java.net.URLDecoder;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import main.java.dto.LinkResourceWithTopicDetails;
import main.java.model.LinkResource;
import main.java.model.Topic;
import main.java.model.User;
import main.java.service.ResourceService;
import main.java.service.TopicService;
import main.java.service.UserService;

@Controller
public class ResourceController {

	@Autowired
	private ResourceService resourceService;
	
	@Autowired
	private TopicService topicService;
	
    @RequestMapping(value="/resource/add" , method = RequestMethod.POST)
    @ResponseBody
    public void addLinkResource(@RequestBody String json) {
        
        System.out.print("Reached");
        System.out.println(json);
       json = URLDecoder.decode(json);
		System.out.println("new json" + json);
		Gson gson = new Gson();
		LinkResourceWithTopicDetails l = new Gson().fromJson(json, LinkResourceWithTopicDetails.class);
		
		Topic t = prepareTopic(l);
        LinkResource lr = prepareLinkResource(l,t);
        System.out.println(lr.getTopic().getName());
        System.out.println(lr.getTopic().getCreatedBy().getUsername());
        resourceService.addResource(lr);
		System.out.println("iside");
		
        
   }
    
    public Topic prepareTopic(LinkResourceWithTopicDetails l){
    	Topic t = topicService.getTopic(l.getTopic_name(), l.getTopicCreatedBy());
    	return t;
    }
    
    public LinkResource prepareLinkResource(LinkResourceWithTopicDetails l , Topic t){
    	LinkResource lr = new LinkResource();
    	User u = new User();
    	u.setUsername("karan_gupta");
    	lr.setCreatedBy(u);
    	lr.setDateCreated(new Date());
    	lr.setDescription(l.getDescription());
    	lr.setLastUpdated(new Date());
    	lr.setTopic(t);
    	lr.setUrl(l.getUrl());
    	return lr ;
    }

}
