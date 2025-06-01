package com.github.ethancarter.unipage.statement;

import com.github.ethancarter.unipage.domain.PageRequest;
import com.github.ethancarter.unipage.domain.Sort;
import com.github.ethancarter.unipage.domain.Pageable;
import jakarta.annotation.Nullable;


/**
 * 分页基础语句生成器
 *
 * @author Ethan Carter
 * @date 2023/08/01
 */
public abstract class BasePaginationStatementBuilder
        <T extends PaginationStatement, R extends BasePaginationStatementBuilder<T, R>> {

    protected Pageable pageable = PageRequest.of(1, 10, Sort.unsorted());

    public R pageable(Pageable pageable) {
        this.pageable = pageable;
        return self();
    }

    public R pageable(int pageNumber, int pageSize) {
        return pageable(pageNumber, pageSize, Sort.unsorted());
    }

    public R pageable(int pageNumber, int pageSize, Sort sort) {
        if (pageNumber < 0) {
            pageNumber = 0;
        }
        this.pageable = PageRequest.of(pageNumber, pageSize, sort);
        return self();
    }

    public R sort(@Nullable Sort sort) {
        if (sort != null) {
            this.pageable = PageRequest
                    .of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        }
        return self();
    }

    @SuppressWarnings("unchecked")
    protected R self() {
        return (R) this;
    }

    public abstract T build();

    public Pageable getPageable() {
        return pageable;
    }
}
