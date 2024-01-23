package com.ayoub.myproject.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
private CourseRepository courseRepository;
    @Autowired
    @Qualifier("CreateCustomCourseAhmed")
    Course CreateCustomCourseAhmed;

    public List<Course> getAllCourses(String topicId){
        List<Course> courses = new ArrayList<>();
        courseRepository.findByTopicId(topicId)
        .forEach(courses::add);
        return courses;
    }
    public Optional<Course> GetCourse(String id){
         return courseRepository.findById(id);
    }

    public void AddCourse(Course course){
        course.setName(CreateCustomCourseAhmed.getName());
        course.setId(CreateCustomCourseAhmed.getId());
        course.setDescription(CreateCustomCourseAhmed.getDescription());

        courseRepository.save(course);
    }
    public void updateCourse(Course course){

        courseRepository.save(course);
    }
    public void deleteCourse(String id){

        courseRepository.deleteById(id);
    }
}
