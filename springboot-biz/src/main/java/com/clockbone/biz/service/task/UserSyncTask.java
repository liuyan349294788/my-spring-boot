package com.clockbone.biz.service.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * Created by clock on 2018/4/16.
 */
@Component
@Slf4j
public class UserSyncTask {

    //taskAsyncPool即配置线程池的方法名，此处如果不写自定义线程池的方法名，会使用默认的线程池
    @Async("taskAsyncPool")
    public Future<String> userInfoSync(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("do some thing...");
        return new AsyncResult<String>("success");
    }
}
