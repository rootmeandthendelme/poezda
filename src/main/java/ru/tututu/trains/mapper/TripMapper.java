package ru.tututu.trains.mapper;

import org.springframework.stereotype.Component;
import ru.tututu.trains.entity.Trip;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TripMapper implements ResultSetMapper<Trip>{

    @Override
    public Trip map(ResultSet resultSet) throws SQLException {
        Trip trip = new Trip();
        trip.setId(resultSet.getInt("id"));
        trip.setTravelDate(resultSet.getDate("travel_date"));
        trip.setTrainId(resultSet.getInt("train_id"));
        return trip;
    }
}
