package com.github.ethancarter.unipage.web.component;

import com.github.ethancarter.unipage.result.set.PaginationResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NullPaginationExporter implements PaginationExporter {

    @Override
    public void exports(PaginationResultSet resultSet, HttpServletRequest request, HttpServletResponse response) {
    }
}
