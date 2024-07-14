package ru.tututu.trains.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.tututu.trains.mapper.ResultSetMapper;
import ru.tututu.trains.utils.params.QueryParam;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataSourceProxy {
    @Autowired
    private final DataSource dataSource;

    public DataSourceProxy(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public <T> List<T> executeSelect(String sql, ResultSetMapper<T> resultSetMapper, QueryParam... params) throws SQLException {
        PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(sql);
        setParams(preparedStatement, params);
        preparedStatement.execute();

        return mapResultSet(preparedStatement.getResultSet(), resultSetMapper);
    }

    private void setParams(PreparedStatement preparedStatement, QueryParam... params) throws SQLException {
        for(int i =0; i < params.length ; i++){
            params[i].setQueryParamValueToStatement(preparedStatement, i+1);
        }
    }

    private <T> List<T> mapResultSet(ResultSet resultSet, ResultSetMapper<T> resultSetMapper) throws SQLException {
        List<T> list = new ArrayList<>();

        while (resultSet.next()){
            list.add(resultSetMapper.map(resultSet));
        }

        return list;
    }
}
