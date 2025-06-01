package com.github.ethancarter.unipage.jdbc;

import com.github.ethancarter.unipage.result.set.ColumnMetadata;
import com.github.ethancarter.unipage.result.set.DefaultColumnMetadata;
import com.github.ethancarter.unipage.util.StringUtils;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultSetMetadataColumnFactory {

    public static List<ColumnMetadata> createColumns(ResultSetMetaData rsmd) throws SQLException {
        List<ColumnMetadata> list = new ArrayList<>();
        int columnCount = rsmd.getColumnCount();
        for (int i = 0; i < columnCount; i++) {
            int columnIndex = i + 1;
            String columnName = lookupColumnName(rsmd, columnIndex);
            Class<?> columnType;
            try {
                columnType = Class.forName(rsmd.getColumnClassName(columnIndex));
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            list.add(new DefaultColumnMetadata(columnName, columnType, i));
        }
        return list;
    }

    public static String lookupColumnName(ResultSetMetaData resultSetMetaData, int columnIndex) throws SQLException {
        String name = resultSetMetaData.getColumnLabel(columnIndex);
        if (StringUtils.isEmpty(name)) {
            name = resultSetMetaData.getColumnName(columnIndex);
        }
        return name;
    }


}
