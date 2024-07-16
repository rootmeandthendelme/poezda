package ru.tututu.trains.mapper;

import org.springframework.stereotype.Component;
import ru.tututu.trains.entity.TripPoint;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TripPointMapper implements ResultSetMapper<TripPoint>{

    @Override
    public TripPoint map(ResultSet resultSet) throws SQLException {
        TripPoint tripPoint = new TripPoint();
        tripPoint.setTripId(resultSet.getInt("trip_id"));
        tripPoint.setArrivalTime(resultSet.getTime("arrival_time"));
        tripPoint.setDepartureTime(resultSet.getTime("departure_time"));
        tripPoint.setPlatformId(resultSet.getInt("platform_id"));
        tripPoint.setId(resultSet.getInt("id"));
        return tripPoint;
    }
}
