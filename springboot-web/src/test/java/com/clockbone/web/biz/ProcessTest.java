package com.clockbone.web.biz;

import com.clockbone.biz.service.common.BusinessKey;
import com.clockbone.dao.TblBusinessApplyMapper;
import com.clockbone.dao.TblBusinessMapper;
import com.clockbone.model.TblBusiness;
import com.clockbone.model.TblBusinessApply;
import com.clockbone.web.AbstratApplicationBaseBootTest;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
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


    @Test
    public void tblTest(){
        TblBusiness tblBusiness = new TblBusiness();
        tblBusinessMapper.insert(tblBusiness);

        TblBusinessApply tblBusinessApply = new TblBusinessApply();
        tblBusinessApply.setProcessId("test");
        tblBusinessApply.setProcessName("name");
        tblBusinessApplyMapper.insert(tblBusinessApply);
    }


    @Test
    public void startProcess(){
        TblBusiness tblBusiness = new TblBusiness();
        tblBusinessMapper.insert(tblBusiness);

        String key = BusinessKey.LEAVE.getKey();
        String businessKey = tblBusiness.getId()+"";
        Map<String,Object> variables = new HashMap<>();
        variables.put("applyUserId",3120);
        variables.put("objId",businessKey + "_3120");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(key,businessKey,variables);
        String processId = processInstance.getProcessDefinitionId();
        String processDefineId = processInstance.getProcessDefinitionId();
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefineId).singleResult();

        //插入流程审请表
        TblBusinessApply tblBusinessApply = new TblBusinessApply();
        tblBusinessApply.setProcessId(processId);
        tblBusinessApply.setProcessName(processDefinition.getName());
        tblBusinessApplyMapper.insert(tblBusinessApply);




    }
}
