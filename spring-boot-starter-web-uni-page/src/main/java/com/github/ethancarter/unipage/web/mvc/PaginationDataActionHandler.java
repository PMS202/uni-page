package com.github.ethancarter.unipage.web.mvc;

import com.github.ethancarter.unipage.result.set.*;
import com.github.ethancarter.unipage.web.component.PaginationComponent;
import com.github.ethancarter.unipage.web.component.PaginationComponentFactory;
import com.github.ethancarter.unipage.executor.PaginationStatementOperations;
import com.github.ethancarter.unipage.statement.PaginationStatement;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.function.Supplier;

@Component
public class PaginationDataActionHandler implements PaginationActionHandler {

    private final PaginationComponentFactory componentFactory;
    private final PaginationStatementOperations paginationStatementOperations;

    private PaginationResultSetHandler<?> defaultDataResultHandler;

    public PaginationDataActionHandler(PaginationComponentFactory paginationComponentFactory,
                                       PaginationStatementOperations paginationStatementOperations) {
        this.componentFactory = paginationComponentFactory;
        this.defaultDataResultHandler = getResultHandler(
                this.componentFactory.getDefaultPaginationComponent(), PaginationMapResultSetHandler::new);
        this.paginationStatementOperations = paginationStatementOperations;
    }

    @Override
    public String getAction() {
        return "data";
    }

    @Override
    public Object handle(String paginationKey, PaginationStatement statement,
                         HttpServletRequest request, HttpServletResponse response) {
        PaginationResultSetHandler<?> resultHandler =
                getResultHandler(componentFactory.getPaginationComponent(paginationKey), () -> defaultDataResultHandler);
        return paginationStatementOperations.queryForResultSet(statement, resultHandler).getContent();
    }

    public void setDefaultDataResultHandler(PaginationResultSetHandler<?> defaultDataResultHandler) {
        Assert.notNull(defaultDataResultHandler, "DefaultDataResultHandler must not be null");
        this.defaultDataResultHandler = defaultDataResultHandler;
    }

    private PaginationResultSetHandler<?> getResultHandler(PaginationComponent paginationComponent,
                                                           Supplier<PaginationResultSetHandler<?>> defaultHandler) {
        if (paginationComponent == null) {
            return defaultHandler.get();
        }
        PaginationResultSetHandler<?> dataResultSetHandler = paginationComponent.getDataResultSetHandler();
        if (dataResultSetHandler != null) {
            return dataResultSetHandler;
        }
        PaginationRowMapper<?> dataRowMapper = paginationComponent.getDataRowMapper();
        if (dataRowMapper != null) {
            return new PaginationRowMapperResultSetHandler<>(dataRowMapper);
        }
        return defaultHandler.get();
    }

}
