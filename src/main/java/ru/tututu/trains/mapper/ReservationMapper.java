package ru.tututu.trains.mapper;

import org.springframework.stereotype.Component;
import ru.tututu.trains.entity.Reservation;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ReservationMapper implements ResultSetMapper<Reservation>{
    @Override
    public Reservation map(ResultSet resultSet) throws SQLException {
        Reservation reservation = new Reservation();
        reservation.setId(resultSet.getInt("id"));
        reservation.setEndPointId(resultSet.getInt("endpoint_id"));
        reservation.setFullName(resultSet.getString("full_name"));
        reservation.setSeatId(resultSet.getInt("seat_id"));
        reservation.setPassport(resultSet.getString("passport"));
        reservation.setStartPointId(resultSet.getInt("startpoint_id"));
        reservation.setUserId(resultSet.getInt("user_id"));
        return reservation;
    }
}
