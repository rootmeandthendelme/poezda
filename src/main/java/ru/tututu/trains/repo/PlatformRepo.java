package ru.tututu.trains.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.tututu.trains.entity.Platform;
import ru.tututu.trains.mapper.PlatformMapper;
import ru.tututu.trains.utils.DataSourceProxy;
import ru.tututu.trains.utils.params.IntegerParam;
import ru.tututu.trains.utils.params.QueryParam;
import ru.tututu.trains.utils.params.StringParam;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class PlatformRepo {
    @Autowired
    private final DataSourceProxy dataSourceProxy;

    public PlatformRepo(DataSourceProxy dataSourceProxy) {
        this.dataSourceProxy = dataSourceProxy;
    }

    public List<Platform> getAllPlatformsByLocalityName(String localityName) throws SQLException {
        String sql = "SELECT * FROM platform\n" +
                        "JOIN station ON platform.station_id=station.id\n" +
                        "JOIN locality ON station.locality_id=locality.id\n" +
                        "WHERE locality.name=?\n";
        QueryParam[] queryParams = new QueryParam[]{new StringParam(localityName)};
        return dataSourceProxy.executeSelect(sql, new PlatformMapper(), queryParams);
    }

    public Optional<Platform> getPlatformById(int id) throws SQLException {
        String sql = "SELECT * FROM platform\n" +
                "WHERE platform.id=?";
        QueryParam[] queryParams = new QueryParam[]{new IntegerParam(id)};
        List<Platform> platformList = dataSourceProxy.executeSelect(sql, new PlatformMapper(), queryParams);

        return platformList.isEmpty() ? Optional.empty() : Optional.of(platformList.get(0));
    }
}
