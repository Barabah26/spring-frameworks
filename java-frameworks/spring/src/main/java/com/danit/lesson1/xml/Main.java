package com.danit.lesson1.xml;

import com.danit.lesson1.User;
import com.danit.lesson1.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        User user1 = context.getBean("user2", User.class);
        System.out.println(user1.hashCode());
        User user2 = context.getBean("user2", User.class);
        System.out.println(user2.hashCode());

    }
}
