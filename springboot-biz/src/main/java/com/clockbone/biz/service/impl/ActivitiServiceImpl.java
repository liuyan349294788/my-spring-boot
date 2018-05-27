package com.clockbone.biz.service.impl;

import com.clockbone.biz.service.ActivitiService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivitiServiceImpl implements ActivitiService {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private RepositoryService repositoryService;

    public void getRepository(){

    }

}
