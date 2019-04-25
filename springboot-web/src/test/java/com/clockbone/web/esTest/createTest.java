package com.clockbone.web.esTest;

import com.clockbone.esentity.Item;
import com.clockbone.esentity.User;
import com.clockbone.web.AbstratApplicationBaseBootTest;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.AliasQuery;

import java.io.IOException;

public class createTest extends AbstratApplicationBaseBootTest{

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void createIndexTest(){
        //elasticsearchTemplate.createIndex(Item.class);
        //elasticsearchTemplate.createIndex(User.class);
        elasticsearchTemplate.deleteIndex(User.class);
    }



    @Test
    public void addDocTEST() throws IOException {




    }
}
