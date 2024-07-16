package ru.tututu.trains.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.util.ArrayUtils;
import ru.tututu.trains.entity.Platform;
import ru.tututu.trains.entity.Trip;
import ru.tututu.trains.mapper.TripMapper;
import ru.tututu.trains.utils.DataSourceProxy;
import ru.tututu.trains.utils.params.ArrayParam;
import ru.tututu.trains.utils.params.DateParam;
import ru.tututu.trains.utils.params.QueryParam;
import ru.tututu.trains.utils.params.StringParam;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class TripRepo {
    @Autowired
    private final DataSourceProxy dataSourceProxy;

    public TripRepo(DataSourceProxy dataSourceProxy) {
        this.dataSourceProxy = dataSourceProxy;
    }

    public List<Trip> getAllTripsByParameters(List<Platform> departurePlatforms, List<Platform> arrivalPlatforms, Optional<String> trainName, Optional<String> date) throws SQLException {
        List<QueryParam> params = new ArrayList<>();
        params.add(new ArrayParam(dataSourceProxy.createArrayOf("INT", departurePlatforms.stream().map(Platform::getId).toArray(Object[]::new))));
        params.add(new ArrayParam(dataSourceProxy.createArrayOf("INT", arrivalPlatforms.stream().map(Platform::getId).toArray(Object[]::new))));
        String sqlWhereClause="";

        if(trainName.isPresent()){
            sqlWhereClause += "AND trip.train_id=(SELECT train.id FROM train WHERE train.name=?)\n";
            params.add(new StringParam(trainName.get()));
        }

        if(date.isPresent()){
            sqlWhereClause += "AND trip.travel_date=?\n";
            params.add(new DateParam(date.get()));
        }

        String sql = "SELECT trip.id, trip.travel_date, trip.train_id\n" +
                "FROM trip_point presumably_departure\n" +
                "JOIN trip_point expected_arrival\n" +
                "ON presumably_departure.id!=expected_arrival.id\n" +
                "AND presumably_departure.trip_id=expected_arrival.trip_id\n" +
                "AND presumably_departure.platform_id = ANY (?)\n" +
                "AND expected_arrival.platform_id = ANY (?)\n" +
                "AND presumably_departure.departure_time<expected_arrival.arrival_time\n" +
                "AND presumably_departure.trip_id IN(\n"+
                "SELECT trip.id FROM trip\n"+
                "WHERE trip.travel_date>=CURRENT_DATE\n"+
                sqlWhereClause+
                ")\n"+
                "JOIN trip ON presumably_departure.trip_id=trip.id\n";

        return dataSourceProxy.executeSelect(sql, new TripMapper(), params.toArray(new QueryParam[0]));
    }
}
