package com.clockbone.biz.service.impl;

import com.clockbone.biz.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

/**
 * Created by clock on 2018/4/16.
 */
@Service
public class RedisServiceImpl implements RedisService{

    //@Autowired
    //private StringRedisTemplate template;
    @Override
    public int set(String key, Long expireTime, Boolean isPresent) {
        return 0;
    }

    @Override
    public String get(String key) {
      /*  ValueOperations<String, String> ops = this.template.opsForValue();
        String key1 = "spring.boot.redis.test";
        if (!this.template.hasKey(key1)) {
            return null;
        }
        return   ops.get(key1);*/
        return null;
    }
}
