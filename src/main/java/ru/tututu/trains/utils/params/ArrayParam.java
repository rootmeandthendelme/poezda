package ru.tututu.trains.utils.params;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ArrayParam implements QueryParam{
    private final Array value;

    public ArrayParam(Array value) {
        this.value = value;
    }

    @Override
    public void setQueryParamValueToStatement(PreparedStatement preparedStatement, int index) throws SQLException {
        preparedStatement.setArray(index, value);
    }
}
