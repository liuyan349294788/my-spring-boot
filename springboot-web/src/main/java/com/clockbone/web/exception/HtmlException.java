package com.clockbone.web.exception;

import lombok.Data;

/**
 * Created by clock on 2018/4/13.
 */
@Data
public class HtmlException extends Exception  {

    public HtmlException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private String code;
    private String msg;
}
