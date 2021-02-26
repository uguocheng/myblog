package com.gcnbl.controller;

import com.gcnbl.service.ArticleService;
import com.gcnbl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/blog")
public class UserController {
    private final UserService userService;

    private final ArticleService articleService;

    @Autowired
    public UserController(UserService userService, ArticleService articleService) {
        this.userService = userService;
        this.articleService = articleService;
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public String register(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           @RequestParam("telphone") int telphone) {
        System.out.println(password);

        userService.addBlogUser(username, password, telphone);

        System.out.println(666);
        //注册成功直接跳转登录
        return "login";

    }


    @PostMapping("/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model, HttpServletRequest httpServletRequest) {

        System.out.println(password);
        Long userId = userService.login(username, password);

        if (userId==null){
            //如果没有记录，重新输入
            model.addAttribute("worryPwd", true);
            return "login";

        }
        else {
            httpServletRequest.getSession().setAttribute("userId", userId);
            return "userCenter";
        }

    }
}
