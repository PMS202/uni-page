package com.github.ethancarter.unipage.statement;

import com.github.ethancarter.unipage.domain.Pageable;

/**
 * 分页基础语句
 *
 * @author Ethan Carter
 * @date 2023/08/21
 */
public abstract class BasePaginationStatement implements PaginationStatement {

    private final Pageable pageable;

    /**
     * 分页基础语句
     *
     * @param pageable 可分页
     */
    protected BasePaginationStatement(Pageable pageable) {
        this.pageable = pageable;
    }


    @Override
    public Pageable getPageable() {
        return pageable;
    }

}
