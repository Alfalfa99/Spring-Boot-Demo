package com.alfalfa.demo.controller;

import com.alfalfa.demo.domain.Course;
import com.alfalfa.demo.domain.RespBean;
import com.alfalfa.demo.domain.User;
import com.alfalfa.demo.service.UserService;
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
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/getUserByUsername")
        public RespBean getUserByName(@RequestParam("username") String username){

        return RespBean.ok("200","success",userService.getUserByUsername(username));
    }

    @GetMapping("/getAllCourses")
    public RespBean getAllCourses(Model model, @RequestParam("currentpage") Integer cp,
                                  @RequestParam("pagesize") Integer ps){
        User user = (User) model.getAttribute("user");
        Page<Object> page = PageHelper.startPage(cp, ps);
        List<Course> course = userService.findUserCourse(user.getId());
        //分页后能够获得总的查询结果数
        //System.out.println(page.getTotal());
        return RespBean.ok("200","success",course);
    }

    @PostMapping("/updateUserDetail")
    public RespBean updateUserDetail(Model model,@RequestBody User user){
        User last_user = (User) model.getAttribute("user");
        last_user.setId(last_user.getId());
        last_user.setNickname(user.getNickname());
        last_user.setGender(user.getGender());
        last_user.setTel(user.getTel());
        userService.updateUserDetail(last_user);
        model.addAttribute("user",last_user);
        return RespBean.ok("200","success",last_user);
    }

    @PostMapping("/chooseCourse")
    public RespBean chooseCourse(Model model, @RequestParam("cid") Long cid){
        User user = (User) model.getAttribute("user");
        userService.chooseCourse(cid,user);
        user = userService.getUserById(user.getId());
        model.addAttribute("user",user);
        return RespBean.ok("200","success",user);
    }

    @PostMapping("/updatePassword")
    public RespBean updatePassword(Model model, @RequestParam("password") String pw){
        User user = (User) model.getAttribute("user");
        user.setPassword(pw);
        userService.updatePassword(user);
        user = userService.getUserById(user.getId());
        model.addAttribute("user",user);
        return RespBean.ok("200","success",user);
    }
}
