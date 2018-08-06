package com.clockbone.web.activi.listener;

import com.clockbone.biz.service.common.BusinessKey;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
public class Agree implements JavaDelegate{

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String objId = (String) execution.getVariable("objId");
        String[] split = StringUtils.split(objId, "\\.");
        String key = split[0];
        Long businessId = Long.valueOf(split[1]);
        Long userId = (Long) execution.getVariable("applyUserId");
        String userName = (String) execution.getVariable("applyUserName");
        String processInstanceId = execution.getProcessInstanceId();
        log.info("objectId:{},applyUserId:{},applyUserName:{}",objId,userId,userName);
        if (Objects.equals(key, BusinessKey.LEAVE.getKey())) {
            //ApplyService ApplyService = (ApplyService) SpringUtil.getBean("ApplyService");
            //ApplyService.greee(businessId, processInstanceId, auditId, auditName);
        }
        log.info("key:{} businessId:{}  审批人:[{}:{}] 审批拒绝.....",
                key, businessId, userId, userName);

    }
}
