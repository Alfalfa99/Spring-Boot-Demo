package com.alfalfa.demo.controller;

import com.alfalfa.demo.domain.RespBean;
import com.alfalfa.demo.domain.User;
import com.alfalfa.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 15423
 */
@RestController
@RequestMapping("/Reg")
public class RegisterController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public RespBean register(@RequestParam("username") String username,@RequestParam("password") String password){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userService.userRegister(user);
        user = userService.getUserByUsername(user.getUsername());
        return RespBean.ok("200","success",user);
    }
    @PostMapping("/test")
    public RespBean test(@RequestBody ConcurrentHashMap<String,Object> map) {
        return RespBean.ok("200", "success", map);
    }
}
