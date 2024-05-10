package com.danit.lesson1.anno;

import com.danit.lesson1.User;
import com.danit.lesson1.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
@Lazy
@Profile("dev")
public class JdbcUserDao implements UserDao {
    private DataSource dataSource;

    @Autowired
    public JdbcUserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<User> findAll() {
        Connection connection = null;
        List<User> users = new ArrayList<>();
        try {
            connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while(resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString(2);
                int age = resultSet.getInt("age");
                Long groupId = resultSet.getLong("group_id");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                users.add(new User(id, name, age, groupId, login, password));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return users;
    }
}
