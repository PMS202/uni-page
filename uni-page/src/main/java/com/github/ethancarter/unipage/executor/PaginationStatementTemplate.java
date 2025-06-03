package com.github.ethancarter.unipage.executor;

import com.github.ethancarter.unipage.result.set.*;
import com.github.ethancarter.unipage.domain.Page;
import com.github.ethancarter.unipage.domain.PageInformation;
import com.github.ethancarter.unipage.exception.PaginationException;
import com.github.ethancarter.unipage.statement.PaginationStatement;
import com.github.ethancarter.unipage.exception.MissingPaginationStatementExecutorException;
import com.github.ethancarter.unipage.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PaginationStatementTemplate implements PaginationStatementOperations {

    private final List<PaginationStatementExecutor<?>> statementExecutors = new ArrayList<>();

    public PaginationStatementTemplate() {
        statementExecutors.add(EmptyPaginationStatementExecutor.getInstance());
    }

    @Override
    public PageInformation queryForInformation(PaginationStatement statement) throws PaginationException {
        return getStatementExecutor(statement).executeForInformation(statement);
    }

    @Override
    public Page<Map<String, Object>> queryForMapResultSet(PaginationStatement statement) throws PaginationException {
        return queryForResultSet(statement, new MapPaginationRowMapper());
    }

    @Override
    public <T> Page<T> queryForResultSet(PaginationStatement statement,
                                         PaginationRowMapper<T> rowMapper) throws PaginationException {
        return queryForResultSet(statement, new PaginationRowMapperResultSetHandler<>(rowMapper));
    }

    @Override
    public <T> Page<T> queryForResultSet(PaginationStatement statement,
                                         PaginationResultSetHandler<T> handler) throws PaginationException {
        try (PaginationResultSet rs = getStatementExecutor(statement).executeForResultSet(statement)) {
            return handler.handle(rs);
        } catch (Exception e) {
            throw new PaginationException(e);
        }
    }

    @SuppressWarnings("unchecked")
    protected PaginationStatementExecutor<PaginationStatement> getStatementExecutor(PaginationStatement statement) {
        Assert.notNull(statement, "pagination statement must not be null.");
        for (PaginationStatementExecutor<?> statementExecutor : statementExecutors) {
            if (statementExecutor.supports(statement)) {
                return (PaginationStatementExecutor<PaginationStatement>) statementExecutor;
            }
        }
        String message = "PaginationStatementExecutor not found for statement of type [" + statement.getClass().getName() + "].";
        if (statement.getLanguage() != null && !statement.getLanguage().isEmpty()) {
            message = "PaginationStatementExecutor not found for language [" + statement.getLanguage() + "].";
        }
        throw new MissingPaginationStatementExecutorException(message);
    }

    public void addStatementExecutor(PaginationStatementExecutor<?> statementExecutor) {
        Assert.notNull(statementExecutor, "PaginationStatementExecutor must not be null.");
        this.statementExecutors.add(statementExecutor);
    }
}
