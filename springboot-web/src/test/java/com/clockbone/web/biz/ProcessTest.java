package com.clockbone.web.biz;

import com.clockbone.biz.service.ApplyService;
import com.clockbone.biz.service.common.BusStatus;
import com.clockbone.biz.service.common.BusinessKey;
import com.clockbone.dao.TblBusinessApplyMapper;
import com.clockbone.dao.TblBusinessMapper;
import com.clockbone.model.Apply;
import com.clockbone.model.TblBusiness;
import com.clockbone.model.TblBusinessApply;
import com.clockbone.web.AbstratApplicationBaseBootTest;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.*;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Slf4j
public class ProcessTest extends AbstratApplicationBaseBootTest {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private TblBusinessMapper tblBusinessMapper;

    @Autowired
    private TblBusinessApplyMapper tblBusinessApplyMapper;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private ApplyService applyService;


    @Test
    public void tblTest(){
        TblBusiness tblBusiness = new TblBusiness();
        tblBusiness.setUserId(3120L);
        tblBusinessMapper.insert(tblBusiness);

        TblBusinessApply tblBusinessApply = new TblBusinessApply();
        tblBusinessApply.setProcessId("test");
        tblBusinessApply.setProcessName("name");
        tblBusinessApply.setUserId(3120L);
        tblBusinessApply.setBusStatus(BusStatus.WAIT.getKey());
        tblBusinessApply.setBusinessId(tblBusiness.getId());
        tblBusinessApplyMapper.insert(tblBusinessApply);
    }


    @Test
    public void startProcess(){
        applyService.apply(new Apply());

    }
}
