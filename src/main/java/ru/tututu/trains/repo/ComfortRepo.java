package ru.tututu.trains.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.tututu.trains.entity.Comfort;
import ru.tututu.trains.mapper.ComfortMapper;
import ru.tututu.trains.utils.DataSourceProxy;
import ru.tututu.trains.utils.params.IntegerParam;
import ru.tututu.trains.utils.params.QueryParam;

import java.sql.SQLException;
import java.util.List;

@Component
public class ComfortRepo {
    @Autowired
    private final DataSourceProxy dataSourceProxy;

    public ComfortRepo(DataSourceProxy dataSourceProxy) {
        this.dataSourceProxy = dataSourceProxy;
    }

    public List<Comfort> getAllComfortByTrainId(int trainId) throws SQLException {
        String sql = "SELECT comfort.id, comfort.value FROM comfort\n" +
                "JOIN train_comfort ON train_comfort.comfort_id=comfort.id\n" +
                "WHERE train_comfort.train_id=?";
        QueryParam[] queryParams = new QueryParam[]{new IntegerParam(trainId)};
        return dataSourceProxy.executeSelect(sql, new ComfortMapper(), queryParams);
    }
}
