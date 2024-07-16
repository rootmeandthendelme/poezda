package ru.tututu.trains.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.tututu.trains.entity.Locality;
import ru.tututu.trains.mapper.LocalityMapper;
import ru.tututu.trains.mapper.StationMapper;
import ru.tututu.trains.utils.DataSourceProxy;
import ru.tututu.trains.utils.params.IntegerParam;
import ru.tututu.trains.utils.params.QueryParam;

import java.sql.SQLException;

@Component
public class LocalityRepo {
    @Autowired
    private final DataSourceProxy dataSourceProxy;

    public LocalityRepo(DataSourceProxy dataSourceProxy) {
        this.dataSourceProxy = dataSourceProxy;
    }

    public Locality getLocalityById(int id) throws SQLException {
        String sql = "SELECT * FROM locality WHERE id=?";
        QueryParam[] queryParams = new QueryParam[]{new IntegerParam(id)};
        return dataSourceProxy.executeSelect(sql, new LocalityMapper(), queryParams).get(0);
    }
}
