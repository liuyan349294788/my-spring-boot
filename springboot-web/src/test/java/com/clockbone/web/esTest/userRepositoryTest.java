package com.clockbone.web.esTest;

import com.clockbone.esentity.User;
import com.clockbone.esrepository.UserRepository;
import com.clockbone.web.AbstratApplicationBaseBootTest;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

public class userRepositoryTest extends AbstratApplicationBaseBootTest{

    @Autowired
    private UserRepository userRepository;

    @Test
    public void insertTest(){
        User user = User.builder().age(30D).hob("游泳").images("http://").name("张三").id("1").build();
        User user1 = User.builder().age(32D).hob("画画").images("http://").name("李四").id("2").build();
        User user3 = User.builder().age(34D).hob("唱歌").images("http://").name("王五").id("3").build();
        User user31 = User.builder().age(30D).hob("唱歌").images("http://").name("王五").id("4").build();
        User user32 = User.builder().age(30D).hob("唱歌").images("http://").name("王五").id("5").build();
        User user33 = User.builder().age(30D).hob("唱歌").images("http://").name("王五").id("6").build();
        userRepository.save(user31);
        userRepository.save(user32);
        userRepository.save(user33);
    }
    @Test
    public void queryTest(){
        //按年龄排序返回
        /*Iterable<User> iterable = userRepository.findAll(Sort.by("age").ascending());
        for(User eachUser : iterable){
            System.out.println(eachUser);
        }*/

        Iterable<User> iterable1 = userRepository.findByAgeBetween(30,31);
        for(User eachUser : iterable1){
            System.out.println(eachUser);
        }
    }

    @Test
    public void matchTest(){
        // 构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加基本分词查询
        queryBuilder.withQuery(QueryBuilders.matchQuery("name", "张三"));

        queryBuilder.withQuery(
                QueryBuilders.boolQuery()
                        .must(QueryBuilders.matchQuery("name", "张三"))
                        .must(QueryBuilders.matchQuery("name", "张三"))
        );
        // 搜索，获取结果
        Page<User> users = this.userRepository.search(queryBuilder.build());
        // 总条数
        long total = users.getTotalElements();
        System.out.println("total = " + total);
        for (User each : users) {
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
        Page<User> uspage = userRepository.search(queryBuilder.build());
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

        for (User user : uspage) {
            System.out.println(user);
        }
    }

}
