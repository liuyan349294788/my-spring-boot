package com.clockbone.web.bootstrap;

import com.clockbone.web.dialect.WorkFocusDialect;
import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;
/*import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;*/
import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.ServletContextResource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.resource.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
@EnableMongoRepositories("com.clockbone.mongodb")
@EnableElasticsearchRepositories("com.clockbone.esrepository")
public class ApplicationBoot {

    public static void main(String[] args) throws Exception {

        // 设置环境变量，解决Es的netty与Netty服务本身不兼容问题
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(ApplicationBoot.class, args);
    }


    /*@Bean
    public SimpleUrlHandlerMapping simpleUrlHandlerMapping() {
        SimpleUrlHandlerMapping simpleUrlHandlerMapping = new SimpleUrlHandlerMapping();
        Map<String, Object> map = new LinkedHashMap<>();
        ResourceHttpRequestHandler resourceHttpRequestHandler = new ResourceHttpRequestHandler();
        List<Resource> locations = new ArrayList<>();
        locations.add(new ServletContextResource(getServletContext(), "/"));
        locations.add(new ClassPathResource("META-INF/resources"));
        locations.add(new ClassPathResource("resources/"));
        locations.add(new ClassPathResource("static/"));
        locations.add(new ClassPathResource("public/"));
        resourceHttpRequestHandler.setLocations(locations);
        resourceHttpRequestHandler.setApplicationContext(getApplicationContext());

        List<ResourceResolver> resourceResolvers = new ArrayList<>();
        PathResourceResolver resourceResolver = new PathResourceResolver();
        resourceResolver.setAllowedLocations(new ServletContextResource(getServletContext(), "/"), new ClassPathResource("META-INF/resources"), new ClassPathResource("resources/"), new ClassPathResource("static/"), new ClassPathResource("public/"));
        resourceResolvers.add(resourceResolver);

        resourceHttpRequestHandler.setResourceResolvers(resourceResolvers);
        map.put("/**", resourceHttpRequestHandler);
        simpleUrlHandlerMapping.setUrlMap(map);
        ResourceUrlProvider resourceUrlProvider = new ResourceUrlProvider();
        Map<String, ResourceHttpRequestHandler> handlerMap = new LinkedHashMap<>();
        handlerMap.put("/**", resourceHttpRequestHandler);
        resourceUrlProvider.setHandlerMap(handlerMap);
        ResourceUrlProviderExposingInterceptor interceptor = new ResourceUrlProviderExposingInterceptor(resourceUrlProvider);
        simpleUrlHandlerMapping.setInterceptors(new Object[]{interceptor});
        return simpleUrlHandlerMapping;
    }*/

    /***
     * 进行静态资源的配置
     */
   /* @Component
    public class StaticResourceConfig implements WebMvcConfigurer {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        }
    }*/

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
