package com.gcnbl.service;

//import com.gcnbl.config.BlogProp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

public class BlogSessionService {
    //@Autowired
    //private BlogProp blogProp;

    //@Autowired
    //private RedisTemplate<String, String> redisTemplate;

    @Resource
    private HttpServletRequest request;

    public Object get(String key) {
        //if (blogProp.isRedisSession()) {
            //return redisTemplate.opsForValue().get(key);
        //} else {
            return request.getSession().getAttribute(key);
        //}
    }
}
