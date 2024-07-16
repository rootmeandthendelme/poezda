package ru.tututu.trains.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.tututu.trains.entity.Platform;
import ru.tututu.trains.entity.Train;
import ru.tututu.trains.entity.TripPoint;
import ru.tututu.trains.mapper.TrainMapper;
import ru.tututu.trains.mapper.TripPointMapper;
import ru.tututu.trains.utils.DataSourceProxy;
import ru.tututu.trains.utils.params.ArrayParam;
import ru.tututu.trains.utils.params.IntegerParam;
import ru.tututu.trains.utils.params.QueryParam;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class TripPointRepo {
    @Autowired
    private final DataSourceProxy dataSourceProxy;

    public TripPointRepo(DataSourceProxy dataSourceProxy) {
        this.dataSourceProxy = dataSourceProxy;
    }

    public List<TripPoint> getRouteByTripId(int tripId, List<Platform> departurePlatforms, List<Platform> arrivalPlatforms) throws SQLException {
        String sql = """
                SELECT * FROM trip_point
                WHERE trip_point.trip_id=?
                AND trip_point.arrival_time>=(
                SELECT trip_point.arrival_time FROM trip_point WHERE trip_point.platform_id = ANY(?)
                AND trip_point.trip_id=?
                )
                AND trip_point.arrival_time<=(
                SELECT trip_point.arrival_time FROM trip_point WHERE trip_point.platform_id = ANY(?)
                AND trip_point.trip_id=?
                )
                ORDER BY trip_point.arrival_time""";
        QueryParam[] queryParams = new QueryParam[]{
                new IntegerParam(tripId),
                new ArrayParam(dataSourceProxy.createArrayOf("INT", departurePlatforms.stream().map(Platform::getId).toArray(Object[]::new))),
                new IntegerParam(tripId),
                new ArrayParam(dataSourceProxy.createArrayOf("INT", arrivalPlatforms.stream().map(Platform::getId).toArray(Object[]::new))),
                new IntegerParam(tripId),
        };
        return dataSourceProxy.executeSelect(sql, new TripPointMapper(), queryParams);
    }

    public Optional<TripPoint> getTripById(int id) throws SQLException {
        String sql = "SELECT * FROM trip_point\n" +
                "WHERE id=?";
        QueryParam[] queryParams = new QueryParam[]{new IntegerParam(id)};
        List<TripPoint> tripPoints = dataSourceProxy.executeSelect(sql, new TripPointMapper(), queryParams);

        return tripPoints.isEmpty() ? Optional.empty() : Optional.of(tripPoints.get(0));
    }
}
