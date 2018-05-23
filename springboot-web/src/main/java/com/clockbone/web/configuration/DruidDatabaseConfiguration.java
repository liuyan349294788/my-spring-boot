package com.clockbone.web.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by clock on 2018/4/16.
 * 数据库连接池配置
 */
@Configuration
@MapperScan(basePackages = "com.clockbone.dao",sqlSessionFactoryRef = "sqlSessionFactory")
public class DruidDatabaseConfiguration {

    /*@Value(value = "spring.datasource.url")
    private String dbUrl;
    @Value(value = "spring.datasource.username")
    private String username;
    @Value(value = "spring.datasource.password")
    private String password;
    @Value(value = "spring.datasource.driver-class-name")
    private String driverClassName;
    @Value(value = "spring.druid.initialSize")
    private int initialSize;
    @Value(value = "spring.druid.minIdle")
    private int minIdle;
    @Value(value = "spring.druid.maxActive")
    private int maxActive;
    @Value(value = "spring.druid.maxWait")
    private int maxWait;
    @Value(value = "spring.druid.timeBetweenEvictionRunsMillis")
    private int timeBetweenEvictionRunsMillis;
    @Value(value = "spring.druid.minEvictableIdleTimeMillis")
    private int minEvictableIdleTimeMillis;
    @Value(value = "spring.druid.validationQuery")
    private String validationQuery;
    @Value(value = "spring.druid.testWhileIdle")
    private boolean testWhileIdle;
    @Value(value = "spring.druid.testOnBorrow")
    private boolean testOnBorrow;
    @Value(value = "spring.druid.testOnReturn")
    private boolean testOnReturn;
    @Value(value = "spring.druid.poolPreparedStatements")
    private boolean poolPreparedStatements;
    @Value(value = "spring.druid.maxPoolPreparedStatementPerConnectionSize")
    private int maxPoolPreparedStatementPerConnectionSize;
    @Value(value = "spring.druid.filtersl")
    private String filters;
    @Value(value = "spring.druid.connectionProperties")
    private String connectionProperties;*/

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    @Primary
    public DruidDataSource dataSource() throws SQLException {
        DruidDataSource datasource = new DruidDataSource();
        /*datasource.setUrl(this.dbUrl);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);

        //configuration
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxActive(maxActive);
        datasource.setMaxWait(maxWait);
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setValidationQuery(validationQuery);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setTestOnBorrow(testOnBorrow);
        datasource.setTestOnReturn(testOnReturn);
        datasource.setPoolPreparedStatements(poolPreparedStatements);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);

        datasource.setFilters(filters);*/

        return datasource;
    }

    @Bean
    public DataSourceTransactionManager transactionManager() throws SQLException {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));

        Interceptor pageHelper = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);
        sessionFactory.setPlugins(new Interceptor[]{pageHelper});

        return sessionFactory.getObject();
    }

}
