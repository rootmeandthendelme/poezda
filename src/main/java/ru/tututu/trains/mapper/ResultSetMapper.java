package ru.tututu.trains.mapper;

import ru.tututu.trains.entity.Platform;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetMapper<T> {
    T map(ResultSet resultSet) throws SQLException;
}
