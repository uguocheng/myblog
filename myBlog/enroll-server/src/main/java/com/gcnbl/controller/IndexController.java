package com.gcnbl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/index")
public class IndexController {
    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("hello", "gcblog");
        return "index";
    }

    @PostMapping("/register")
    public String register(@RequestParam("username") String username,
                           @RequestParam("password") String password) {
        return null;
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
