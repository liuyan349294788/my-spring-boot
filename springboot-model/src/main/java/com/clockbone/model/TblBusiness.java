package com.clockbone.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TblBusiness {
    private Long id;

    private Date createTime;

    private Date updateTime;


}