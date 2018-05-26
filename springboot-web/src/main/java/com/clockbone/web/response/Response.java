package com.clockbone.web.response;

import lombok.Data;

@Data
public class Response<T> {

    private String code;
    private String errorMsg;
    private T data;

    public Response(){
        this.code = "200";
    }
    public Response(String errorCode){
        this.code = errorCode;
        this.errorMsg = "失败";
    }
    public Response(String errorCode,String errorMsg){
        this.code = errorCode;
        this.errorMsg = errorMsg;
    }

    public  static Response ok(){
        return new Response();
    }
    public  Response(T data){
        this.data = data;
        this.code = "200";
    }


}
