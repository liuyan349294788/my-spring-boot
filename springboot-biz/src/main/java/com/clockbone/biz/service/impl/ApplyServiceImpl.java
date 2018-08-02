package com.clockbone.biz.service.impl;

import com.clockbone.biz.service.ApplyService;
import com.clockbone.biz.service.common.BusStatus;
import com.clockbone.biz.service.common.BusinessKey;
import com.clockbone.dao.ActivitiMapper;
import com.clockbone.dao.TblBusinessApplyMapper;
import com.clockbone.dao.TblBusinessMapper;
import com.clockbone.model.*;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
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
    private IdentityService identityService;

    @Autowired
    private TaskService taskService;
    @Override
    @Transactional
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
        //创建审批流
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(key,objId,variables);
        log.info("创建审批流,procesInstance:{}",processInstance);
        String processId = processInstance.getProcessInstanceId();
        String processDefineId = processInstance.getProcessDefinitionId();
        log.info("创建审批流,processId:{},processDefineId:{}",processInstance,processDefineId);
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
    public List<TblBusinessApplyRes> selectApplyList(TblBusinessApply tblBusinessApply) {
        List<TblBusinessApply> list =  tblBusinessApplyMapper.selectApplyList(tblBusinessApply);
        List<TblBusinessApplyRes> resList = new ArrayList<>();
        if(CollectionUtils.isEmpty(list)){
            return resList;
        }
        list.forEach(each->{
            TblBusinessApplyRes tblBusinessApplyRes = new TblBusinessApplyRes();
            tblBusinessApplyRes.setBusStatus(each.getBusStatus());
            tblBusinessApplyRes.setProcessName(each.getProcessName());
            tblBusinessApplyRes.setProcessId(each.getProcessId());
            //查询任务
            Task task = taskService.createTaskQuery().processInstanceId(each.getProcessId()).singleResult();
            if(null != task){
                tblBusinessApplyRes.setDefineProcessId(task.getProcessDefinitionId());
                tblBusinessApplyRes.setTaskId(task.getId());
                //当前处理节点（上级）
                tblBusinessApplyRes.setCurrentNode(task.getName());
                //任务派遣人id
                if(!Strings.isNullOrEmpty(task.getAssignee())){
                    tblBusinessApplyRes.setCurrentHandle(Long.parseLong(task.getAssignee())+"任务派遣人名字");
                }else{
                    //多个派遣人
                    List<TblTaskAssigne> userList = tblBusinessApplyMapper.selectTaskAssigne(task.getId());
                    List<String> assigneList = new ArrayList<>();
                    if(!CollectionUtils.isEmpty(userList)){
                        userList.forEach(eachUser->{
                            if(!Strings.isNullOrEmpty(eachUser.getGroupId())){
                                assigneList.add(eachUser.getGroupId());
                            }
                            if(!Strings.isNullOrEmpty(eachUser.getUserId())){
                                assigneList.add(eachUser.getUserId()+"");
                            }
                        });
                        String users = Joiner.on(",").join(assigneList);
                        tblBusinessApplyRes.setCurrentHandle(users);
                    }
                }
            }
            tblBusinessApplyRes.setApplyUser(each.getCreateUserId()+"申请人名字");
            resList.add(tblBusinessApplyRes);
        });
        return resList;
    }
}
