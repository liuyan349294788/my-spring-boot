package com.clockbone.biz.service.task.poll;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by clock on 2018/4/16.
 */
@Configuration
@EnableAsync
public class TaskExecutePool {

    @Bean
    public Executor taskAsyncPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //init thread num
        executor.setCorePoolSize(5);
        //max thread mun
        executor.setMaxPoolSize(10);
        //wait queueu size
        executor.setQueueCapacity(200);
        //wait 60S   back to thread pool
        executor.setKeepAliveSeconds(60);
        //wait for task complete then shutdonw pool
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }
}
