package com.clockbone.model;

import lombok.Data;

import java.util.Date;

@Data
public class Apply {
    private Long id;
    private String name;
    private String reason;
    private String checkName;
    private String busStatus;
    private Date createTime;
    private Date updateTime;

    //审批流类型
    private String businessKey;
}
