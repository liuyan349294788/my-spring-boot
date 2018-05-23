package com.clockbone.biz.service.impl;
import com.clockbone.biz.service.TestService;
import com.clockbone.model.User;
import org.springframework.stereotype.Service;

/**
 * Created by clock on 2018/4/16.
 */
@Service
public class TestServiceImpl implements TestService {

    public User getUserInfo(Long id){
        User u = new User();
        u.setId(10L);
        u.setUserId("afdasd");
        u.setUserName("est");
        return u;

    }

}
