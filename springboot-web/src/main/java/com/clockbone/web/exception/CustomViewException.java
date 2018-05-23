package com.clockbone.web.exception;

/**
 * Created by clock on 2018/4/13.
 */
public class CustomViewException extends RuntimeException {

    public CustomViewException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private String code;
    private String msg;
}
