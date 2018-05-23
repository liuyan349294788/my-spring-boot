package com.clockbone.web.exception;

import lombok.Data;

/**
 * Created by clock on 2018/4/13.
 */
@Data
public class CustomErrorType {

    public CustomErrorType(Integer status,String message){
        this.status = status;
        this.message = message;
    }

    private Integer status;

    private String message;
}
