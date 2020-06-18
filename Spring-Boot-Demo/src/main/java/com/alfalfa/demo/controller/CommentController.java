package com.alfalfa.demo.controller;

import com.alfalfa.demo.domain.Commentlist;
import com.alfalfa.demo.domain.RespBean;
import com.alfalfa.demo.domain.User;
import com.alfalfa.demo.service.CommentService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author 15423
 */
@RestController
@SessionAttributes("user")
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    @PostMapping("/addComment")
    public RespBean addComment(Model model, @RequestBody Commentlist commentlist){
        User user = (User) model.getAttribute("user");
        commentlist.setUser_id(user.getId());
        commentlist.setUser_name(user.getNickname());
        commentService.addComment(commentlist);
        return RespBean.ok("200","success",commentlist);
    }

    @PostMapping("/delComment")
    public RespBean delComment(Model model, @RequestParam("id") Long id){
        commentService.delComment(id);
        return RespBean.ok("200","success",null);
    }

    @GetMapping("/findAllComByCid")
    public RespBean findAllComByCid(@RequestParam("cid") Long cid,
                                    @RequestParam(value = "cp",required = false,defaultValue = "1") Integer cp,
                                    @RequestParam(value = "ps",required = false,defaultValue = "10") Integer ps){
        PageHelper.startPage(cp, ps);
        commentService.findAllComByCid(cid);
        return RespBean.ok("200","success",commentService.findAllComByCid(cid));
    }

    @GetMapping("/findParentCom")
    public RespBean findParentCom(@RequestParam("pid") Long pid,
                                  @RequestParam(value = "cp",required = false,defaultValue = "1") Integer cp,
                                  @RequestParam(value = "ps",required = false,defaultValue = "10") Integer ps){
        PageHelper.startPage(cp, ps);
        return RespBean.ok("200","success",commentService.findParentCom(pid));
    }

    @GetMapping("/findComByid")
    public RespBean findComByid(@RequestParam("pid") Long pid){
        return RespBean.ok("200","success",commentService.findComByid(pid));
    }

    @PostMapping("/updateCommentByID")
    public RespBean updateComment(@RequestBody Commentlist cl){
        commentService.updateComment(cl);
        return RespBean.ok("200","success",null);
    }
}
