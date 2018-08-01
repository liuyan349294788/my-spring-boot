package com.clockbone.biz.service.impl;

import com.clockbone.biz.service.ApplyService;
import com.clockbone.biz.service.common.BusStatus;
import com.clockbone.biz.service.common.BusinessKey;
import com.clockbone.dao.ActivitiMapper;
import com.clockbone.dao.TblBusinessApplyMapper;
import com.clockbone.dao.TblBusinessMapper;
import com.clockbone.model.Apply;
import com.clockbone.model.TblBusiness;
import com.clockbone.model.TblBusinessApply;
import com.clockbone.model.TblBusinessApplyRes;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.assertj.core.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ApplyServiceImpl implements ApplyService {
    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private ActivitiMapper activitiMapper;

    @Autowired
    private TblBusinessMapper tblBusinessMapper;

    @Autowired
    private TblBusinessApplyMapper tblBusinessApplyMapper;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private TaskService taskService;
    @Override
    public Apply apply(Apply apply) {
        Long userId = 0L;
        //先创建一条业务数据
        TblBusiness tblBusiness = new TblBusiness();
        tblBusinessMapper.insert(tblBusiness);

        //启动流程
        String key = BusinessKey.LEAVE.getKey();
        Map<String,Object> variables = new HashMap<>();
        variables.put("applyUserId",userId);
        String objId = key + "." + tblBusiness.getId();
        variables.put("objId",objId);
        // 设置流程启动人
        identityService.setAuthenticatedUserId(String.valueOf(userId));
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(key,objId,variables);
        String processId = processInstance.getProcessInstanceId();
        String processDefineId = processInstance.getProcessDefinitionId();
        //创建审批流
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefineId).singleResult();

        //插入流程审请表
        TblBusinessApply tblBusinessApply = new TblBusinessApply();
        //记录审批id
        tblBusinessApply.setProcessId(processId);
        tblBusinessApply.setProcessName(processDefinition.getName());
        tblBusinessApply.setCreateUserId(userId);
        tblBusinessApply.setBusStatus(BusStatus.WAIT.getKey());
        //记录是哪条业务发起的审批
        tblBusinessApply.setBusinessId(tblBusiness.getId());
        tblBusinessApplyMapper.insert(tblBusinessApply);
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

    @Override
    public List<TblBusinessApply> selectApplyList(TblBusinessApply tblBusinessApply) {
        List<TblBusinessApply> list =  tblBusinessApplyMapper.selectApplyList(tblBusinessApply);

        list.forEach(each->{
            TblBusinessApplyRes tblBusinessApplyRes = new TblBusinessApplyRes();
            //查询任务
            Task task = taskService.createTaskQuery().processInstanceId(each.getProcessId()).singleResult();
            if(null != task){
                tblBusinessApplyRes.setTaskId(task.getId());
                //当前处理节点（上级）
                tblBusinessApplyRes.setCurrentNode(task.getName());
                tblBusinessApplyRes.setProcessDefineId(task.getProcessDefinitionId());
                //任务派遣人id
                if(Strings.isNullOrEmpty(task.getAssignee())){
                    tblBusinessApplyRes.setCurrentHandle(Long.parseLong(task.getAssignee())+"名字");
                }

            }
        });
        return list;
    }
}
