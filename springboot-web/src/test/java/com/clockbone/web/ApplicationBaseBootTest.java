package com.clockbone.web;

import com.clockbone.biz.service.TestService;
import com.clockbone.biz.service.UserService;
import com.clockbone.model.User;
import com.clockbone.web.bootstrap.ApplicationBoot;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by clock on 2018/4/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationBoot.class, //
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public  class ApplicationBaseBootTest{

        @Resource
        private UserService userService;

        @Test
        public void userServiceTest(){
            long id = 10;
            User user = userService.getUserInfo(id);
            System.out.println(user);

        }

    @Test
    public void userSyncInfoTest(){
        long id = 10;
        Boolean user = userService.syncUserInfo();
        System.out.println(user);

    }



    /*@Resource
    private TestService testService;

    @Test
    public void userServiceTest(){
        long id = 10;
        User user = testService.getUserInfo(id);
        System.out.println(user);

    }*/

}
