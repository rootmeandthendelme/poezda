package ru.tututu.trains.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.tututu.trains.entity.User;
import ru.tututu.trains.mapper.UserMapper;
import ru.tututu.trains.utils.DataSourceProxy;
import ru.tututu.trains.utils.params.QueryParam;
import ru.tututu.trains.utils.params.StringParam;

import java.sql.SQLException;
import java.util.List;

@Component
public class UserRepo {
    @Autowired
    private final DataSourceProxy dataSourceProxy;

    public UserRepo(DataSourceProxy dataSourceProxy) {
        this.dataSourceProxy = dataSourceProxy;
    }

    public User findByLogin(String login) throws SQLException {
        String sql = "SELECT * FROM users WHERE users.login=?";
        QueryParam[] queryParams = new QueryParam[]{new StringParam(login)};

        List<User> users = dataSourceProxy.executeSelect(sql, new UserMapper(), queryParams);
        return !users.isEmpty() ? users.get(0) : null;
    }
}
