package com.clockbone.biz.service.impl;

import com.clockbone.biz.service.ActivitiService;
import com.clockbone.dao.ActivitiMapper;
import com.clockbone.model.Apply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivitiServiceImpl implements ActivitiService {
    @Autowired
    private ActivitiMapper activitiMapper;
    @Override
    public Apply apply(Apply apply) {
        activitiMapper.insert(apply);
        return apply;
    }

    @Override
    public List<Apply> select() {
        return activitiMapper.select();
    }
}
