package ru.tututu.trains.mapper;

import org.springframework.stereotype.Component;
import ru.tututu.trains.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserMapper implements ResultSetMapper<User>{

    @Override
    public User map(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setFullName(resultSet.getString("full_name"));
        user.setLogin(resultSet.getString("login"));
        user.setId(resultSet.getInt("id"));
        user.setPassword(resultSet.getString("password"));
        user.setRole(resultSet.getString("role"));
        return user;
    }
}
