package com.clockbone.web.configuration;

import com.clockbone.web.controller.AccountController;
import com.clockbone.web.exception.CustomErrorType;
import com.clockbone.web.exception.CustomException;
import com.clockbone.web.exception.CustomViewException;
import com.clockbone.web.exception.HtmlException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by clock on 2018/4/12.
 * 用ControllerAdvice 注解来处理spring boot http clent访问异常
 * 拦截被抛出的异常
 *
 */
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    /* *
     * unified exception intercetion,json and service will interceted  and return the json error code
     * @param request
     * @param ex
     * @return*/

    @ExceptionHandler(Exception.class)
    @ResponseBody
    Map<String,String> handleControllerException(HttpServletRequest request, Throwable ex) {
        HttpStatus status = getStatus(request);
        Map<String,String> map = new HashMap();
        map.put("code", String.valueOf(status.value()));
        map.put("msg", ex.getMessage());
        return map;
        //return new ResponseEntity<>("customerException", status);
    }
    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
    /**
     * custome  CustomException  and return json
     * @param request
     * @param ex
     * @return*/

    @ExceptionHandler(CustomException.class)
    @ResponseBody
    Map<String,String> handleControllerCustomException(HttpServletRequest request, CustomException ex) {
        HttpStatus status = getStatus(request);
        Map<String,String> map = new HashMap();
        map.put("code", String.valueOf(status.value()));
        map.put("msg", "customer error msg");
        return map;
    }

    /**
     * CustomViewException exception and return service
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(CustomViewException.class)
    ModelAndView handleControllerCustomExceptionView(HttpServletRequest request, CustomViewException ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        HttpStatus status = getStatus(request);
        Map<String,String> map = new HashMap();
        map.put("code", String.valueOf(status.value()));
        map.put("msg", "customer error msg");
        return modelAndView;
    }

}
