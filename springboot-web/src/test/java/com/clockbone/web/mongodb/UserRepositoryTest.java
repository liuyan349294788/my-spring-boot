package com.clockbone.web.mongodb;


import com.clockbone.entity.UserInfo;
import com.clockbone.mongodb.UserRepository;
import com.clockbone.web.AbstratApplicationBaseBootTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class UserRepositoryTest extends AbstratApplicationBaseBootTest{

    @Autowired
    UserRepository userRepository;

    @Test
    public void saveTest(){
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId("123");
        userInfo.setUserName("王五");
        userInfo.setAge("34");
        userInfo.setAddress("上海市黄埔区");
        userInfo.setMobileNo("123111111");
        userInfo.setIdcard("123123208093284234");
        userInfo.setIdcardType("0");
        userInfo.setHomeTel("432-9989");
        userInfo.setAsset("23424234.23423");
        userInfo.setPost("090000");
        userRepository.save(userInfo);

    }

    @Test
    public void selectTest(){
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId("123");
        userInfo.setUserName("王五");
        List<UserInfo> list = userRepository.selectByParameter(userInfo);
        log.info("list:{}",list);
    }
}
