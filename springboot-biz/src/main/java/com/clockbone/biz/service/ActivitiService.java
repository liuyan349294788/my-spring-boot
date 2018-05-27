package com.clockbone.biz.service;
import com.clockbone.model.Apply;
import com.clockbone.model.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by clock on 2018/4/16.
 */
public interface ActivitiService {

    Apply apply(Apply apply);

    List<Apply> select(Apply apply);

    Integer update(Long id,String busStatus);

}
