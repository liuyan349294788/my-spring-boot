package com.clockbone.web.controller;

import com.clockbone.web.exception.CustomException;
import com.clockbone.web.exception.CustomViewException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by clock on 2018/4/12.
 */
@Controller
public class AccountController {



    @RequestMapping("/upload")
    public String uploadhtml(){
        return "upload/upload";
    }



    @RequestMapping("/list")
    public String listAsHtml(Model model) {
        // Duplicated logic
        model.addAttribute("test");
        return "list";         // View determined by service-resolution
    }

    /**
     * return the error test
     * @param model
     * @return
     */
    @RequestMapping("/listviewerror")
    public String listviewerror(Model model) {
        // Duplicated logic
        model.addAttribute("test");
        if(true){
            throw new CustomViewException("","");
        }
        return "list";
    }


    @RequestMapping("/index")
    public String indexHtml(Model model) {
        // Duplicated logic
        model.addAttribute("test");
        return "index";         // View determined by service-resolution
    }


    @RequestMapping("/jsonResponse")
    @ResponseBody
    public String response(Model model) {
        // Duplicated logic
        model.addAttribute("test");
        return "indexjson";         // View determined by service-resolution
    }

    @RequestMapping("/errorResTest")
    @ResponseBody
    public String errorResTest(){

        int i = 0;
        int j = -1;
        int x = j/i;
        return "erro";

    }

    @RequestMapping("/throwcustomexcepiont")
    @ResponseBody
    public String throwCustomExcepiontTest(){
        try{
            int i = 0;
            int j = -1;
            int x = j/i;
        }catch (Exception e){
            throw new CustomException("custom code",e.getMessage());
        }
        return "erro";

    }


}
