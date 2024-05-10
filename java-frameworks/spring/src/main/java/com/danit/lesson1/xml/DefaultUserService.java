package com.danit.lesson1.xml;

import com.danit.lesson1.User;
import com.danit.lesson1.UserDao;
import com.danit.lesson1.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.List;


public class DefaultUserService implements UserService, ApplicationContextAware {
    private UserDao userDao;

    public DefaultUserService() {
    }

    public DefaultUserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getAll() {
        return userDao.findAll();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        // TODO use context
    }
}
