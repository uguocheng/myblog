package com.gcnbl.controller;

import com.gcnbl.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ArticleController {
    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @RequestMapping("/addArticle")
    public String addArticle(@RequestParam("content") String content) {
        int count = articleService.addArticle(content);
        if (count>0){
            System.out.println(6666);
        }
        System.out.println(7777);
        return "addArticle";
    }

}
