package com.clockbone.biz.service.impl;

import com.clockbone.biz.service.ApplyService;
import com.clockbone.dao.ActivitiMapper;
import com.clockbone.model.Apply;
import org.activiti.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplyServiceImpl implements ApplyService {

    @Autowired
    private ActivitiMapper activitiMapper;

    @Autowired
    private RuntimeService runtimeService;
    @Override
    public Apply apply(Apply apply) {
        activitiMapper.insert(apply);
        runtimeService.startProcessInstanceByKey("myProcess_1");
        //启动一个流程
        return apply;
    }

    @Override
    public List<Apply> select(Apply apply) {
        return activitiMapper.select(apply);
    }

    @Override
    public Integer update(Long id,String busStatus) {
        return activitiMapper.updateStatus(id,busStatus);
    }
}
