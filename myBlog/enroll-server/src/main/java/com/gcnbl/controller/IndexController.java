package com.gcnbl.controller;

import com.gcnbl.service.ArticleService;
import com.gcnbl.service.UserService;
import com.gcnbl.service.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/index")
public class IndexController {
    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("hello", "gcblog");
        return "register";
    }

    @RequestMapping("/register")
    public String register(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           @RequestParam("telphone") int telphone) {
        int count = userService.addBlogUser(username,password,telphone);
        if (count>0){
            System.out.println(666);
        }
        System.out.println(777);
        return "addarticle";
    }

    @RequestMapping("/addArticle")
    public String addArticle(@RequestParam("content") String content) {
        int count = articleService.addArticle(content);
        if (count>0){
            System.out.println(6666);
        }
        System.out.println(7777);
        return "addarticle";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password) {
        //如果没有记录，则进行注册
        if (false){
            return "redirct:/index/register";
        }
        return null;
    }


}
