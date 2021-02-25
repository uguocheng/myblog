package com.gcnbl.controller;

import com.gcnbl.service.ArticleService;
import com.gcnbl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/blog")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    @GetMapping("/register")
    public String register() {
        return "register";
    }
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public String userRegister(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           @RequestParam("password2") String password2,
                           @RequestParam("telphone") int telphone,Model model) {
        System.out.println(password);
        System.out.println(password2);
        int count = userService.addBlogUser(username, password, password2, telphone);

        if (count == 1) { //注册成功
            System.out.println(666);
            return "userCenter";
        }

        //密码不一致或系统问题
        model.addAttribute("twoPassword", true);
        System.out.println(777);
        return "register";

    }

    @PostMapping("/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model) {
        //如果没有记录，则进行注册
        if (false){
            model.addAttribute("noRegister", true);
            return "register";
        }
        return null;
    }
}
