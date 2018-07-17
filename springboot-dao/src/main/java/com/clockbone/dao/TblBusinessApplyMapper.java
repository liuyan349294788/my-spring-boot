package com.clockbone.dao;

import com.clockbone.model.TblBusinessApply;

public interface TblBusinessApplyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TblBusinessApply record);

    int insertSelective(TblBusinessApply record);

    TblBusinessApply selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TblBusinessApply record);

    int updateByPrimaryKey(TblBusinessApply record);
}