package com.clockbone.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TblBusinessApply {
    private Long id;

    private Date createTime;

    private Date updateTime;

    private String processId;

    private String processName;

    private Long businessId;

    private String busStatus;

    private Long createUserId;

}