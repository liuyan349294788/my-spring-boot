package com.clockbone.web.bootstrap;

import com.clockbone.web.dialect.WorkFocusDialect;
import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;
/*import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;*/
import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by clock on 2018/4/12.
 *
 */
//官网说明: 用@SpringBootApplication 注解和用 @Configuration @EnableAutoConfiguration @ComponentScan是等效的
@SpringBootApplication(scanBasePackages = {"com.clockbone","org.activiti"} /*,
        exclude={DataSourceAutoConfiguration.class}*/ ,
        exclude = {
                org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
                org.activiti.spring.boot.SecurityAutoConfiguration.class,
                //org.springframework.boot.actuate.autoconfigure.ManagementWebSecurityAutoConfiguration.class
                PageHelperAutoConfiguration.class,
                }) //exclude :
//// same as @Configuration @EnableAutoConfiguration @ComponentScan
//@MapperScan("com.clockbone.dao")
//@EnableGlobalMethodSecurity(securedEnabled = false)
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

    @Bean
    @ConditionalOnMissingBean
    public WorkFocusDialect wlfDialect() {
        return new WorkFocusDialect();
    }


}
