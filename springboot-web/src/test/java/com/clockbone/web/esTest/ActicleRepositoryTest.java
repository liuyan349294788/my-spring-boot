package com.clockbone.web.esTest;

import com.clockbone.esentity.Acticle;
import com.clockbone.esrepository.ActicleRepository;
import com.clockbone.web.AbstratApplicationBaseBootTest;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import java.io.IOException;

public class ActicleRepositoryTest extends AbstratApplicationBaseBootTest{

    @Autowired
    private ActicleRepository acticleRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;


    @Test
    public void createIndexTest1() throws IOException {
        /*XContentBuilder mapping = XContentFactory.jsonBuilder()
                .startObject()
                .startObject("properties")
                //分类id
                .startObject("id").field("type","long").endObject()
                //分类名称
                .startObject("category").field("type","text").endObject()
                .startObject("range").field("type","text").endObject()
                .startObject("mode").field("type","text").endObject()
                //开始时间
                .startObject("start_time").field("type","date").field("format","yyyy-MM-dd HH:mm:ss").endObject()
                .startObject("end_time").field("type","date").field("format","yyyy-MM-dd HH:mm:ss").endObject()
                .startObject("title").field("type","text").field("analyzer","ik_smart").endObject()
                .startObject("content").field("type","text").field("analyzer","ik_smart").endObject()
                .endObject()
                .endObject();*/
        elasticsearchTemplate.createIndex(Acticle.class);
        //elasticsearchTemplate.putMapping("acticle1","acticle_record1",mapping);
        //elasticsearchTemplate.addAlias(new AliasQuery().setAliasName("actile_alias"))
        //设置别名，好处数据 索引拓展的时候，代码不受影响
        //GET acticle1/_search


    }


    @Test
    public void insertTest(){
        Acticle acticle = Acticle.builder().id(6L).
                category("KPXXHZ").
                mode("INSTANT").
                start_time("2018-12-19 11:58:48").
                end_time("2018-12-20 10:58:48").
                title("开票信息汇总").
                content("").
                build();
        acticleRepository.save(acticle);
    }
    /*@Test
    public void queryTest(){
        //按年龄排序返回
        *//*Iterable<User> iterable = userRepository.findAll(Sort.by("age").ascending());
        for(User eachUser : iterable){
            System.out.println(eachUser);
        }*//*

        Iterable<User> iterable1 = acticleRepository.findByAgeBetween(30,31);
        for(User eachUser : iterable1){
            System.out.println(eachUser);
        }
    }*/

    @Test
    public void matchTest(){
        // 构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加基本分词查询
        queryBuilder.withQuery(QueryBuilders.matchQuery("title", "开票信息"));

        /*queryBuilder.withQuery(
                QueryBuilders.boolQuery()
                        .should(QueryBuilders.matchQuery("title", "张三"))
                        .should(QueryBuilders.matchQuery("content", "张三"))
        );*/
        // 搜索，获取结果
        Page<Acticle> acticles = this.acticleRepository.search(queryBuilder.build());
        // 总条数
        long total = acticles.getTotalElements();
        System.out.println("total = " + total);
        for (Acticle each : acticles) {
            System.out.println(each);
        }


    }


    @Test
    public void searchByPage(){
        // 构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加基本分词查询
        queryBuilder.withQuery(QueryBuilders.termQuery("age", "30"));
        // 分页：
        int page = 0;
        int size = 10;
        queryBuilder.withPageable(PageRequest.of(page,size));

        // 搜索，获取结果
        Page<Acticle> uspage = acticleRepository.search(queryBuilder.build());
        System.out.println(uspage);

        // 总条数
        long total = uspage.getTotalElements();
        System.out.println("总条数 = " + total);
        // 总页数
        System.out.println("总页数 = " + uspage.getTotalPages());
        // 当前页
        System.out.println("当前页：" + uspage.getNumber());
        // 每页大小
        System.out.println("每页大小：" + uspage.getSize());

        for (Acticle user : uspage) {
            System.out.println(user);
        }
    }

}
