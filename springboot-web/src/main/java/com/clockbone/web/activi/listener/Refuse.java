package com.clockbone.web.activi.listener;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Refuse implements JavaDelegate{

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

    }
}
