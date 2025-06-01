package com.github.ethancarter.unipage.web.component;

import com.github.ethancarter.unipage.result.set.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DefaultPaginationComponent implements PaginationComponent {

    private PaginationResultSetHandler<?> dataResultSetHandler = new PaginationMapResultSetHandler();
    private PaginationRowMapper<?> dataRowMapper = new MapPaginationRowMapper();
    private PaginationExporter exporter = new NullPaginationExporter();

    @Override
    public PaginationResultSetHandler<?> getDataResultSetHandler() {
        return dataResultSetHandler;
    }

    @Override
    public PaginationRowMapper<?> getDataRowMapper() {
        return dataRowMapper;
    }

    @Override
    public PaginationExporter getExporter() {
        return exporter;
    }
}
