package ru.tututu.trains.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.tututu.trains.entity.Carriege;
import ru.tututu.trains.mapper.CarriegeMapper;
import ru.tututu.trains.utils.DataSourceProxy;
import ru.tututu.trains.utils.params.IntegerParam;
import ru.tututu.trains.utils.params.QueryParam;

import java.sql.SQLException;
import java.util.List;

@Component
public class CarriegeRepo {
    @Autowired
    private final DataSourceProxy dataSourceProxy;

    public CarriegeRepo(DataSourceProxy dataSourceProxy) {
        this.dataSourceProxy = dataSourceProxy;
    }

    public List<Carriege> getCarriegesByTrainId(int trainId) throws SQLException {
        String sql = "SELECT * FROM carriege WHERE carriege.train_id=?";
        QueryParam[] queryParams = new QueryParam[]{new IntegerParam(trainId)};
        return dataSourceProxy.executeSelect(sql, new CarriegeMapper(), queryParams);
    }
}
