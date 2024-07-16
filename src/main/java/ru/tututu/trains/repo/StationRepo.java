package ru.tututu.trains.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.tututu.trains.entity.Station;
import ru.tututu.trains.mapper.StationMapper;
import ru.tututu.trains.utils.DataSourceProxy;
import ru.tututu.trains.utils.params.IntegerParam;
import ru.tututu.trains.utils.params.QueryParam;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class StationRepo {
    @Autowired
    private final DataSourceProxy dataSourceProxy;

    public StationRepo(DataSourceProxy dataSourceProxy) {
        this.dataSourceProxy = dataSourceProxy;
    }

    public Optional<Station> getStationById(int id) throws SQLException {
        String sql = "SELECT * FROM station WHERE id=?";
        QueryParam[] queryParams = new QueryParam[]{new IntegerParam(id)};
        List<Station> stationList = dataSourceProxy.executeSelect(sql, new StationMapper(), queryParams);

        return stationList.isEmpty() ? Optional.empty() : Optional.of(stationList.get(0));
    }
}
