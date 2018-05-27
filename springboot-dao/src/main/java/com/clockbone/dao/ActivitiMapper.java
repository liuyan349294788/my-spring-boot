package com.clockbone.dao;

import com.clockbone.model.Apply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ActivitiMapper {

    void insert(Apply apply);

    List<Apply> select(@Param("param") Apply apply);

    Integer updateStatus(@Param("id") Long id,@Param("busStatus") String busStatus);

}
