package ru.tututu.trains.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.tututu.trains.entity.Reservation;
import ru.tututu.trains.entity.TripPoint;
import ru.tututu.trains.exceptions.NotFoundException;
import ru.tututu.trains.mapper.ReservationMapper;
import ru.tututu.trains.utils.DataSourceProxy;
import ru.tututu.trains.utils.params.IntegerParam;
import ru.tututu.trains.utils.params.QueryParam;
import ru.tututu.trains.utils.params.StringParam;

import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ReservationRepo {
    @Autowired
    private final DataSourceProxy dataSourceProxy;

    public ReservationRepo(DataSourceProxy dataSourceProxy) {
        this.dataSourceProxy = dataSourceProxy;
    }

    public Reservation createReservation(Reservation reservation) throws SQLException {
        String sql = """
                INSERT INTO public.reservation(seat_id, user_id, startpoint_id, endpoint_id, passport, full_name)
            	VALUES (?, ?, ?, ?, ?, ?) RETURNING id, seat_id, user_id, startpoint_id, endpoint_id, passport, full_name;
                """;
        QueryParam[] queryParams = new QueryParam[]{
                new IntegerParam(reservation.getSeatId()), new IntegerParam(reservation.getUserId()),
                new IntegerParam(reservation.getStartPointId()), new IntegerParam(reservation.getEndPointId()),
                new StringParam(reservation.getPassport()), new StringParam(reservation.getFullName())
        };

        List<Reservation> reservationList = dataSourceProxy.executeSelect(sql, new ReservationMapper(), queryParams);

        return reservationList.isEmpty()? null: reservationList.get(0);
    }

    public void deleteReservation(int id) throws SQLException {

        String sql = """
                DELETE FROM reservation
                WHERE reservation.id=?
                """;
        QueryParam[] queryParams = new QueryParam[]{new IntegerParam(id)};

        dataSourceProxy.executeDelete(sql,queryParams);
    }

    public Optional<Reservation> findReservationById(int id) throws SQLException {
        String sql = "SELECT * FROM reservation WHERE id=?";
        QueryParam[] queryParams = new QueryParam[]{new IntegerParam(id)};
        List<Reservation> reservationList = dataSourceProxy.executeSelect(sql, new ReservationMapper(), queryParams);

        return reservationList.isEmpty() ? Optional.empty() : Optional.of(reservationList.get(0));
    }
}
