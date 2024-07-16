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
import java.util.Optional;

@Component
public class UserRepo {
    @Autowired
    private final DataSourceProxy dataSourceProxy;

    public UserRepo(DataSourceProxy dataSourceProxy) {
        this.dataSourceProxy = dataSourceProxy;
    }

    public Optional<User> findByLogin(String login) throws SQLException {
        String sql = "SELECT * FROM users WHERE users.login=?";
        QueryParam[] queryParams = new QueryParam[]{new StringParam(login)};
        List<User> users = dataSourceProxy.executeSelect(sql, new UserMapper(), queryParams);

        return users.isEmpty() ? Optional.empty() : Optional.of(users.get(0));
    }
}
