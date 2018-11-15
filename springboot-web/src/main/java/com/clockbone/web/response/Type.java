package com.clockbone.web.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class Type implements Serializable{
    private String name;
    private String age;
}
