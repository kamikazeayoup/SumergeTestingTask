package com.ayoub.myproject.course;


import com.ayoub.myproject.topic.Topic;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class CourseConfiguration {

     String Id = "10";
    private String name = "Ahmed";
    private String description = "This is Ahmed";

    @Bean(name = "CreateCustomCourseAhmed")
    @ConditionalOnMissingBean(name = "CreateCustomCourseAhmedOverride")
    Course CreeateCustomCourseAhmed(){
        Course cust = new Course();
        cust.setId(Id);
        cust.setTopic(new Topic("1" , "" , ""));
        cust.setName(name);
        cust.setDescription(description);
        return cust;
    }

    @Bean(name = "CreateCustomCourseMohamed")
    Course CreeateCustomCourseMohamed(){
        Course cust = new Course();
        cust.setId("2");
        cust.setTopic(new Topic("1" , "" , ""));
        cust.setName("Mohamed");
        cust.setDescription("Mohamed Description");
        return cust;
    }

}
