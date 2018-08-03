package com.clockbone.biz.service;
import com.clockbone.model.Apply;
import com.clockbone.model.TblBusinessApply;
import com.clockbone.model.TblBusinessApplyRes;

import java.util.List;
import java.util.Map;

/**
 * Created by clock on 2018/4/16.
 */
public interface ApplyService {

    Apply apply(Apply apply);

    List<Apply> select(Apply apply);

    Integer update(Long id, String busStatus);

    /**
     * 查询审批流
     * @param tblBusinessApply
     * @return
     */
    List<TblBusinessApplyRes> selectApplyList(TblBusinessApply tblBusinessApply);

    /**
     * shen pi liucheng shenqing
     */
    void doProcess(String taskId,String message);

}
