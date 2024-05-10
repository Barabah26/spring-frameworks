package com.danit.lesson1.java;

import com.danit.lesson1.User;
import com.danit.lesson1.UserService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        UserService service = context.getBean("defaultUserService", UserService.class);

        List<User> users = service.getAll();
        System.out.println(users);
        System.out.println(Arrays.toString(context.getBeanDefinitionNames()));
    }
}
