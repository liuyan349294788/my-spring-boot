package com.clockbone.web.controller;

import com.clockbone.biz.service.ActivitiService;
import com.clockbone.biz.service.ApplyService;
import com.clockbone.model.Apply;
import com.clockbone.web.response.Response;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    /**
     * 请假
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
}
