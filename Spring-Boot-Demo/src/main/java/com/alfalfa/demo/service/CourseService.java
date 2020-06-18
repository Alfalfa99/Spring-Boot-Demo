package com.alfalfa.demo.service;

import com.alfalfa.demo.dao.CourseDao;
import com.alfalfa.demo.domain.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 15423
 */
@Service
public class CourseService {
    @Autowired
    CourseDao courseDao;

    public void addCourse(Course course){
        courseDao.addCourse(course);
    }

    public void delCourse(Long cid){
        courseDao.delCourse(cid);
    }

    public List<Course> findAllCourse(){
        return courseDao.findAllCourse();
    }

    public List<Course> findCourseBy(String cname, Long cid){
        return courseDao.findCourseBy(cname, cid);
    }

    public List<Course> findUserCourse(Long id){
        return courseDao.findUserCourse(id);
    }

    public void updateCourseDetail(Course c){
        courseDao.updateCourseDetail(c);
    }

    public Course findCourseById(Long cid){
        return courseDao.findCourseByid(cid);
    }

//    课程点赞功能
    public void updateCoursePop(Long cid){
        Course course = courseDao.findCourseByid(cid);
        courseDao.updateCoursePop(course.getPopcount()+1,cid);
    }
}
