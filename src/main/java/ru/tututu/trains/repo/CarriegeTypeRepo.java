package ru.tututu.trains.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.tututu.trains.entity.CarriegeType;
import ru.tututu.trains.mapper.CarriegeTypeMapper;
import ru.tututu.trains.utils.DataSourceProxy;
import ru.tututu.trains.utils.params.IntegerParam;
import ru.tututu.trains.utils.params.QueryParam;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CarriegeTypeRepo {
    @Autowired
    private final DataSourceProxy dataSourceProxy;

    public CarriegeTypeRepo(DataSourceProxy dataSourceProxy) {
        this.dataSourceProxy = dataSourceProxy;
    }

    public Optional<CarriegeType> getCarriegeTypeById(int id) throws SQLException {
        String sql = "SELECT * FROM carriege_type WHERE id=?";
        QueryParam[] queryParams = new QueryParam[]{new IntegerParam(id)};
        List<CarriegeType> carriegeTypeList = dataSourceProxy.executeSelect(sql, new CarriegeTypeMapper(), queryParams);

        return carriegeTypeList.isEmpty() ? Optional.empty() : Optional.of(carriegeTypeList.get(0));
    }
}
