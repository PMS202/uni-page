package com.github.ethancarter.unipage.result.set;

import com.github.ethancarter.unipage.domain.Page;
import com.github.ethancarter.unipage.domain.PageImpl;
import com.github.ethancarter.unipage.exception.PaginationException;

import java.util.List;

/**
 * 分页内容处理程序
 *
 * @author Ethan Carter
 * @date 2024/05/11
 */
public abstract class AbstractPaginationContentHandler<T> implements PaginationResultSetHandler<T> {

    private final PaginationResultSetHandler<T> handler;

    protected AbstractPaginationContentHandler(PaginationRowMapper<T> rowMapper) {
        this(new PaginationRowMapperResultSetHandler<>(rowMapper));
    }

    protected AbstractPaginationContentHandler(PaginationResultSetHandler<T> handler) {
        this.handler = handler;
    }

    @Override
    public Page<T> handle(PaginationResultSet resultSet) throws PaginationException {
        Page<T> handle = handler.handle(resultSet);
        if (handle.hasContent()) {
            List<T> handleContent = handleContent(handle.getContent());
            if (handle.getContent() == handleContent) {
                return handle;
            } else {
                return new PageImpl<>(handle.getTotalElements(), handleContent, handle.getPageable());
            }
        }
        return handle;
    }

    protected List<T> handleContent(List<T> content) {
        return content;
    }
}
