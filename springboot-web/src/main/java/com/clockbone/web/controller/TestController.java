package com.clockbone.web.controller;

import com.clockbone.service.response.MyThingResDto;
import com.clockbone.service.response.MyThingXmlResDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by clock on 2018/4/12.
 */
@RestController
@RequestMapping(value = "/springboot/")
public class TestController {

    /**
      默认返回Json     * @return
     */
    @RequestMapping("/testjson")
    @ResponseBody
    public MyThingResDto home() {
        MyThingResDto myTingResDto = new MyThingResDto();
        myTingResDto.setClose("close");
        myTingResDto.setOpen("open");
        return myTingResDto;
    }

    /**
     * 返回xml类型格式
     * @return
     */
    @RequestMapping("/testxml")
    @ResponseBody
    public MyThingXmlResDto testXml() {
        MyThingXmlResDto myTingResDto = new MyThingXmlResDto();
        myTingResDto.setClose("close");
        myTingResDto.setOpen("open");
        return myTingResDto;
    }
}
