package com.github.ethancarter.unipage.jdbc;

import com.github.ethancarter.unipage.exception.PaginationException;

/**
 * JDBC 分页异常
 *
 * @author Ethan Carter
 * @date 2024/03/08
 */
public class JdbcPaginationException extends PaginationException {

    public JdbcPaginationException(String msg) {
        super(msg);
    }

    public JdbcPaginationException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public JdbcPaginationException(Throwable cause) {
        super(cause);
    }
}
