package ru.tututu.trains.mapper;

import org.springframework.stereotype.Component;
import ru.tututu.trains.entity.CarriegeType;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CarriegeTypeMapper implements ResultSetMapper<CarriegeType>{
    @Override
    public CarriegeType map(ResultSet resultSet) throws SQLException {
        CarriegeType carriegeType = new CarriegeType();
        carriegeType.setType(resultSet.getString("type"));
        carriegeType.setId(resultSet.getInt("id"));
        return carriegeType;
    }
}
