package ru.tututu.trains.utils.params;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StringParam implements QueryParam{
    private final String value;

    public StringParam(String value) {
        this.value = value;
    }

    @Override
    public void setQueryParamValueToStatement(PreparedStatement preparedStatement, int index) throws SQLException {
        preparedStatement.setString(index, value);
    }
}
