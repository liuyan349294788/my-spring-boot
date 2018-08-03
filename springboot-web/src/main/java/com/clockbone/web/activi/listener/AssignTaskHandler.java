package com.clockbone.web.activi.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class AssignTaskHandler  implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        String objId = (String) delegateTask.getExecution().getVariable("objId");
        String[] split = StringUtils.split(objId, "\\.");
        String key = split[0];
        Long businessId = Long.valueOf(split[1]);
        if (Objects.equals(key,"1")) {
            Long id = (Long) delegateTask.getExecution().getVariable("id");
            String processInstanceId = delegateTask.getProcessInstanceId();
        }
    }
}
