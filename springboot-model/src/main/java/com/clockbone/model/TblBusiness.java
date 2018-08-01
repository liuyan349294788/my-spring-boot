package com.clockbone.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TblBusiness implements Serializable {
    private Long id;

    private Date createTime;

    private Date updateTime;

    //申请人
    private Long userId;
    //原因
    private String reason;


}