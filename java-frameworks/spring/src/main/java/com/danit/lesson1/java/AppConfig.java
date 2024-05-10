package com.danit.lesson1.java;

import com.danit.lesson1.UserDao;
import com.danit.lesson1.UserService;
import com.danit.lesson1.anno.DefaultUserService;
import com.danit.lesson1.anno.JdbcUserDao;
import com.danit.lesson1.anno.MockUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.danit.lesson1.anno")
@PropertySource({ "/application.properties" })
public class AppConfig {

    @Autowired
    private Environment environment;

/*    @Bean(initMethod = "init")
    public MockUserDao mockUserDao() {
        return new MockUserDao();
    }

    @Bean
    @Qualifier("jdbcUserDao")
    public JdbcUserDao jdbcUserDao(@Autowired DataSource dataSource) {
        return new JdbcUserDao(dataSource);
    }

    @Bean(name = "service")
    public UserService defaultUserService(@Qualifier("jdbcUserDao") @Autowired UserDao userDao) {
        return new DefaultUserService(userDao);
    }*/

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(environment.getProperty("jdbc.url").trim());
        dataSource.setUsername(environment.getProperty("jdbc.username").trim());
        dataSource.setPassword(environment.getProperty("jdbc.password").trim());
        return dataSource;
    }
}
