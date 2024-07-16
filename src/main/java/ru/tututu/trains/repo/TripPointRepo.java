package ru.tututu.trains.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.tututu.trains.entity.TripPoint;
import ru.tututu.trains.mapper.TripPointMapper;
import ru.tututu.trains.utils.DataSourceProxy;
import ru.tututu.trains.utils.params.ArrayParam;
import ru.tututu.trains.utils.params.IntegerParam;
import ru.tututu.trains.utils.params.QueryParam;

import java.sql.SQLException;
import java.util.List;

@Component
public class TripPointRepo {
    @Autowired
    private final DataSourceProxy dataSourceProxy;

    public TripPointRepo(DataSourceProxy dataSourceProxy) {
        this.dataSourceProxy = dataSourceProxy;
    }

    public List<TripPoint> getRouteByTripId(int tripId, Object[] departurePlatforms, Object[] arrivalPlatforms) throws SQLException {
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
                new ArrayParam(dataSourceProxy.createArrayOf("INT", departurePlatforms)),
                new IntegerParam(tripId),
                new ArrayParam(dataSourceProxy.createArrayOf("INT", arrivalPlatforms)),
                new IntegerParam(tripId),
        };
        return dataSourceProxy.executeSelect(sql, new TripPointMapper(), queryParams);
    }
}
