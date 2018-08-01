package com.clockbone.biz.service.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BusinessKey {

    LEAVE("leave", "离职"),
    APPLY("apply", "请假"),
    BAOXIAO("baoxiao", "报销");
    private String key;
    private String name;



}
