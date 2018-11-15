package com.clockbone.web.response;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "clockbone")
@Data
public class TestObj  implements Serializable{
    private static final long serialVersionUID = 7203028940875675470L;
    private String name;
    private String age;
    private String job;
    private Map<String,Object> map;
    private List<Integer> list;
    private Type type;
}
