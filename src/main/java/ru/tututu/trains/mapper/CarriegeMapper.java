package ru.tututu.trains.mapper;

import org.springframework.stereotype.Component;
import ru.tututu.trains.entity.Carriege;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CarriegeMapper implements ResultSetMapper<Carriege>{
    @Override
    public Carriege map(ResultSet resultSet) throws SQLException {
        Carriege carriege = new Carriege();
        carriege.setCarriegeNumber(resultSet.getInt("carriege_number"));
        carriege.setTrainId(resultSet.getInt("train_id"));
        carriege.setId(resultSet.getInt("id"));
        carriege.setCarriegeTypeId(resultSet.getInt("carriege_type_id"));
        return carriege;
    }
}
