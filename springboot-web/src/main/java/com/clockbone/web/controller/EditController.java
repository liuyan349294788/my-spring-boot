package com.clockbone.web.controller;

import com.clockbone.biz.service.ApplyService;
import com.clockbone.model.Apply;
import com.clockbone.service.response.MyThingResDto;
import com.clockbone.service.response.MyThingXmlResDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by clock on 2018/4/12.
 */
@Controller
@RequestMapping(value = "/springboot/")
@Slf4j
public class EditController {

    @Resource
    private ApplyService applyService;

    @RequestMapping("editindex")
   public String index(Model model){
        Apply apply = new Apply();
        List<Apply> list = applyService.select(apply);
        model.addAttribute("list",list);
       return "edit/index";
   }
    @RequestMapping("editvalue")
    @ResponseBody
   public Map editTest(HttpServletRequest request, @Param("pk")String pk,
                       @Param("name")String name,@Param("value")String value){
        System.out.println("pk="+pk+" name="+name+" value="+value);
        Map<String,String> map = new HashMap<>();
        map.put("newValue","qinjun");
        map.put("id",pk);
        map.put("name",name);
        return map;
   }
}
