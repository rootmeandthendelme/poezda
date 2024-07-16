package ru.tututu.trains.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.tututu.trains.entity.CarriegeType;
import ru.tututu.trains.mapper.CarriegeTypeMapper;
import ru.tututu.trains.utils.DataSourceProxy;
import ru.tututu.trains.utils.params.IntegerParam;
import ru.tututu.trains.utils.params.QueryParam;

import java.sql.SQLException;

@Component
public class CarriegeTypeRepo {
    @Autowired
    private final DataSourceProxy dataSourceProxy;

    public CarriegeTypeRepo(DataSourceProxy dataSourceProxy) {
        this.dataSourceProxy = dataSourceProxy;
    }

    public CarriegeType getCarriegeTypeById(int id) throws SQLException {
        String sql = "SELECT * FROM carriege_type WHERE id=?";
        QueryParam[] queryParams = new QueryParam[]{new IntegerParam(id)};
        return dataSourceProxy.executeSelect(sql, new CarriegeTypeMapper(), queryParams).get(0);
    }
}
