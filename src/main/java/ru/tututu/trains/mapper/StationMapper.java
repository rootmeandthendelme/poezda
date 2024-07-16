package ru.tututu.trains.mapper;

import org.springframework.stereotype.Component;
import ru.tututu.trains.entity.Station;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class StationMapper implements ResultSetMapper<Station>{

    @Override
    public Station map(ResultSet resultSet) throws SQLException {
        Station station = new Station();
        station.setName(resultSet.getString("name"));
        station.setId(resultSet.getInt("id"));
        station.setLocalityId(resultSet.getInt("locality_id"));
        return station;
    }
}
