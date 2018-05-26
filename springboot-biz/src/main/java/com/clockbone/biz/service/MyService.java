package com.clockbone.biz.service;

import org.activiti.engine.task.Task;

import java.util.List;

public interface MyService {
    public void startProcess();

    public List<Task> getTasks(String assignee);

}
