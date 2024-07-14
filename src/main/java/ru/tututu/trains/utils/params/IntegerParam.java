package ru.tututu.trains.utils.params;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class IntegerParam implements QueryParam{
    private final int value;

    public IntegerParam(int value) {
        this.value = value;
    }

    @Override
    public void setQueryParamValueToStatement(PreparedStatement preparedStatement, int index) throws SQLException {
        preparedStatement.setInt(index, value);
    }
}
