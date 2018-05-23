package com.clockbone.service.response;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by BF400254 on 2018/4/12.
 */
@Data
@XmlRootElement
public class MyThingXmlResDto {

    private String close;

    private String open;
}
