package ru.tututu.trains.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.tututu.trains.entity.Train;
import ru.tututu.trains.mapper.TrainMapper;
import ru.tututu.trains.utils.DataSourceProxy;
import ru.tututu.trains.utils.params.IntegerParam;
import ru.tututu.trains.utils.params.QueryParam;

import java.sql.SQLException;

@Component
public class TrainRepo {
    @Autowired
    private final DataSourceProxy dataSourceProxy;

    public TrainRepo(DataSourceProxy dataSourceProxy) {
        this.dataSourceProxy = dataSourceProxy;
    }

    public Train getTrainById(int trainId) throws SQLException {
        String sql = "SELECT * FROM train\n" +
                "WHERE id=?";
        QueryParam[] queryParams = new QueryParam[]{new IntegerParam(trainId)};
        return dataSourceProxy.executeSelect(sql, new TrainMapper(), queryParams).get(0);
    }
}
