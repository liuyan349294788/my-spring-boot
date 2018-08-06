package com.clockbone.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TblBusiness implements Serializable {
    private Long id;

    private Date createTime;

    private Date updateTime;

    private Long userId;
    private String reason;

    private String type;


}