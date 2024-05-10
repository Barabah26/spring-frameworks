package com.danit.lesson1.anno;

import com.danit.lesson1.User;
import com.danit.lesson1.UserService;
import com.danit.lesson1.xml.DefaultUserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context-anno.xml");

        // retrieve configured instance
        UserService service = context.getBean("defaultUserService", UserService.class);

        // use configured instance
        List<User> users = service.getAll();
        System.out.println(users);
    }
}
