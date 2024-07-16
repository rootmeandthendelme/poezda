package ru.tututu.trains.mapper;

import org.springframework.stereotype.Component;
import ru.tututu.trains.entity.Train;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TrainMapper implements ResultSetMapper<Train>{
    @Override
    public Train map(ResultSet resultSet) throws SQLException {
        Train train = new Train();
        train.setId(resultSet.getInt("id"));
        train.setTrainTypeId(resultSet.getInt("train_type_id"));
        train.setName(resultSet.getString("name"));
        return train;
    }
}
