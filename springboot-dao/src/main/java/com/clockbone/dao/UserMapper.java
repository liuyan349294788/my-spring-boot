package com.clockbone.dao;

import com.clockbone.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by clock on 2018/4/16.
 */
//@Mapper
public interface UserMapper {

    @Select("SELECT ID,USER_ID as userId,USER_NAME as userName FROM USER where id=#{id}")
    User getUserInfo(long id);

    User selectByPrimaryKey(Long id);

    List<User> getUserByParamter(User user);

}
