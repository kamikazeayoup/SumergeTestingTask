package com.ayoub.myproject.topic;

import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TopicController  {

    @Autowired
    private TopicService topicService;

    @GetMapping("/topics")
    public List<Topic> GetTopics(){
        return this.topicService.GetAlTopics();
    }
    @RequestMapping("/topics/{id}")
    public Optional<Topic> GetTopic(@PathVariable("id") String id) throws InavlidTopicIdException {
        return this.topicService.GetTopic(id);

    }

    @RequestMapping(method = RequestMethod.POST , value = "/topics" )
    public void AddTopic(@RequestBody Topic topic){
        this.topicService.AddTopic(topic);
    }
    @RequestMapping(method = RequestMethod.PUT , value = "/topics/{id}" )
    public void updateTopic(@RequestBody Topic topic , @PathVariable String id){
        this.topicService.updateTopic(id , topic);
    }

    @RequestMapping(method = RequestMethod.DELETE , value = "/topics/{id}" )
    public void deleteTopic(@PathVariable String id){
        this.topicService.deleteTopic(id);
    }


}