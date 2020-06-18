package com.alfalfa.demo.controller;

import com.alfalfa.demo.domain.Course;
import com.alfalfa.demo.domain.RespBean;
import com.alfalfa.demo.service.CourseService;
import com.alfalfa.demo.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 15423
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserService userService;
    @Autowired
    CourseService courseService;

    @DeleteMapping("/deleteUser")
    public RespBean deleteUser(@RequestParam("id") Long id){
        userService.deleteUser(id);
        return RespBean.ok("200","success",null);
    }

    @GetMapping("/getAllUser")
    public RespBean getAllUser(@RequestParam(value = "cp",required = false,defaultValue = "1") Integer cp,
                               @RequestParam(value = "ps",required = false,defaultValue = "10") Integer ps){
        PageHelper.startPage(cp, ps);
        return RespBean.ok("200","success",userService.findAllUser());
    }

    @PostMapping("/updateCourse")
    public RespBean updateCourse(@RequestBody Course course){
        courseService.updateCourseDetail(course);
        return RespBean.ok("200","success",null);
    }

    @PostMapping("/delCourse")
    public RespBean delCourse(@RequestParam("cid") Long cid){
        courseService.delCourse(cid);
        return RespBean.ok("200","success",null);
    }

    @PostMapping("/addCourse")
    public RespBean addCourse(@RequestBody Course course){
        courseService.addCourse(course);
        return RespBean.ok("200","success",null);
    }

}
