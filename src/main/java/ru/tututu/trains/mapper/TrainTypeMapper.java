package ru.tututu.trains.mapper;

import org.springframework.stereotype.Component;
import ru.tututu.trains.entity.TrainType;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TrainTypeMapper implements ResultSetMapper<TrainType>{
    @Override
    public TrainType map(ResultSet resultSet) throws SQLException {
        TrainType trainType = new TrainType();
        trainType.setType(resultSet.getString("type"));
        trainType.setId(resultSet.getInt("id"));
        return trainType;
    }
}
