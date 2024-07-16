package ru.tututu.trains.mapper;

import org.springframework.stereotype.Component;
import ru.tututu.trains.entity.Seat;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class SeatMapper implements ResultSetMapper<Seat>{
    @Override
    public Seat map(ResultSet resultSet) throws SQLException {
        Seat seat = new Seat();
        seat.setSeatNumber(resultSet.getInt("seat_number"));
        seat.setId(resultSet.getInt("id"));
        seat.setCarriegeId(resultSet.getInt("carriege_id"));
        return seat;
    }
}
