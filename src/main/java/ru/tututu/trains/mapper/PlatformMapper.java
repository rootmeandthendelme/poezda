package ru.tututu.trains.mapper;

import org.springframework.stereotype.Component;
import ru.tututu.trains.entity.Platform;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PlatformMapper implements ResultSetMapper<Platform>{
    @Override
    public Platform map(ResultSet resultSet) throws SQLException {
        Platform platform = new Platform();
        platform.setId(resultSet.getInt("id"));
        platform.setNum(resultSet.getInt("num"));
        platform.setStationId(resultSet.getInt("station_id"));
        return platform;
    }
}
