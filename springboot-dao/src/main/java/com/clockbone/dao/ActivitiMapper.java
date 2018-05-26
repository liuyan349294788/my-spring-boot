package com.clockbone.dao;

import com.clockbone.model.Apply;

import java.util.List;

public interface ActivitiMapper {

    void insert(Apply apply);

    List<Apply> select();

}
