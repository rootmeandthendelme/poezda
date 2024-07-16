package ru.tututu.trains.mapper;

import org.springframework.stereotype.Component;
import ru.tututu.trains.entity.Locality;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class LocalityMapper implements ResultSetMapper<Locality>{
    @Override
    public Locality map(ResultSet resultSet) throws SQLException {
        Locality locality = new Locality();
        locality.setName(resultSet.getString("name"));
        locality.setId(resultSet.getInt("id"));
        return locality;
    }
}
