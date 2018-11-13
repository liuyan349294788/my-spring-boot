package com.clockbone.entity;


import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Setter
@Getter
@ToString
@Builder
@Document(collection = "user_info")
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

    private String userName;
    private String userId;
    private String sex;
    private String age;
    private String job;
    private String address;
    private String idcard;
    private String idcardType;
    private String mobileNo;
    private String homeTel;
    private String post;
    private String asset;

    private Date createTime;

}
