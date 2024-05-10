package com.danit.lesson1.anno;

import com.danit.lesson1.User;
import com.danit.lesson1.UserDao;
import com.danit.lesson1.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultUserService implements UserService {
    private UserDao userDao;

    public DefaultUserService() {
    }

    @Autowired
    public DefaultUserService(/*@Qualifier("uDao") */UserDao userDao) { // List<UserDao> dao - inject all deps
        this.userDao = userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getAll() {
        return userDao.findAll();
    }
}
