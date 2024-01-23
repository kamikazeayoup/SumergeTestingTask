package com.ayoub.myproject.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TopicService {
    @Autowired
private TopicRepository topicRepository;

    public List<Topic> GetAlTopics(){
        List<Topic> topics = new ArrayList<>();
        topicRepository.findAll().forEach(topics::add);
        return topics;
    }


    public Optional<Topic> GetTopic (String id) throws InavlidTopicIdException {
        if (Integer.parseInt(id) < 0)
            throw new InavlidTopicIdException("Cannot Have Negative Id!");
         return topicRepository.findById(id);
    }

    public void AddTopic(Topic topic){

        topicRepository.save(topic);
    }
    public void updateTopic(String id , Topic topic){

        topicRepository.save(topic);
    }
    public void deleteTopic(String id){

        topicRepository.deleteById(id);
    }
}
