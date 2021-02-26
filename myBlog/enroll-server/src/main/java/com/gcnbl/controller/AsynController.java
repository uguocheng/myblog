package com.gcnbl.controller;

import com.gcnbl.results.Result;
import com.gcnbl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class AsynController {

    @Autowired
    private UserService userService;

    private Result<HashMap> result=Result.OK();

    @RequestMapping("/blog/registerName")
    public Result<HashMap> registerName(@RequestParam("username") String username) {
        System.out.println(username);

        int i = userService.validateName(username);

        System.out.println("i="+i);

        HashMap<String,Object> map = new HashMap<>();
        if (i==0){
            map.put("userExist", false);
            map.put("msg", "该用户名可用");
        }else {
            map.put("userExist", true);
            map.put("msg", "用户名已存在");
        }

        result.setModel(map);

        return result;  //JSONObject.toJSON(map) 也可直接将map转换为json并传递给前端
    }
}
