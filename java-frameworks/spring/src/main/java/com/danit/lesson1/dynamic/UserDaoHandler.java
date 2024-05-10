package com.danit.lesson1.dynamic;

import com.danit.lesson1.User;
import com.danit.lesson1.UserDao;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Collections;

public class UserDaoHandler implements InvocationHandler {
    private UserDao userDao;

    public UserDaoHandler(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Object invoke(Object dao, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("findAll")) {
            return Collections.singletonList(new User(""));
        }
        method.invoke(dao, args);
        // TODO
        return null;
    }
}
