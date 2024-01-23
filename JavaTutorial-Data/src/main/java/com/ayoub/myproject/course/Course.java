package com.ayoub.myproject.course;

import com.ayoub.myproject.topic.Topic;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Course {
    @Id
    private String Id;
    private String name;
    private String description;
    @ManyToOne
    private Topic topic;

    public Course() {

    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Course(String id, String name, String description , String topicId) {
        Id = id;
        this.name = name;
        this.description = description;
        this.topic = new Topic(topicId , "" , "");
    }
}
