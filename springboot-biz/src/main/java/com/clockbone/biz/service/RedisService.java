package com.clockbone.biz.service;

/**
 * Created by clock on 2018/4/16.
 */
public interface RedisService {

    int set(String key,Long expireTime,Boolean isPresent);

    String get(String key);

}
