package com.alfalfa.demo.controller;

import com.alfalfa.demo.domain.Course;
import com.alfalfa.demo.domain.RespBean;
import com.alfalfa.demo.domain.User;
import com.alfalfa.demo.service.CourseService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 15423
 */
@RestController
@SessionAttributes("user")
@RequestMapping("/course")
public class CourseController {
    @Autowired
    CourseService courseService;

    @GetMapping("/getUserCourse")
    public RespBean getUserCourse(Model model,
                                  @RequestParam(value = "cp",required = false,defaultValue = "1") Integer cp,
                                  @RequestParam(value = "ps",required = false,defaultValue = "5") Integer ps){
        User user = (User) model.getAttribute("user");
        PageHelper.startPage(cp, ps);
        return RespBean.ok("200","success",courseService.findUserCourse(user.getId()));
    }

    @GetMapping("/getAllCourse")
    public RespBean getAllCourse(@RequestParam(value = "cp",required = false,defaultValue = "1") Integer cp,
                                 @RequestParam(value = "ps",required = false,defaultValue = "10") Integer ps){
        PageHelper.startPage(cp, ps);
        return RespBean.ok("200","success",courseService.findAllCourse());
    }

    @GetMapping("/getCourseBy")
    public RespBean getCourseBy(@RequestParam("cid") Long cid, @RequestParam("cname") String cname,
                                @RequestParam(value = "cp",required = false,defaultValue = "1") Integer cp,
                                @RequestParam(value = "ps",required = false,defaultValue = "10") Integer ps){
        PageHelper.startPage(cp, ps);
        return RespBean.ok("200","success",courseService.findCourseBy(cname, cid));
    }

    @PostMapping("/updateCoursePop")
    public RespBean updateCoursePop(@RequestParam("cid") Long cid){
        courseService.updateCoursePop(cid);
        return RespBean.ok("200","success",null);
    }
}
