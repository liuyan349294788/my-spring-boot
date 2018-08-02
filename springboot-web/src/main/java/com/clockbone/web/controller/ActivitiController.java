package com.clockbone.web.controller;

import com.clockbone.biz.service.ActivitiService;
import com.clockbone.biz.service.ApplyService;
import com.clockbone.model.Apply;
import com.clockbone.model.TblBusinessApply;
import com.clockbone.model.TblBusinessApplyRes;
import com.clockbone.web.response.Response;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("activiti/")
public class ActivitiController {

    @Resource
    private ApplyService applyService;

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
        applyService.apply(apply);
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
}
