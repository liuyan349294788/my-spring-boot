package com.clockbone.web.controller;

import com.clockbone.biz.service.ActivitiService;
import com.clockbone.biz.service.ApplyService;
import com.clockbone.biz.service.common.BusinessKey;
import com.clockbone.model.Apply;
import com.clockbone.model.DoProcess;
import com.clockbone.model.TblBusinessApply;
import com.clockbone.model.TblBusinessApplyRes;
import com.clockbone.web.response.Response;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("activiti/")
public class ActivitiController {

    @Resource
    private ApplyService applyService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private RepositoryService repositoryService;


    /**
     * 请假首面
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("index")
    public String index(HttpServletRequest request, Model model){
        Apply apply = new Apply();
        List<Apply> list = applyService.select(apply);
        model.addAttribute("list",list);
        return "activiti/index";
    }

    @RequestMapping("checkindex")
    public String checkIndex(HttpServletRequest request, Model model){
        Apply apply = new Apply();
        apply.setBusStatus("WAIT");
        List<Apply> list = applyService.select(apply);
        model.addAttribute("list",list);
        return "activiti/check";
    }

    @RequestMapping("check")
    @ResponseBody
    public Response check(HttpServletRequest request, Model model,Long id,String busStatus){
        applyService.update(id,busStatus);
        return new Response();
    }

    //-----------------------------------流程相关方法开始------------------------------------------------------------------
    /**
     * 请假，发起申请流程
     * @param request
     * @param model
     * @param apply
     * @return
     */
    @PostMapping("apply")
    @ResponseBody
    public Response apply(HttpServletRequest request,Model model,Apply apply){
        if(Objects.equals(apply.getName(),"0")){
            apply.setBusinessKey(BusinessKey.LEAVE.getKey());
        }else{
            apply.setBusinessKey(BusinessKey.APPLY.getKey());
        }
        try{
            applyService.apply(apply);
        }catch (Exception e){
            return new Response("500","失败");
        }
        return new Response(apply);

    }

    //查看流程列表，我发起的申请，我待办的申请
    @RequestMapping("myProcess")
    public String myProcess(HttpServletRequest request,@RequestParam(defaultValue = "0") String type,Model model){
        //我发起的
        if(Objects.equals(type,"0")){
            TblBusinessApply t = new TblBusinessApply();
            t.setCreateUserId(0L);
            List<TblBusinessApplyRes> list = applyService.selectApplyList(t);
            model.addAttribute("list",list);
        }else{
            //我待办的

        }
        return "activiti/processlist";
    }

    @PostMapping("doprocess")
    @ResponseBody
    public Response doprocess(DoProcess doProcess){
        applyService.doProcess(doProcess.getTaskId(),doProcess.getMessage());
        return new Response();

    }

    /**
     */
    @RequestMapping(value = "/resource")
    public void loadByProcessInstance(@RequestParam("type") String resourceType,
                                      @RequestParam("processInstanceId") String processInstanceId,
                                      HttpServletResponse response)
            throws Exception {
        InputStream resourceAsStream = null;
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processInstance.getProcessDefinitionId())
                .singleResult();

        String resourceName = "";
        if (resourceType.equals("image")) {
            resourceName = processDefinition.getDiagramResourceName();
        } else if (resourceType.equals("xml")) {
            resourceName = processDefinition.getResourceName();
        }
        resourceAsStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), resourceName);
        byte[] b = new byte[1024];
        int len = -1;
        while ((len = resourceAsStream.read(b, 0, 1024)) != -1) {
            response.getOutputStream().write(b, 0, len);
        }
    }

}
