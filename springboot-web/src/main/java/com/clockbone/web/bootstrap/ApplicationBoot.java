package com.clockbone.web.bootstrap;

import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

/**
 * Created by clock on 2018/4/12.
 *
 */
//官网说明: 用@SpringBootApplication 注解和用 @Configuration @EnableAutoConfiguration @ComponentScan是等效的
// The @SpringBootApplication annotation is equivalent to using @Configuration, @EnableAutoConfiguration, and @ComponentScan with their default attributes, as shown in the following example:
@SpringBootApplication(scanBasePackages = "com.clockbone"/*,
        exclude={DataSourceAutoConfiguration.class}*/ ,exclude = {PageHelperAutoConfiguration.class}) //exclude : 排除数据库源自动配置,如果不用数据库
//// same as @Configuration @EnableAutoConfiguration @ComponentScan
//@MapperScan("com.clockbone.dao")
public class ApplicationBoot {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ApplicationBoot.class, args);
    }


}
