package com.clockbone.biz.service;
import com.clockbone.model.User;
import com.github.pagehelper.PageInfo;

import java.util.concurrent.Future;

/**
 * Created by clock on 2018/4/16.
 */
public interface UserService {

    User getUserInfo(Long id);

    Boolean syncUserInfo();

    User getPrimaryKey(Long id);

    PageInfo<User> getUserList(User user,int pageNo,int pageSize);

}
