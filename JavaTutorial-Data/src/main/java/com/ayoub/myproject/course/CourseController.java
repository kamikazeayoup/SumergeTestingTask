package com.ayoub.myproject.course;
import com.ayoub.myproject.topic.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CourseController {
    @Autowired
    private CourseService courseService;
    @RequestMapping("/topics/{topicId}/courses")
    public List<Course> getAllCourses(@PathVariable String topicId){

        return this.courseService.getAllCourses(topicId);
    }

    @RequestMapping("/topics/{topicId}/courses/{id}")
    public Optional<Course> getCoursebyId(@PathVariable("id") String id){

        return this.courseService.GetCourse(id);
    }
    @RequestMapping(method = RequestMethod.POST , value = "/topics/{topicId}/courses" )
    public void AddTopic(@RequestBody Course course , @PathVariable String topicId){
        course.setTopic(new Topic(topicId , "" , ""));
        this.courseService.AddCourse(course);
    }
    @RequestMapping(method = RequestMethod.PUT , value = "/topics/{topicId}/courses/{id}" )
    public void updateTopic(@RequestBody Course course, @PathVariable("id") String id , @PathVariable("topicId") String topicId ){
        course.setTopic(new Topic(topicId , "" , ""));
        this.courseService.updateCourse(course);
    }

    @RequestMapping(method = RequestMethod.DELETE , value = "/topics/{topicId}/courses/{id}" )
    public void deleteTopic(@PathVariable String id){
        this.courseService.deleteCourse(id);
    }


}