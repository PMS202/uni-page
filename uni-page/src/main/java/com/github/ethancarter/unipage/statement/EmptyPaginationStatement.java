package com.github.ethancarter.unipage.statement;

import com.github.ethancarter.unipage.domain.Pageable;

/**
 * 空分页语句
 *
 * @author Ethan Carter
 * @date 2024/08/19
 */
public class EmptyPaginationStatement implements PaginationStatement {

    private static final EmptyPaginationStatement INSTANCE = new EmptyPaginationStatement();

    private EmptyPaginationStatement() {
    }

    public static EmptyPaginationStatement getInstance() {
        return INSTANCE;
    }

    @Override
    public Object getNativeStatement() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Pageable getPageable() {
        throw new UnsupportedOperationException();
    }

}
