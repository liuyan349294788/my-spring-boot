package com.clockbone.web.biz;

import com.clockbone.biz.service.UserService;
import com.clockbone.model.User;
import com.clockbone.web.AbstratApplicationBaseBootTest;
import com.clockbone.web.ApplicationBaseBootTest;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by clock on 2018/4/16.
 */
@Slf4j
public class UserServiceTest extends AbstratApplicationBaseBootTest {

    @Resource
    private UserService userService;

    @Test
    public void userServiceTest(){
        long id = 10;
        User user = userService.getPrimaryKey(id);
        log.info("user={}",user);

    }

    @Test
    public void userPageInfoTest(){
        User user = new User();
        user.setUserName("test");
        PageInfo<User> pageInfo = userService.getUserList(user,1,10);
        log.info("getTotal:{}",pageInfo.getTotal());
        log.info("getPageNum:{}",pageInfo.getPageNum());
        log.info("getPageSize:{}",pageInfo.getPageSize());
        log.info("page list:{}",pageInfo.getList());
    }
}
