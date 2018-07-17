package com.clockbone.biz.service.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BusinessKey {

    LEAVE("LEAVE", "离职"),
    APPLY("APPLY", "请假"),
    BAOXIAO("BAOXIAO", "报销");
    private String key;
    private String name;

}
