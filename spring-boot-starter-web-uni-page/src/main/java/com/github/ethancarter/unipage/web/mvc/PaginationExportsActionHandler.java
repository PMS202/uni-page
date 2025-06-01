package com.github.ethancarter.unipage.web.mvc;

import com.github.ethancarter.unipage.web.component.NullPaginationExporter;
import com.github.ethancarter.unipage.web.component.PaginationComponent;
import com.github.ethancarter.unipage.web.component.PaginationComponentFactory;
import com.github.ethancarter.unipage.web.component.PaginationExporter;
import com.github.ethancarter.unipage.executor.PaginationStatementOperations;
import com.github.ethancarter.unipage.statement.PaginationStatement;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.function.Supplier;

@Component
public class PaginationExportsActionHandler implements PaginationActionHandler {

    private final PaginationComponentFactory componentFactory;
    private final PaginationStatementOperations paginationStatementOperations;

    private PaginationExporter defaultExporter;

    public PaginationExportsActionHandler(PaginationComponentFactory componentFactory,
                                          PaginationStatementOperations paginationStatementOperations) {
        this.componentFactory = componentFactory;
        this.paginationStatementOperations = paginationStatementOperations;
        this.defaultExporter = getExporter(componentFactory.getDefaultPaginationComponent(), NullPaginationExporter::new);
    }

    @Override
    public String getAction() {
        return "exports";
    }

    @Override
    public Object handle(String paginationKey, PaginationStatement statement,
                         HttpServletRequest request, HttpServletResponse response) {
        PaginationExporter exporter =
                getExporter(componentFactory.getPaginationComponent(paginationKey), () -> defaultExporter);
        paginationStatementOperations.queryForResultSet(statement, resultSet -> {
            exporter.exports(resultSet, request, response);
            return null;
        });
        return null;
    }

    public void setDefaultExporter(PaginationExporter defaultExporter) {
        Assert.notNull(defaultExporter, "defaultExporter must not be null");
        this.defaultExporter = defaultExporter;
    }

    private PaginationExporter getExporter(PaginationComponent component, Supplier<PaginationExporter> defaultExporter) {
        return Optional.ofNullable(component).map(PaginationComponent::getExporter).orElseGet(defaultExporter);
    }
}
