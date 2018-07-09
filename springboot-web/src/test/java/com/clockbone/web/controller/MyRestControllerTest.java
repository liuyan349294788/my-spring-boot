package com.clockbone.web.controller;

import com.clockbone.web.AbstratApplicationBaseBootTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;


@Slf4j
public class MyRestControllerTest extends AbstratApplicationBaseBootTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    //@Test
    public void MyRestControllerTest(){
        log.info("test===================");
        String url = "/process";
        String params = null;
        Object result =
                testRestTemplate.postForObject(url,params,
                        Object.class,params );
        log.info("result={}",result);

    }

    //@Test
    public void taskTest(){
        log.info("test===================");
        String url = "/tasks?assignee=kermit";
        String params = "assignee=kermit";
        Object result =
                testRestTemplate.postForObject(url,params,
                        Object.class,params );
        log.info("result={}",result);

    }
}
