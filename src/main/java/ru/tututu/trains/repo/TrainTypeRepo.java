package ru.tututu.trains.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.tututu.trains.entity.TrainType;
import ru.tututu.trains.mapper.TrainTypeMapper;
import ru.tututu.trains.utils.DataSourceProxy;
import ru.tututu.trains.utils.params.IntegerParam;
import ru.tututu.trains.utils.params.QueryParam;

import java.sql.SQLException;

@Component
public class TrainTypeRepo {
    @Autowired
    private final DataSourceProxy dataSourceProxy;

    public TrainTypeRepo(DataSourceProxy dataSourceProxy) {
        this.dataSourceProxy = dataSourceProxy;
    }

    public TrainType getTrainTypeById(int id) throws SQLException {
        String sql = "SELECT * FROM train_type\n" +
                "WHERE id=?";
        QueryParam[] queryParam = new QueryParam[]{new IntegerParam(id)};
        return dataSourceProxy.executeSelect(sql, new TrainTypeMapper(), queryParam).get(0);
    }
}
