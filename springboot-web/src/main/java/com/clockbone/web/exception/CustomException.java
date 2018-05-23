package com.clockbone.web.exception;

/**
 * Created by clock on 2018/4/13.
 */
public class CustomException extends RuntimeException {

    public CustomException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private String code;
    private String msg;
}
