package com.github.ethancarter.unipage.web.mvc;

import com.github.ethancarter.unipage.web.component.PageInfoRepresentation;
import com.github.ethancarter.unipage.executor.PaginationStatementOperations;
import com.github.ethancarter.unipage.statement.PaginationStatement;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class PaginationInfoActionHandler implements PaginationActionHandler {

    private final PaginationStatementOperations paginationStatementOperations;

    public PaginationInfoActionHandler(PaginationStatementOperations paginationStatementOperations) {
        this.paginationStatementOperations = paginationStatementOperations;
    }

    @Override
    public String getAction() {
        return "info";
    }

    @Override
    public Object handle(String paginationKey, PaginationStatement statement,
                         HttpServletRequest request, HttpServletResponse response) {
        return PageInfoRepresentation.of(paginationStatementOperations.queryForInformation(statement));
    }
}
