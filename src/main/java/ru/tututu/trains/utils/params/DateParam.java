package ru.tututu.trains.utils.params;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DateParam implements QueryParam{
    private final String value;

    public DateParam(String value) {
        this.value = value;
    }

    @Override
    public void setQueryParamValueToStatement(PreparedStatement preparedStatement, int index) throws SQLException {
        preparedStatement.setDate(index, Date.valueOf(value));
    }
}
