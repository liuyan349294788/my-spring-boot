package com.clockbone.mongodb;

import com.clockbone.entity.UserInfo;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class UserRepository {

    @Resource
    private MongoTemplate mongoTemplate;

    public void save(UserInfo userInfo){
        userInfo.setCreateTime(new Date());
        mongoTemplate.save(userInfo);
    }

    public List<UserInfo> selectByParameter(UserInfo userInfo){
        Criteria criteria = Criteria
                .where("userId").is(userInfo.getUserId())
                .and("userName").is(userInfo.getUserName())
                ;
        Query query = new Query(criteria).with(new Sort(Sort.Direction.DESC, "createTime"));
        return mongoTemplate.find(query, UserInfo.class);

    }
}
