package com.danit.lesson1.xml;

import com.danit.lesson1.User;
import com.danit.lesson1.UserDao;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

public class MockUserDao implements UserDao {
    private List<User> users;

    public void init() {
        users = List.of(
                new User("Poroshenko", 55, null, "poroh", "poroh"),
                new User("Zelensky", 45, null, "ze", "ze")
        );
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setUserNames(List<String> names) {
        users = new ArrayList<>(names.size());
        for (String name : names) {
            users.add(new User(name));
        }
    }
}
