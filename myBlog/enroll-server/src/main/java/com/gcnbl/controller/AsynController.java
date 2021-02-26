package com.gcnbl.controller;

import com.alibaba.fastjson.JSONObject;
import com.gcnbl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController

public class AsynController {

    @Autowired
    private UserService userService;

    @RequestMapping("/blog/registerName")
    public Object registerName(@RequestParam("username") String username) {
        System.out.println(username);

        int i = userService.validateName(username);

        System.out.println("i="+i);

        Map<String,Object> map = new HashMap<>();
        if (i==0){
            map.put("userExist", false);
            map.put("msg", "该用户名可用");
        }else {
            map.put("userExist", true);
            map.put("msg", "用户名已存在");
        }

        //将msp转换为json并传递给前端
        return JSONObject.toJSON(map);
    }
}
