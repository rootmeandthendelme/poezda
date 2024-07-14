package ru.tututu.trains.utils.params;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface QueryParam {
    void setQueryParamValueToStatement(PreparedStatement preparedStatement, int index) throws SQLException;
}