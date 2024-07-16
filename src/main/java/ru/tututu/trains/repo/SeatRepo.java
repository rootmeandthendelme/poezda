package ru.tututu.trains.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.tututu.trains.entity.Seat;
import ru.tututu.trains.mapper.SeatMapper;
import ru.tututu.trains.utils.DataSourceProxy;
import ru.tututu.trains.utils.params.IntegerParam;
import ru.tututu.trains.utils.params.QueryParam;
import ru.tututu.trains.utils.params.TimeParam;

import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

@Component
public class SeatRepo {
    @Autowired
    private final DataSourceProxy dataSourceProxy;

    public SeatRepo(DataSourceProxy dataSourceProxy) {
        this.dataSourceProxy = dataSourceProxy;
    }

    public List<Seat> getNotReservedSeats(int tripId, int carriegeId, Time departureTime, Time arrivalTime) throws SQLException {
        String sql = """
                SELECT seat.id, seat.seat_number, seat.carriege_id FROM seat
                JOIN carriege ON seat.carriege_id=carriege.id
                WHERE carriege.id=? AND seat.id NOT IN(
                SELECT seat.id FROM reservation
                JOIN trip_point start_time ON start_time.id=reservation.startpoint_id AND start_time.trip_id=?
                JOIN trip_point end_time ON end_time.id=reservation.endpoint_id AND end_time.trip_id=?
                JOIN seat ON seat.id=reservation.seat_id
                WHERE(((end_time.arrival_time>=? AND end_time.arrival_time<=?)
                	OR ((start_time.arrival_time>=? AND start_time.arrival_time<=?))
                	OR ((start_time.arrival_time<=? AND end_time.arrival_time>=?)))))
                """;
        TimeParam startTime = new TimeParam(departureTime);
        TimeParam endTime = new TimeParam(arrivalTime);

        QueryParam[] queryParams = new QueryParam[]{
                new IntegerParam(carriegeId),
                new IntegerParam(tripId), new IntegerParam(tripId),
                startTime, endTime,
                startTime, endTime,
                startTime, endTime
        };

        return dataSourceProxy.executeSelect(sql, new SeatMapper(), queryParams);
    }
}
