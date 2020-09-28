package com.alfalfa.demo.service;

import com.alfalfa.demo.dao.CommentlistDao;
import com.alfalfa.demo.domain.Commentlist;
import com.alfalfa.demo.domain.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 15423
 */
@Service
public class CommentService {
    //自动注入
    @Autowired
    CommentlistDao commentlistDao;
    //CommentlistDao commentlistDao = new CommentlistDao();
    @Autowired
    CourseService courseService;

    @Transactional(rollbackForClassName = "GlobalExceptionHandler.java")
    public void addComment(Commentlist commentlist){
        commentlistDao.addComment(commentlist);
        Course course = courseService.findCourseById(commentlist.getCourse_id());
        course.setComment_count(course.getComment_count()+1);
        courseService.updateCourseDetail(course);
    }

    public void delComment(Long id){
        commentlistDao.delComment(id);
    }

    public List<Commentlist> findAllComByCid(Long cid){
        return commentlistDao.findAllComByCid(cid);
    }

    public List<Commentlist> findParentCom(Long clid){
        return commentlistDao.findParentCom(clid);
    }

    public List<Commentlist> findComByid(Long id){
        return commentlistDao.findComByid(id);
    }

    public void updateComment(Commentlist cl){
        commentlistDao.updateComment(cl);
    }
}
