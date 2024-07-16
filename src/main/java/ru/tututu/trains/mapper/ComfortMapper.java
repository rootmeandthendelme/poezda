package ru.tututu.trains.mapper;

import org.springframework.stereotype.Component;
import ru.tututu.trains.entity.Comfort;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ComfortMapper implements ResultSetMapper<Comfort>{
    @Override
    public Comfort map(ResultSet resultSet) throws SQLException {
        Comfort comfort = new Comfort();
        comfort.setId(resultSet.getInt("id"));
        comfort.setValue(resultSet.getString("value"));
        return comfort;
    }
}
