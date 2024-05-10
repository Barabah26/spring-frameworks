package com.danit.lesson1.dynamic;

import com.danit.lesson1.UserDao;
import com.danit.lesson1.UserService;
import com.danit.lesson1.anno.DefaultUserService;
import com.danit.lesson1.anno.MockUserDao;

import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
        UserDao original = new MockUserDao();
        UserDaoHandler handler = new UserDaoHandler(original);
        UserDao proxyDao = (UserDao) Proxy.newProxyInstance(
                UserDao.class.getClassLoader(),
                new Class[] { UserDao.class },
                handler);
        UserService userService = new DefaultUserService(proxyDao);
        System.out.println(userService.getAll());
    }
}
