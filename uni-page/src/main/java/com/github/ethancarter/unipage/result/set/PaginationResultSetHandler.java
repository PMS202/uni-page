package com.github.ethancarter.unipage.result.set;

import com.github.ethancarter.unipage.result.PaginationResultHandler;
import com.github.ethancarter.unipage.domain.Page;
import com.github.ethancarter.unipage.exception.PaginationException;

/**
 * 分页结果处理程序
 *
 * @author Ethan Carter
 * @date 2023/07/28
 */
public interface PaginationResultSetHandler<T> extends PaginationResultHandler<PaginationResultSet, Page<T>> {
    /**
     * 处理
     *
     * @param resultSet 结果集
     * @return {@link Page}<{@link T}>
     */
    Page<T> handle(PaginationResultSet resultSet) throws PaginationException;
}
