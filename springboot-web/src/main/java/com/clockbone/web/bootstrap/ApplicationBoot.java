package com.clockbone.web.bootstrap;

import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;
/*import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;*/
import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * Created by clock on 2018/4/12.
 *
 */
//官网说明: 用@SpringBootApplication 注解和用 @Configuration @EnableAutoConfiguration @ComponentScan是等效的
@SpringBootApplication(scanBasePackages = "com.clockbone"/*,
        exclude={DataSourceAutoConfiguration.class}*/ ,
        exclude = {PageHelperAutoConfiguration.class,SecurityAutoConfiguration.class}) //exclude :
//// same as @Configuration @EnableAutoConfiguration @ComponentScan
//@MapperScan("com.clockbone.dao")
public class ApplicationBoot {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ApplicationBoot.class, args);
    }


    /**
     * The CommandLineRunner is a special kind of Spring bean that is executed when the application boots:
     * @return
     */
    /*@Bean
    public CommandLineRunner init(final RepositoryService repositoryService,
                                  final RuntimeService runtimeService,
                                  final TaskService taskService) {

        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {
                System.out.println("Number of process definitions : "
                        + repositoryService.createProcessDefinitionQuery().count());
                System.out.println("Number of tasks : " + taskService.createTaskQuery().count());
                runtimeService.startProcessInstanceByKey("oneTaskProcess");
                System.out.println("Number of tasks after process start: " + taskService.createTaskQuery().count());
            }
        };

    }*/


}