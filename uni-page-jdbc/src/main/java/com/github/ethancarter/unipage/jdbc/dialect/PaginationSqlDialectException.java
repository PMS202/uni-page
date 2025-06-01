package com.github.ethancarter.unipage.jdbc.dialect;

import com.github.ethancarter.unipage.exception.PaginationException;

public class PaginationSqlDialectException extends PaginationException {

    public PaginationSqlDialectException(String msg) {
        super(msg);
    }

    public PaginationSqlDialectException(Throwable cause) {
        super(cause);
    }

    public PaginationSqlDialectException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
