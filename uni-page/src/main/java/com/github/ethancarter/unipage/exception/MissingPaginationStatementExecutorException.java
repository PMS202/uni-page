package com.github.ethancarter.unipage.exception;

import jakarta.annotation.Nullable;

/**
 * 缺少分页语句执行程序异常
 *
 * @author Ethan Carter
 * @date 2025/05/28
 */
public class MissingPaginationStatementExecutorException extends PaginationException {

    public MissingPaginationStatementExecutorException(String msg) {
        super(msg);
    }

    public MissingPaginationStatementExecutorException(String msg, @Nullable Throwable cause) {
        super(msg, cause);
    }
}
