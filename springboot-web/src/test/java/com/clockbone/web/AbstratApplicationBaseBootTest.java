package com.clockbone.web;

import com.clockbone.biz.service.UserService;
import com.clockbone.model.User;
import com.clockbone.web.bootstrap.ApplicationBoot;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by clock on 2018/4/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationBoot.class, //
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public  abstract class AbstratApplicationBaseBootTest {

}
