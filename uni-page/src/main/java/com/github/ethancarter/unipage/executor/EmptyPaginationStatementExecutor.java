package com.github.ethancarter.unipage.executor;

import com.github.ethancarter.unipage.domain.PageRequest;
import com.github.ethancarter.unipage.result.set.EmptyPaginationResultSet;
import com.github.ethancarter.unipage.domain.PageImpl;
import com.github.ethancarter.unipage.domain.PageInformation;
import com.github.ethancarter.unipage.domain.Pageable;
import com.github.ethancarter.unipage.exception.PaginationException;
import com.github.ethancarter.unipage.result.set.PaginationResultSet;
import com.github.ethancarter.unipage.statement.EmptyPaginationStatement;
import com.github.ethancarter.unipage.statement.PaginationStatement;

/**
 * 空分页语句执行器
 *
 * @author Ethan Carter
 * @date 2024/09/02
 */
public class EmptyPaginationStatementExecutor implements PaginationStatementExecutor<EmptyPaginationStatement> {

    private static final EmptyPaginationStatementExecutor INSTANCE = new EmptyPaginationStatementExecutor();
    private final Pageable pageable = PageRequest.of(1, 0);
    private final PageInformation pageInformation = new PageImpl<>(0, pageable);
    private final EmptyPaginationResultSet resultSet = new EmptyPaginationResultSet(pageable);

    private EmptyPaginationStatementExecutor() {
    }

    public static EmptyPaginationStatementExecutor getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean supports(PaginationStatement paginationStatement) {
        return paginationStatement instanceof EmptyPaginationStatement;
    }

    @Override
    public PageInformation executeForInformation(EmptyPaginationStatement statement) throws PaginationException {
        return pageInformation;
    }

    @Override
    public PaginationResultSet executeForResultSet(EmptyPaginationStatement statement) throws PaginationException {
        return resultSet;
    }
}
