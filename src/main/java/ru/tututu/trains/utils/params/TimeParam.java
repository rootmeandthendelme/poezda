package ru.tututu.trains.utils.params;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;

public class TimeParam implements QueryParam{
    private final Time value;

    public TimeParam(String value) {
        this.value = Time.valueOf(value);
    }

    public TimeParam(Time value) {
        this.value = value;
    }

    @Override
    public void setQueryParamValueToStatement(PreparedStatement preparedStatement, int index) throws SQLException {
        preparedStatement.setTime(index, value);
    }
}
