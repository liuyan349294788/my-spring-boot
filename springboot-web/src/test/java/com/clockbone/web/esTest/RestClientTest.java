package com.clockbone.web.esTest;

import com.clockbone.web.AbstratApplicationBaseBootTest;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.ResponseException;
import org.elasticsearch.client.RestClient;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.util.Collections;

/**
 * @author: jun_qin
 * @create: 2019/4/25 15:13
 **/
@Slf4j
public class RestClientTest  extends AbstratApplicationBaseBootTest {

    @Autowired
    private RestClient restClient;

    /**
     *
     * curl -XGET "http://localhost:9200/account,acticle1_test/_search" -H 'Content-Type: application/json' -d'
     {
     "query": {
     "bool": {
     "should": [
     {
     "match":
     {
     "title":"张三"
     }
     },
     {
     "match": {
     "name": "张三"
     }
     }
     ]
     }
     }
     }'
     * @throws IOException
     */

    @Test
    public void searchTest() throws IOException {

        String endpoint = "/account,acticle1_test/_search";
        String keyword = "开票信息汇总";

        String json = "{\n" +
                "     \"query\": {\n" +
                "     \"match\": {\n" +
                "     \"title\": \""+keyword+"\"\n" +
                "     }\n" +
                "     }\n" +
                "     }";

        json="{\n" +
                "     \"query\": {\n" +
                "     \"bool\": {\n" +
                "     \"should\": [\n" +
                "     {\n" +
                "     \"match\": \n" +
                "     {\n" +
                "     \"title\":\"张三\"\n" +
                "     }\n" +
                "     },\n" +
                "     {\n" +
                "     \"match\": {\n" +
                "     \"name\": \"张三\"\n" +
                "     }\n" +
                "     }\n" +
                "     ]\n" +
                "     }\n" +
                "     }\n" +
                "     }";
        HttpEntity entity = new NStringEntity(json, ContentType.APPLICATION_JSON);

        Response response = restClient.performRequest("POST",endpoint, Collections.<String, String>emptyMap(),entity);
        System.out.println(EntityUtils.toString(response.getEntity()));


    }
}
