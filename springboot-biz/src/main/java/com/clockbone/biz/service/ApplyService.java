package com.clockbone.biz.service;
import com.clockbone.model.Apply;

import java.util.List;

/**
 * Created by clock on 2018/4/16.
 */
public interface ApplyService {

    Apply apply(Apply apply);

    List<Apply> select(Apply apply);

    Integer update(Long id, String busStatus);

}
