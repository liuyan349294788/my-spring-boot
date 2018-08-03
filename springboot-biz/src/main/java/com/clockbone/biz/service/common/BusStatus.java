package com.clockbone.biz.service.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BusStatus {

    WAIT("WAIT", "等待审核"),
    DONE("DONE", "完成"),
    REJECT("REJECT", "拒绝");
    private String key;
    private String name;

}
