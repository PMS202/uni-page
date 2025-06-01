package com.github.ethancarter.web.utils;

import com.github.ethancarter.unipage.result.set.PaginationResultSetMetadata;
import com.github.ethancarter.unipage.result.set.PaginationRow;
import com.github.ethancarter.unipage.web.component.BeanPropertyPaginationRowMapper;
import org.springframework.jdbc.support.JdbcUtils;

import java.beans.PropertyDescriptor;
import java.sql.SQLException;

public class JdbcBeanPropertyPaginationRowMapper<T> extends BeanPropertyPaginationRowMapper<T> {

    public JdbcBeanPropertyPaginationRowMapper(Class<T> mappedClass) {
        super(mappedClass);
    }

    public JdbcBeanPropertyPaginationRowMapper(Class<T> mappedClass, boolean checkFullyPopulated) {
        super(mappedClass, checkFullyPopulated);
    }

    @Override
    protected Object getColumnValue(PaginationResultSetMetadata metadata, PaginationRow prs,
                                    int index, String name, PropertyDescriptor pd) {
        try {
            if (prs instanceof JdbcPaginationRow) {
                return JdbcUtils.getResultSetValue(((JdbcPaginationRow) prs)
                        .getResultSet(), index + 1, pd.getPropertyType());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return super.getColumnValue(metadata, prs, index, name, pd);
    }

}
