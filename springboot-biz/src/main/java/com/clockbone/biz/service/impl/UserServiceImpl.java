package com.clockbone.biz.service.impl;

import com.clockbone.biz.service.UserService;
import com.clockbone.biz.service.task.UserSyncTask;
import com.clockbone.dao.UserMapper;
import com.clockbone.model.User;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.Future;

/**
 * Created by clock on 2018/4/16.
 */
@Service(value = "userService")
@Slf4j
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Resource
    private UserSyncTask userSyncTask;


    @Override
    public User getUserInfo(Long id) {
        return userMapper.getUserInfo(id);
        //return null;
    }

    @Override
    public Boolean syncUserInfo() {
        log.info("sys");
        log.info("syncUserInfo begin");
        Future<String>  stringFuture =  userSyncTask.userInfoSync();
        while (!stringFuture.isDone()){

        }
        log.info("syncUserInfo end");
        return false;
    }

    @Override
    public User getPrimaryKey(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<User> getUserList(User user,int pageNo,int pageSize) {
        //设置分页参数，执行下面紧跟的一条sql会自动拦截
        PageHelper.startPage(pageNo,pageSize);
        List<User> userList = userMapper.getUserByParamter(user);
        //放到pageInfo对象中
        PageInfo<User> userPage = new PageInfo<>(userList);
        return userPage;
    }
}
