package com.clockbone.web;

import com.clockbone.web.bootstrap.ApplicationBoot;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by clock on 2018/4/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationBoot.class, //
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class ApplicationBootTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void AccountControllerTest(){
        log.info("test===================");
        String url = "/throwcustomexcepiont?";
        String params = "p1=p1&p2=p2";
        Object result =
                testRestTemplate.postForObject(url,params,
                        Object.class,params );
        log.info("result={}",result);

    }


}
